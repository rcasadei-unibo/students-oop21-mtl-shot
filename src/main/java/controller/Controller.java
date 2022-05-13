package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import app.MetalShot;
import controller.map.MapController;
import controller.player.PlayerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import util.map.TextMap;

/**
 * 
 *
 */
public class Controller {

    private final PlayerController playerController;
    private final MapController mapController;
	private BulletsController bulletsController;
	private WeaponController weaponController;
    private final Timeline gameLoop;
    private static final double FPS = 1000;

    // Instance of model (Stage?)
    private final MetalShot viewReference;

    /**
     * The main controller constructor.
     * @param viewReference
     * @throws IOException if the text map is not present
     */
    public Controller(final MetalShot viewReference) throws IOException {
        final TextMap textMap = new TextMap("src\\main\\resources\\map.txt");
        this.mapController = new MapController(textMap);
        this.viewReference = viewReference;
		this.bulletsController = new BulletsController(this);
		this.weaponController = new WeaponController(this);
        this.playerController = new PlayerController(this.viewReference.getPlayerView(), this); // null ->
                                                                                                         // player view
        this.gameLoop = new Timeline(new KeyFrame(Duration.seconds(1 / FPS), new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                // TODO implement game loop here

                // Move player
                // Jumping and falling included
                // Shoot (player)
                // Move/shoot enemies (based on Susca's AI)
                // Check for colliding bullets
				weaponController.controllerTick();
				bulletsController.controllerTick();
				viewReference.displayBullets(getBullets());
                playerController.check();
            }
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
    }

    public void gameStart() {
        gameLoop.play();
        // TODO
    }

    public void gamePause() { // not in UML
        gameLoop.pause();
        // TODO show pause menu
    }

    public void gameReset() {
        // TODO
    }

    public void keyPressed(final KeyCode key) {
        if (key == KeyCode.A) {
            playerController.getPlayer().setLeft(true);
        }
        if (key == KeyCode.D) {
            playerController.getPlayer().setRight(true);
        }
        if (key == KeyCode.SPACE) {
            playerController.getPlayer().setJump(true);
        }
        if (key == KeyCode.S) {
            playerController.getPlayer().setCrouchKey(true);
        }
        if (key == KeyCode.ESCAPE) {
            this.gamePause();
        }
		if (key.equals(KeyCode.E)) {
			/*
			 *     // Shooting sintax
			 * if (this.weaponController.tryToShoot(this.player)) {
			 *     this.bulletsController.addBullet(this.player);
			 * }
			 * 
			 */
			System.out.println("Shooting...");
		} else if (key.equals(KeyCode.R)) {
			/*
			 *     // The player reloads
			 * this.player.getWeapon().reload();
			 * 
			 */
			System.out.println("Reloading...");
		}
    }

    public void keyReleased(final KeyCode key) {
        if (key == KeyCode.A) {
            playerController.getPlayer().setLeft(false);
        }
        if (key == KeyCode.D) {
            playerController.getPlayer().setRight(false);
        }
        if (key == KeyCode.SPACE) {
            playerController.getPlayer().setJump(false);
        }
        if (key == KeyCode.S) {
            playerController.getPlayer().setCrouchKey(false);
        }
    }

    public void save() {
        // TODO
    }

    public void load() {
        // TODO
    }

    public void nextLevel() {
        // TODO
    }

    /**
     * Gets the class that handle the map control.
     * @return MapController
     */
    public MapController getMapController() {
        return this.mapController;
    }

    /**
     * Gets the class that handle the player control.
     * @return PlayerController
     */
    public PlayerController getPlayerController() {
        return this.playerController;
    }
    /**
     * Gets the view reference.
     * @return MetalShot
     */
    public MetalShot getView() {
        return this.viewReference;
    }
    /*
	 * Returns every Character (the player, enemies, ...)
	 * currently in game.
	 */
	public Set<Character> getAllCharacters() {
		// TODO
		return null;
	}
	
	/*
	 * Returns a map where every entry represents
	 * a bullet's position and direction.
	 */
	public Map<Vector, Direction> getBullets() {
		Map<Vector, Direction> ret = new HashMap<>();
		
		for (var b : this.bulletsController.getBullets()) {
			ret.put(b.getPosition(), b.getDirection());
		}
		
		return ret;
	}
}
