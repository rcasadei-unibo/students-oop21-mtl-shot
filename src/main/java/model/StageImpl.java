package model;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.InstanceNotFoundException;

import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import model.map.Level;
import model.weapons.Bullet;
import model.weapons.P2020;
import util.Vector2D;
import util.map.TextMap;

/**
 * TODO: javadoc.
 *
 */
public class StageImpl {

    private Player player;
    private final Collection<Bullet> bullets = null;
    private final Level level;

    public StageImpl(final TextMap textMap) throws IOException, InstanceNotFoundException {        
        this.level = new Level(Stream.of("src/main/resources/map.txt","src/main/resources/map2.txt","src/main/resources/map3.txt").collect(Collectors.toList()));
        this.player = new PlayerBuilder()
                .hitbox(new Vector2D(1, 1.5))
                .position(level.getPlayerSpawn())
                .weapon(new P2020())
                .health(new SimpleHealth())
                .lives(3)
                .build();
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Level getLevel() {
        return this.level;
    }

}
