package controller;

import controller.enemy.EnemyController;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.management.InstanceNotFoundException;

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
import util.map.TextMap;
import view.GameView;

/**
 * The main controller. It contains all sub-controllers and it manages the game loop.
 */
public class Controller {
    private final PlayerController playerController;
    private final Collection<EnemyController> enemiesController;
    private final BulletsController bulletsController;
    private final WeaponController weaponController;
    private final StageImpl stage;
    private final Timeline gameLoop;
    private boolean paused;
    /**
     * Ticks per second. A unit that represent how many steps are calculated in a
     * second.
     */
    public static final double TPS = 60;

    private final GameView viewReference;

    /**
     * The main controller constructor.
     * 
     * @param gameView
     * @throws IOException if the text map is not present
     * @throws InstanceNotFoundException if player spawn is not set in any text map
     */
    public Controller(final GameView gameView) throws IOException, InstanceNotFoundException {
        final TextMap textMap = new TextMap(ClassLoader.getSystemResource("map.txt").getPath());
        this.stage = new StageImpl(textMap);
        this.viewReference = gameView;
        this.enemiesController = new LinkedList<>();
        this.bulletsController = new BulletsController(this.stage.getPlayer(), this.stage.getBullets(), this.stage.getEnemies());
        this.weaponController = new WeaponController();
        this.playerController = new PlayerController(this.stage.getLevel(),
                this.stage.getPlayer());

        this.stage.getEnemies().forEach(e -> enemiesController.add(new EnemyController(this.stage.getLevel(), e)));
        System.out.println(enemiesController.size());

        for (EnemyController enemyController : this.enemiesController) {
            enemyController.getBrain().setPlayer(this.stage.getPlayer());
        }
        this.gameLoop = new Timeline(new KeyFrame(Duration.seconds(1 / TPS), new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                // TODO implement game loop here

                // Move player
                // Jumping and falling included
                // Shoot (player)
                // Move/shoot enemies (based on Susca's AI)
                // Check for colliding bullets
                for (EnemyController enemyController : enemiesController) {
                    enemyController.controllerTick();
                    if(enemyController.isDead()) {
                        enemiesController.remove(enemyController);
                        stage.getEnemies().remove(enemyController.getCharacter());
                    }
                }
                weaponController.controllerTick();
                bulletsController.controllerTick();
                playerController.controllerTick();
                if (!viewReference.getWindow().isFocused()) {
                    stage.getPlayer().reset();
                }
                gameView.refresh(stage);
            }
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts the game loop.
     */
    public void gameStart() {
        gameLoop.play();
        paused = false;
    }

    /**
     * Pause the game loop and display the pause menu.
     * 
     * @throws IOException if the fxml sheet doesn't exist.
     */
    public void gamePause() throws IOException {
        if (!paused) {
            gameLoop.pause();
            this.viewReference.displayPauseMenu();
        }
        paused = true;
    }

    /**
     * Handles the input key from standard input on it's press.
     * 
     * @param key the key pressed
     * @throws IOException if the pause menu fxml sheet doesn't exist.
     */
    public void keyPressed(final KeyCode key) throws IOException {
        if (key == KeyCode.A) {
            stage.getPlayer().setLeft(true);
            stage.getPlayer().getAim().setDirection(Direction.LEFT);
        }
        if (key == KeyCode.D) {
            stage.getPlayer().setRight(true);
            stage.getPlayer().getAim().setDirection(Direction.RIGHT);
        }
        if (key == KeyCode.W) {
            stage.getPlayer().getAim().setDirection(Direction.UP);
        }
        if (key == KeyCode.SPACE) {
            stage.getPlayer().setJump(true);
        }
        if (key == KeyCode.S) {
            stage.getPlayer().setCrouchKey(true);
            stage.getPlayer().getAim().setDirection(Direction.DOWN);
        }
        if (key.equals(KeyCode.J)) {
            if (this.weaponController.tryToShoot(this.stage.getPlayer())) {
                // Play shoot sound
                this.bulletsController.addBullet(this.stage.getPlayer());
                //System.out.println("Shooting...");
            }
        } else if (key.equals(KeyCode.R)) {
             if (this.weaponController.tryToReload(this.stage.getPlayer())) {
                 // Play reload sound
                 // Play reload animation
             }
        }
        if (key == KeyCode.ESCAPE) {
            this.gamePause();
        }
    }

    /**
     * Handles the input key from standard input on it's release.
     * 
     * @param key the key released
     */
    public void keyReleased(final KeyCode key) {
        if (key == KeyCode.A) {
            stage.getPlayer().setLeft(false);
        }
        if (key == KeyCode.D) {
            stage.getPlayer().setRight(false);
        }
        if (key == KeyCode.W) {
            stage.getPlayer().getAim().returnToHorizontal();
        }
        if (key == KeyCode.SPACE) {
            stage.getPlayer().setJump(false);
        }
        if (key == KeyCode.S) {
            stage.getPlayer().setCrouchKey(false);
            stage.getPlayer().getAim().returnToHorizontal();
        }
    }

    /**
     * Saves the current game state.
     */
    public void save() {
        // TODO
    }

    /**
     * Loads the selected game.
     */
    public void load() {
        // TODO
    }

    /**
     * Loads the next level.
     */
    public void nextLevel() {
        // TODO
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

    public StageImpl getStage() {
        return this.stage;
    }
}
