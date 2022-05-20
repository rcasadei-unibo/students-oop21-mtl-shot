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
    private final StageImpl stage;
    private BulletsController bulletsController;
    private WeaponController weaponController;
    private final Timeline gameLoop;
    public static final double TPS = 60;
    private final MetalShot viewReference;

    /**
     * The main controller constructor.
     * 
     * @param viewReference
     * @throws IOException if the text map is not present
     */
    public Controller(final MetalShot viewReference) throws IOException {
        final TextMap textMap = new TextMap(ClassLoader.getSystemResource("map.txt").getPath());
        this.stage = new StageImpl(textMap);
        this.viewReference = viewReference;
        this.bulletsController = new BulletsController(this);
        this.weaponController = new WeaponController(this);
        this.playerController = new PlayerController(this.stage.getPlayer(), this.stage.getLevel(), this.viewReference.getPlayerView());
        this.gameLoop = new Timeline(new KeyFrame(Duration.seconds(1 / TPS), new EventHandler<ActionEvent>() {

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
                playerController.controllerTick();
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

    /**
     * Handle the input key from standard input on it's press.
     * @param key
     */
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
            if (this.weaponController.tryToShoot(this.playerController.getPlayer())) {
                this.bulletsController.addBullet(this.playerController.getPlayer());
                System.out.println("Shooting...");
            }
        } else if (key.equals(KeyCode.R)) {
            this.playerController.getPlayer().getWeapon().reload();
        }
    }

    /**
     * Handle the input key from standard input on it's release.
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
     * Gets the class that handles the player control.
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

    /**
     * Returns a map where every entry represents a bullet's position and direction.
     * 
     * @return a Map
     */
    public Map<Vector2D, Direction> getBullets() {
        final Map<Vector2D, Direction> ret = new HashMap<>();
        for (final var b : this.bulletsController.getBullets()) {
            ret.put(b.getPosition(), b.getDirection());
        }
        return ret;
    }
    
    public StageImpl getStage() {
    	return this.stage;
    }
}
