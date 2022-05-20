package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import app.MetalShot;
import model.StageImpl;
import model.character.Character;
import controller.map.MapController;
import controller.player.PlayerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import util.Direction;
import util.Vector2D;
import util.map.TextMap;

/**
 * 
 *
 */
public class Controller {

    private final PlayerController playerController;
    private final MapController mapController;
    private final StageImpl stage;
    private BulletsController bulletsController;
    private WeaponController weaponController;
    private final Timeline gameLoop;
    private static final double FPS = 1000;

    // Instance of model (Stage?)
    private final MetalShot viewReference;

    /**
     * The main controller constructor.
     * 
     * @param viewReference
     * @throws IOException if the text map is not present
     */
    public Controller(final MetalShot viewReference) throws IOException {
    	
        final TextMap textMap = new TextMap("/home/andrea/Documenti/Università/ilProggetto/progetto/OOP21-Mtl-Shot/bin/main/map.txt"/*ClassLoader.getSystemResource("map.txt").getPath()*/);
        this.stage = new StageImpl(textMap);
        this.mapController = new MapController(this.stage.getMapModel());
        this.viewReference = viewReference;
        this.bulletsController = new BulletsController(this.stage.getPlayer(), this.stage.getBullets2(), null);
        this.weaponController = new WeaponController(this);
        this.playerController = new PlayerController(this.viewReference.getPlayerView(), this, this.stage.getPlayer()); // null ->
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
                playerController.check();
                viewReference.refresh(stage);
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
            playerController.getPlayer().getAim().setDirection(Direction.LEFT);
        }
        if (key == KeyCode.D) {
            playerController.getPlayer().setRight(true);
            playerController.getPlayer().getAim().setDirection(Direction.RIGHT);
        }
        if (key == KeyCode.W) {
            playerController.getPlayer().getAim().setDirection(Direction.UP);
        }
        if (key == KeyCode.SPACE) {
            playerController.getPlayer().setJump(true);
        }
        if (key == KeyCode.S) {
            playerController.getPlayer().setCrouchKey(true);
            playerController.getPlayer().getAim().setDirection(Direction.DOWN);
        }
        if (key.equals(KeyCode.J)) {
            if (this.weaponController.tryToShoot(this.stage.getPlayer())) {
                this.bulletsController.addBullet(this.stage.getPlayer());
                System.out.println("Shooting...");
            }
        } else if (key.equals(KeyCode.R)) {
            this.playerController.getPlayer().getWeapon().reload();
        }
    }

    /**
     * 
     * @param key
     */
    public void keyReleased(final KeyCode key) {
        if (key == KeyCode.A) {
            playerController.getPlayer().setLeft(false);
        }
        if (key == KeyCode.D) {
            playerController.getPlayer().setRight(false);
        }
        if (key == KeyCode.W) {
            playerController.getPlayer().getAim().returnToHorizontal();
        }
        if (key == KeyCode.SPACE) {
            playerController.getPlayer().setJump(false);
        }
        if (key == KeyCode.S) {
            playerController.getPlayer().setCrouchKey(false);
            playerController.getPlayer().getAim().returnToHorizontal();
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
     * 
     * @return MapController
     */
    public MapController getMapController() {
        return this.mapController;
    }

    /**
     * Gets the class that handle the player control.
     * 
     * @return PlayerController
     */
    public PlayerController getPlayerController() {
        return this.playerController;
    }

    /**
     * Gets the view reference.
     * 
     * @return MetalShot
     */
    public MetalShot getView() {
        return this.viewReference;
    }

    /**
     * Returns every Character (the player, enemies, ...) currently in game.
     * 
     * @return a Set of characters
     */
    public Set<Character> getAllCharacters() {
        // TODO
        final var set = new HashSet<Character>();
        set.add(playerController.getPlayer());
        return set;
    }
}
