package model;

import java.io.IOException;
import java.util.Collection;

import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import model.map.tile.MapModel;
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
    private final MapModel mapModel;

    /**
     * The stage constructor.
     * 
     * @param textMap
     * @throws IOException if the txt map sheet doesn't exist.
     */
    public StageImpl(final TextMap textMap) throws IOException {
        this.mapModel = new MapModel(textMap);
        this.player = new PlayerBuilder()
                .hitbox(new Vector2D(1, 1))
                .position(mapModel.getPlayerSpawn())
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

    public MapModel getMapModel() {
        return this.mapModel;
    }

}
