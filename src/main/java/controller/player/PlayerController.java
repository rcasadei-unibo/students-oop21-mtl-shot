package controller.player;

import controller.CharacterController;
import controller.map.MapController;
import model.character.Player;
import view.player.PlayerView;

/**
 * The player controller. It checks if the player is colliding into the ground, colliding with bullets 
 * and manages everything about the weapon.
 */
public class PlayerController extends CharacterController{
//    /**
//     * The player that has to be controlled.
//     */
//    private final Player player;
//    /**
//     * The View part of the player that has to be updated.
//     */
//    private final PlayerView playerView;
//    /**
//     * The map controller, used to handle the checks with the ground.
//     */
//    private final MapController mapController;

    /**
     * The player controller constructor.
     * @param player
     * @param mapController
     * @param playerView
     */
    public PlayerController(final PlayerView playerView, final MapController mapController, final Player player) {
        super(playerView, mapController, player);
    }
}
