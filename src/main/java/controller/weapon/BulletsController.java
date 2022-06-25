package controller.weapon;

import java.util.Collection;
import java.util.Optional;

import controller.SoundsController;
import model.character.Character;
import model.character.Enemy;
import model.character.Player;
import model.map.Level;
import model.weapons.Bullet;
import view.sounds.SoundManager.Sounds;

/**
 * Updates the bullets Collection, checking collisions with enemies and Map
 * tiles, or else ticking them.
 *
 */
public class BulletsController {
    private Collection<Bullet> bulletsReference;
    private Player playerReference;
    private Collection<Enemy> enemiesReference;
    private SoundsController soundsControllerRef;
    private Level levelReference;

    /**
     * Instantiates BulletsController.
     * 
     * @param playerReference     - Reference to the Player
     * @param bulletsReference    - Reference to the bullets collection
     * @param enemiesReference    - Reference to enemies Collection
     * @param soundsControllerRef - Reference to SoundController
     * @param levelReference      - Reference to Level
     */
    public BulletsController(final Player playerReference, final Collection<Bullet> bulletsReference,
            final Collection<Enemy> enemiesReference, final SoundsController soundsControllerRef,
            final Level levelReference) {
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
                enemyColliding.get().getHealth().hurt(b.getDamage());
                if (enemyColliding.get().getHealth().isDead()) {
                    this.soundsControllerRef.playSound(Sounds.DIE_1);
                } else {
                    this.soundsControllerRef.playSound(Sounds.HURT_1);
                }
            } else {
                if (b.getPosition().getY() > 0 && b.getPosition().getX() > 0
                        && this.levelReference.getSegmentAtPosition(b.getPosition())
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
     * Checks if Bullet b is colliding with any of the current Characters in game.
     * 
     * @param b - Bullet to be checked
     * @return Optional of the character colliding with Bullet b Optional.empty() if
     *         b is not colliding with any Enemy
     */
    private Optional<Character> checkEnemyColliding(final Bullet b) {
        for (final var c : this.enemiesReference) {
            if (b.isColliding(c)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    /**
     * @param b - Bullet to be checked
     * @return true if Bullet b is colliding with the player.
     */
    private boolean checkPlayerColliding(final Bullet b) {
        return this.playerReference.isColliding(b);
    }

    /**
     * @param b
     * @return true if Bullet b is colliding with a Tile.
     */
    private boolean checkTilesColliding(final Bullet b) {
        if (b.getPosition().getX() > 0 && b.getPosition().getY() > 0) {
            return this.levelReference.getSegmentAtPosition(b.getPosition()).getCollidableAtPosition(b.getPosition())
                    .isPresent();
        } else {
            return false;
        }
    }

}
