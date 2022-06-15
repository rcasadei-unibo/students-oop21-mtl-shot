package controller.player;

import controller.CharacterController;
import model.character.Player;
import model.map.Level;
import view.player.PlayerView;

/**
 * The player controller. It checks if the player is colliding into the ground,
 * colliding with bullets and manages everything about the weapon.
 */
public class PlayerController extends CharacterController {
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
	 * 
	 * @param player
	 * @param mapController
	 * @param playerView
	 */
	public PlayerController(final Level level, final Player player) {
		super(level, player);
	}
}
