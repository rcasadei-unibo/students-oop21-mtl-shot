package model;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import model.map.tile.MapModel;
import model.weapons.Bullet;
import model.weapons.P2020;
import model.weapons.PeaceKeeper;
import util.Direction;
import util.Vector2D;
import util.map.TextMap;

/**
 * TODO: javadoc.
 *
 */
public class StageImpl {

    private Player player;
    private Collection<Bullet> bullets;
    private final MapModel mapModel;

    public StageImpl(final TextMap textMap) throws IOException {        
        this.mapModel = new MapModel(textMap);
        this.player = new PlayerBuilder()
                .hitbox(new Vector2D(1, 1))
                .position(mapModel.getPlayerSpawn())
                .weapon(new PeaceKeeper())
                .health(new SimpleHealth())
                .lives(3)
                .build();
        this.bullets = new LinkedList<>();
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        // TODO Auto-generated method stub
        return this.player;
    }

    public MapModel getMapModel() {
        // TODO Auto-generated method stub
        return this.mapModel;
    }
    
    /**
     * Returns a map where every entry represents a bullet's position and direction.
     * 
     * @return a Map
     */
    public Map<Vector2D, Direction> getBullets() {
        final Map<Vector2D, Direction> ret = new HashMap<>();
        for (final var b : this.bullets) {
            ret.put(b.getPosition(), b.getDirection());
        }
        return ret;
    }
    
    public Collection<Bullet> getBullets2() {
    	return this.bullets;
    }
    
    // public Collection<Enemy> getEnemies();
}
