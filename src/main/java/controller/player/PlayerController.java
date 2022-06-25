package controller.player;


import controller.CharacterController;
import model.character.Player;
import model.map.Level;

/**
 * The player controller. It checks if the player is colliding into the ground,
 * colliding with bullets and manages everything about the weapon.
 */
public class PlayerController extends CharacterController {

    /**
     * The player controller constructor.
     * 
     * @param level the level where the player is
     * @param player the player to control
     */
    public PlayerController(final Level level, final Player player) {
        super(level, player);
    }
}
