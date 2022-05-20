package controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import controller.map.MapController;
import model.character.Character;
import model.character.Player;
import model.map.tile.MapModel;
import model.weapons.Bullet;

/**
 * TODO: write javadoc.
 *
 */
public class BulletsController {
	private Collection<Bullet> bulletsReference;
	private Player playerReference;
	private MapController mapReference;	// TODO: it will be MapModel
	//private Collection<Enemy> enemiesReference; TODO: uncomment after merging
	
	public BulletsController(final Player playerReference, final Collection<Bullet> bulletsReference, final MapController mapReference /*, final Collection<Enemy> enemiesReference TODO: uncomment after merging */) {
		this.playerReference = playerReference;
		this.bulletsReference = bulletsReference;
		this.mapReference = mapReference;
		//this.enemiesReference = enemiesReference; TODO: uncomment after merging
	}
	
	public void controllerTick() {
		
		this.bulletsReference.forEach(b -> {
			final boolean playerColliding = this.checkPlayerColliding(b);
			final var enemyColliding = this.checkEnemyColliding(b);
			
			if (playerColliding && !this.playerReference.equals(b.getOwner())) {
				b.hitSomething();
				this.playerReference.getHealth().hurt(b.getDamage());
			} else if (false /*this.mapReference.hasSingleCollidable(b.getPosition())*/) {
				b.hitSomething();
				// TODO: if the tile is breakable, tile brakes here
			} else if (enemyColliding.isPresent() /* && !this.enemiesReference.contains(b.getOwner()) */) {
				b.hitSomething();
				/*this.enemiesReference.forEach(e -> {
					if (e.equals(enemyColliding)) {
						e.hurt(b.getDamage());
					}
				});*/
			} else {
				//if (b.getPosition().getX() <= this.mapReference.getWidth() && b.getPosition().getY() <= this.mapReference.getHeight()) {					
					b.tick();
				/*} else {
					b.hitSomething();
				}*/
			}
		});
		/*
		 * This line removes from this.bullets bullets that have hit something
		 */
		// OLD VERSION: this.bulletsReference = this.bulletsReference.stream().filter(b -> b.hasHit() == false).collect(Collectors.toList());
		this.bulletsReference.removeIf(b -> b.hasHit() == true);
	}
	
	public void addBullet(final Character owner) {
		this.bulletsReference.add(new Bullet(owner));
	}
	
	/*
	 * This method checks if Bullet b is colliding with
	 * any of the current Characters in game
	 */
	private Optional<Character> checkEnemyColliding(final Bullet b) {
		/*for (final var c : this.enemiesReference) {
			if (b.isColliding(c)) {
				return Optional.of(c);
			}
		} TODO: uncomment after merging */
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
	private Optional<Character> checkTilesColliding(final Bullet b) {
		// TODO: implementation based on Map implementation
		return Optional.empty();
	}
}
