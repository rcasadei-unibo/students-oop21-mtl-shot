package controller;

import controller.enemy.EnemyController;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;

import model.StageImpl;
import model.character.Character;
import model.weapons.Kraber;
import model.weapons.PeaceKeeper;
import model.weapons.R99;
import controller.player.PlayerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Pair;
import util.direction.DirectionHorizontal;
import util.direction.DirectionVertical;
import view.GameView;
import view.sounds.SoundManager.Sounds;

/**
 * The main controller. It contains all sub-controllers and it manages the game
 * loop.
 */
public class Controller extends Thread {

    private final PlayerController playerController;
    private final Collection<EnemyController> enemiesController;
    private final BulletsController bulletsController;
    private final WeaponController weaponController;
    private final SoundsController soundsController;
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
     * @param userName
     * @param primaryStage
     * @throws IOException               if the text map is not present
     * @throws InstanceNotFoundException if player spawn is not set in any text map
     */
    public Controller(final String userName, final Stage primaryStage) throws InstanceNotFoundException, IOException {
        this.stage = new StageImpl();
        final boolean fs = primaryStage.isFullScreen();
        final var dim = new Pair<>(primaryStage.getWidth(), primaryStage.getHeight());
        this.viewReference = new GameView(userName, this);
        primaryStage.setScene(viewReference);
        primaryStage.setFullScreen(fs);
        primaryStage.setWidth(dim.getX());
        primaryStage.setHeight(dim.getY());
        this.enemiesController = new LinkedList<>();
        this.weaponController = new WeaponController();
        this.playerController = new PlayerController(this.stage.getLevel(), this.stage.getPlayer());
        this.soundsController = new SoundsController();
        this.bulletsController = new BulletsController(this.stage.getPlayer(), this.stage.getBullets(),
                this.stage.getEnemies(), this.soundsController, this.stage.getLevel());
        this.stage.getEnemies().forEach(
                e -> enemiesController.add(new EnemyController(this.stage.getLevel(), e, this.stage.getPlayer())));
        this.paused = false;

        refreshEnemiesStatus();

        this.gameLoop = new Timeline(new KeyFrame(Duration.seconds(1 / TPS), new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (paused) {
                    viewReference.menuRefresh();
                } else {
                    var remove = new LinkedList<EnemyController>();

                    enemiesController.forEach(e -> {
                        if (e.isActive()) {
                            e.controllerTick(viewReference.getCameraManager().getBounds(), false);
                            if (e.getCharacter().isShooting()) {
                                 e.fire(weaponController, bulletsController, soundsController);
                            }
                            if (e.isDead()) {
                                remove.add(e);
                                viewReference.getUserData().increasePoints();
                            }
                        }
                    });

                    if (!remove.isEmpty()) {
                        remove.forEach(e -> removeEnemy(e));
                    }

                    weaponController.controllerTick();
                    bulletsController.controllerTick();

                    playerController.controllerTick(viewReference.getCameraManager().getBounds(),
                            stage.getEnemies().stream()
                                    .filter(t -> stage.getLevel().getSegmentAtPosition(stage.getPlayer().getPosition())
                                            .equals(stage.getLevel().getSegmentAtPosition(t.getPosition())))
                                    .collect(Collectors.toSet()).isEmpty());
                    if (playerController.getCharacter().isShooting()) {
                        playerController.fire(weaponController, bulletsController, soundsController);
                    }

                    if (playerController.isDead()) {
                        gameOver();
                    }

                    soundsController.controllerTick();

                    if (stage.getLevel().getSegmentAtPosition(stage.getPlayer().getPosition())
                            .equals(stage.getLevel().getSegments().get(stage.getLevel().getSegments().size() - 1))) {

                        viewReference.displayWinMenu();
                        gameLoop.pause();

                    }

                    viewReference.refresh(stage);
                }
            }
        }));
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);
        this.gameLoop.play();
        gameStart();
    }

    /**
     * Starts the game loop.
     */
    public void gameStart() {
        this.soundsController.forcePlaySound(Sounds.MAIN_THEME);
        this.paused = false;
    }

    /**
     * Pause the game loop and display the pause menu.
     */
    public void gamePause() {
        this.soundsController.stopSound(Sounds.MAIN_THEME);
        this.paused = true;
        this.viewReference.displayPauseMenu();
    }

    /**
     * 
     */
    public void gameOver() {
        this.gameLoop.pause();
        this.viewReference.displayGameOverMenu();
    }

    /**
     * Handles the input key from standard input on it's press.
     * 
     * @param key the key pressed
     * @throws IOException if the pause menu fxml sheet doesn't exist.
     */
    public void keyPressed(final KeyCode key) {
        if (key == KeyCode.A) {
            stage.getPlayer().setLeft(true);
            stage.getPlayer().getAim().setHorizontal(DirectionHorizontal.LEFT);
        }
        if (key == KeyCode.D) {
            stage.getPlayer().setRight(true);
            stage.getPlayer().getAim().setHorizontal(DirectionHorizontal.RIGHT);
        }
        if (key == KeyCode.W) {
            stage.getPlayer().getAim().setVertical(DirectionVertical.UP);
        }
        if (key == KeyCode.SPACE) {
            if (!stage.getPlayer().isJumping() && !stage.getPlayer().isFalling()) {
                this.soundsController.playSound(Sounds.JUMP_1);
            }
            stage.getPlayer().setJump(true);
        }
        if (key == KeyCode.S) {
            stage.getPlayer().setCrouchKey(true);
            stage.getPlayer().getAim().setVertical(DirectionVertical.DOWN);
        }
        if (key.equals(KeyCode.J)) {
            this.stage.getPlayer().setFire(true);
        }
        if (key == KeyCode.ESCAPE) {
            this.gamePause();
        }
        if (key == KeyCode.DIGIT1) {
            this.stage.getPlayer().setWeapon(new R99());
        }
        if (key == KeyCode.DIGIT2) {
            this.stage.getPlayer().setWeapon(new PeaceKeeper());
        }
        if (key == KeyCode.DIGIT3) {
            this.stage.getPlayer().setWeapon(new Kraber());
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
        if (key == KeyCode.J) {
            this.stage.getPlayer().setFire(false);
        }
    }

    /**
     * Gets the class that handle the p public void setFire(boolean b) {
     * this.isShooting = b; }
     * 
     * public boolean isShooting() { return this.isShooting; }layer control.
     * 
     * @return PlayerController
     */
    public PlayerController getPlayerController() {
        return this.playerController;
    }

    /**
     * Returns every Character (the player, enemies, ...) currently in game.
     * 
     * @return a Set of characters
     */
    public Set<Character> getAllCharacters() {
        final var set = new HashSet<Character>();
        set.add(stage.getPlayer());
        return set;
    }

    /**
     * Gets the main stage.
     * 
     * @return StageImpl
     */
    public StageImpl getStage() {
        return this.stage;
    }

    /**
     * Pause the enemies that are in a segment different from the Player segment.
     */
    public void refreshEnemiesStatus() {
        enemiesController.forEach(e -> {
            if (stage.getLevel().getSegmentAtPosition(e.getCharacter().getPosition()) != stage.getLevel()
                    .getSegmentAtPosition(stage.getPlayer().getPosition())) {
                e.setActive(false);
            } else {
                e.setActive(true);
            }
        });
    }

    private void removeEnemy(final EnemyController enemyController) {
        enemiesController.remove(enemyController);
        stage.getEnemies().remove(enemyController.getCharacter());
    }

}
