package controller;

import controller.enemy.BasicBot;
import controller.enemy.EnemyController;
import controller.enemy.SimpleBot;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.StageImpl;
import model.character.Character;
import controller.map.MapController;
import controller.player.PlayerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;


import util.Direction;
import util.Vector2D;
import util.map.TextMap;
import view.GameView;

/**
 * 
 *
 */
public class Controller {

    private final PlayerController playerController;
	private final EnemyController enemyController;
    private final MapController mapController;
    private final StageImpl stage;
    private BulletsController bulletsController;
    private WeaponController weaponController;
    private final Timeline gameLoop;
    public static final double TPS = 60;

    // Instance of model (Stage?)
    private final GameView viewReference;

    /**
     * The main controller constructor.
     * 
     * @param gameView
     * @throws IOException if the text map is not present
     */
    public Controller(final GameView gameView) throws IOException {
        final TextMap textMap = new TextMap(ClassLoader.getSystemResource("map.txt").getPath());
        this.stage = new StageImpl(textMap);
        this.mapController = new MapController(this.stage.getMapModel());
        this.viewReference = gameView;
        this.bulletsController = new BulletsController(this);
        this.weaponController = new WeaponController(this);
        this.playerController = new PlayerController(this.viewReference.getPlayerView(), this.getMapController(), this.stage.getPlayer()); // null ->
                                                                                                // player view

        this.enemyController = new EnemyController(this.viewReference.getEnemyView(), this.getMapController(), this.stage.getEnemy());
        this.enemyController.getBrain().setPlayer(this.stage.getPlayer());
        //SBAGLIATO, SOLO TEMPORANEO!!!!
//  		SimpleBot brain = new BasicBot();
//  		brain.getEntity().setPosition(25, 0);
//  		brain.setPlayer(stage.getPlayer());
        this.gameLoop = new Timeline(new KeyFrame(Duration.seconds(1 / TPS), new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                // TODO implement game loop here

                // Move player
                // Jumping and falling included
                // Shoot (player)
                // Move/shoot enemies (based on Susca's AI)
                // Check for colliding bullets
            	enemyController.brainTick();
            	enemyController.controllerTick();
				System.out.println("Entity POS: " + stage.getEnemy().getPosition());
                weaponController.controllerTick();
                bulletsController.controllerTick();
                gameView.displayBullets(getBullets());
                playerController.controllerTick();
                gameView.refresh(stage);
            }
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
    }

    public void gameStart() {
        gameLoop.play();
        // TODO
    }

    public void gamePause() throws IOException { // not in UML
        gameLoop.pause();
        final Parent root = FXMLLoader.load(getClass().getResource("/fxml/PauseMenu.fxml"));
        final Scene scene = new Scene(root);
        this.viewReference.getStage().setScene(scene);
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
            playerController.getCharacter().setLeft(true);
            playerController.getCharacter().getAim().setDirection(Direction.LEFT);
        }
        if (key == KeyCode.D) {
            playerController.getCharacter().setRight(true);
            playerController.getCharacter().getAim().setDirection(Direction.RIGHT);
        }
        if (key == KeyCode.W) {
            playerController.getCharacter().getAim().setDirection(Direction.UP);
        }
        if (key == KeyCode.SPACE) {
            playerController.getCharacter().setJump(true);
        }
        if (key == KeyCode.S) {
            playerController.getCharacter().setCrouchKey(true);
            playerController.getCharacter().getAim().setDirection(Direction.DOWN);
        }
        if (key.equals(KeyCode.J)) {
            if (this.weaponController.tryToShoot(this.playerController.getCharacter())) {
                this.bulletsController.addBullet(this.playerController.getCharacter());
                System.out.println("Shooting...");
            }
        } else if (key.equals(KeyCode.R)) {
            this.playerController.getCharacter().getWeapon().reload();
        }
    }

    /**
     * Handle the input key from standard input on it's release.
     * @param key
     * @throws IOException 
     */
    public void keyReleased(final KeyCode key) throws IOException {
        if (key == KeyCode.A) {
            playerController.getCharacter().setLeft(false);
        }
        if (key == KeyCode.D) {
            playerController.getCharacter().setRight(false);
        }
        if (key == KeyCode.W) {
            playerController.getCharacter().getAim().returnToHorizontal();
        }
        if (key == KeyCode.SPACE) {
            playerController.getCharacter().setJump(false);
        }
        if (key == KeyCode.S) {
            playerController.getCharacter().setCrouchKey(false);
            playerController.getCharacter().getAim().returnToHorizontal();
        }
        if (key == KeyCode.ESCAPE) {
            this.gamePause();
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
    public GameView getView() {
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
        set.add(playerController.getCharacter());
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
}
