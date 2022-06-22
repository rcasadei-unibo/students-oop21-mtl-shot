package controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.character.Character;
import model.character.Enemy;
import model.character.Player;
import model.map.Level;
import model.map.tile.MapModel;
import model.weapons.Bullet;
import view.sounds.SoundManager.Sounds;

/**
 * Updates and mantains the bullets Collection,
 * checking collisions with enemies and Map tiles,
 * or else ticking them.
 *
 */
public class BulletsController {
    private Collection<Bullet> bulletsReference;
    private Player playerReference;
    private Collection<Enemy> enemiesReference;
    private SoundsController soundsControllerRef;
    private Level levelReference;
	
    /**
     * @param playerReference - A reference to the Player
     * @param bulletsReference - A reference to the bullets collection
     * @param mapReference - A reference to the Map
     */
	public BulletsController(final Player playerReference, final Collection<Bullet> bulletsReference, final Collection<Enemy> enemiesReference, final SoundsController soundsControllerRef, final Level levelReference) {
		this.playerReference = playerReference;
		this.bulletsReference = bulletsReference;
		this.enemiesReference = enemiesReference;
		this.soundsControllerRef = soundsControllerRef;
		this.levelReference = levelReference;
	}
	
	/**
	 * Ticks BulletsController.
	 */
	public void controllerTick() {
		this.bulletsReference.forEach(b -> {
			final boolean playerColliding = this.checkPlayerColliding(b);
			final var enemyColliding = this.checkEnemyColliding(b);
			final boolean tileColliding = this.checkTilesColliding(b);

			if (playerColliding && !this.playerReference.equals(b.getOwner())) {
				b.hitSomething();
				this.playerReference.getHealth().hurt(b.getDamage());
			} else if (tileColliding) {
				b.hitSomething();
			} else if (enemyColliding.isPresent() && !this.enemiesReference.contains(b.getOwner())) {
				b.hitSomething();
				this.soundsControllerRef.playSound(Sounds.HURT_1);
				enemyColliding.get().getHealth().hurt(b.getDamage());
			} else {
				if (this.levelReference.getSegmentAtPosition(b.getPosition())
						.equals(this.levelReference.getSegmentAtPosition(this.playerReference.getPosition()))) {
					b.tick();
				} else {
					b.hitSomething();
				}
			}
		});
		/*
		 * This line removes from this.bullets bullets that have hit something
		 */
		this.bulletsReference.removeIf(b -> b.hasHit());
	}
	
	/**
	 * Adds a Bullet in the bullets Collection.
	 * 
	 * @param owner - The Character who shots
	 */
	public void addBullet(final Character owner) {
		this.bulletsReference.add(new Bullet(owner));
	}
	
	/**
	 * Checks if Bullet b is colliding with
	 * any of the current Characters in game.
	 * 
	 * @param b - Bullet to be checked
	 * @return Optional of the character colliding with Bullet b
	 * Optional.empty() if b is not colliding with any Enemy
	 */
	private Optional<Character> checkEnemyColliding(final Bullet b) {
		for (final var c : this.enemiesReference) {
			if (b.isColliding(c)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}
	
	/*
	 * This method returns true if the
	 * bullet b is colliding with the player
	 */
	private boolean checkPlayerColliding(final Bullet b) {
		return this.playerReference.isColliding(b);
	}
	
	/*
	 * This method checks if Bullet b
	 * is colliding with any tile
	 */
	private boolean checkTilesColliding(final Bullet b) {
		return this.levelReference
				.getSegmentAtPosition(b.getPosition())
				.getCollidableAtPosition(b.getPosition())
				.isPresent();
	}
}
