package controller.player;

import controller.CharacterController;
import controller.map.MapController;
import model.character.Player;

/**
 * The player controller. It checks if the player is colliding into the ground, colliding with bullets 
 * and manages everything about the weapon.
 */
public class PlayerController extends CharacterController {

    /**
     * The player controller constructor.
     * 
     * @param player
     * @param mapController
     */
    public PlayerController(final MapController mapController, final Player player) {
        super(mapController, player);
    }
}
