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
import model.character.tools.health.SimpleHealth;
import model.map.Level;
import model.weapons.Bullet;
import model.weapons.P2020;
import util.UserData;
import util.Vector2D;
import util.map.TextMap;

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
     * @param textMap
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
                .hitbox(new Vector2D(1, 1.5))
                .position(level.getPlayerSpawn())
                .weapon(new P2020())
                .health(new SimpleHealth())
                .lives(3)
                .build();
        this.bullets = new LinkedList<>();
    }

    private void addEnemies() {
        for (final Vector2D pos : level.getEnemiesSpawn()) {
            enemies.add(new Enemy(pos, new Vector2D(1, 1.5), new SimpleHealth()));
        }
    }

    /**
     * 
     * @return bla
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * 
     * @return bla
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * 
     * @return bla
     */
    public Collection<Enemy> getEnemies() {
        return this.enemies;
    }

    /**
     * 
     * @return bla
     */
    public Collection<Bullet> getBullets() {
        return this.bullets;
    }
}
