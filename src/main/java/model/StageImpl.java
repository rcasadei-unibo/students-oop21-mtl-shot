package model;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.management.InstanceNotFoundException;
import model.character.Enemy;

import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.movableentity.EntityConstants;
import model.character.tools.health.SimpleHealth;
import model.map.Level;
import model.weapons.Bullet;
import model.weapons.R99;
import util.Vector2D;

/**
 * The main stage where every MetalShot entity is present.
 *
 */
public class StageImpl {

    private final Player player;
    private final Collection<Enemy> enemies;
    private final Collection<Bullet> bullets;
    private final Level level;

    /**
     * The stage constructor.
     * 
     * @throws IOException               if the txt map sheet doesn't exist.
     * @throws InstanceNotFoundException
     */
    public StageImpl() throws IOException, InstanceNotFoundException {
        this.level = new Level(
                Stream.of("map.txt", "map2.txt", "map3.txt")
                        .collect(Collectors.toList()));
        this.enemies = new LinkedList<>();
        addEnemies();
        this.player = new PlayerBuilder()
                .hitbox(EntityConstants.DEFAULT_HITBOX)
                .position(level.getPlayerSpawn())
                .weapon(new R99())
                .health(new SimpleHealth())
                .lives(3)
                .build();
        this.bullets = new LinkedList<>();
    }

    /**
     * Returns the Player.
     * @return the Player.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the Level.
     * @return the Level.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Returns a Collection of all the Enemies.
     * @return all the Enemies.
     */
    public Collection<Enemy> getEnemies() {
        return this.enemies;
    }

    /**
     * Returns a Collection of all the Bullets.
     * @return all the Bullets.
     */
    public Collection<Bullet> getBullets() {
        return this.bullets;
    }

    private void addEnemies() {
        for (final Vector2D pos : level.getEnemiesSpawn()) {
            enemies.add(new Enemy(pos, EntityConstants.DEFAULT_HITBOX, new SimpleHealth()));
        }
    }
}
