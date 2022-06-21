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
import controller.player.PlayerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import util.DirectionHorizontal;
import util.DirectionVertical;
import util.map.TextMap;
import view.GameView;

/**
 * The main controller. It contains all sub-controllers and it manages the game
 * loop.
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
     * @throws IOException               if the text map is not present
     * @throws InstanceNotFoundException if player spawn is not set in any text map
     */
    public Controller(final GameView gameView) throws IOException, InstanceNotFoundException {
        final TextMap textMap = new TextMap(ClassLoader.getSystemResource("map.txt").getPath());
        this.stage = new StageImpl(textMap);
        this.viewReference = gameView;
        this.enemiesController = new LinkedList<>();
        this.bulletsController = new BulletsController(this.stage.getPlayer(),
        		this.stage.getBullets(),
        		this.stage.getEnemies(),
        		this.stage.getLevel());
        this.weaponController = new WeaponController();
        this.playerController = new PlayerController(this.stage.getLevel(), this.stage.getPlayer());
        this.stage.getEnemies().forEach(e -> enemiesController.add(new EnemyController(this.stage.getLevel(), e)));
        for (final EnemyController enemyController : this.enemiesController) {
            enemyController.getBrain().setPlayer(this.stage.getPlayer());
        }

        refreshEnemiesStatus();

        this.gameLoop = new Timeline(new KeyFrame(Duration.seconds(1 / TPS), new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {

                var remove = new LinkedList<EnemyController>();

                enemiesController.forEach(e -> {
                    if (e.isActive()) {
                        e.controllerTick(viewReference.getCameraManager().getBounds(), false);
                        if(e.getCharacter().isShooting()) {
//                            e.fire(weaponController, bulletsController);
                        }
                        if (e.isDead()) {
                            remove.add(e);
                        }
                    }
                });

                if(!remove.isEmpty()) {
                    remove.forEach(e -> removeEnemy(e));
                }

                weaponController.controllerTick();
                bulletsController.controllerTick();
                playerController.controllerTick(viewReference.getCameraManager().getBounds(),
						stage.getEnemies().stream()
								.filter(t -> stage.getLevel().getSegmentAtPosition(stage.getPlayer().getPosition())
										.equals(stage.getLevel().getSegmentAtPosition(t.getPosition())))
								.collect(Collectors.toSet()).isEmpty());
                if(playerController.getCharacter().isShooting()) {
                    playerController.fire(weaponController, bulletsController);
                }
                
                if(playerController.isDead()) {
                    try {
                        gamePause();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                
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
            stage.getPlayer().setJump(true);
        }
        if (key == KeyCode.S) {
            stage.getPlayer().setCrouchKey(true);
            stage.getPlayer().getAim().setVertical(DirectionVertical.DOWN);
        }
        if (key.equals(KeyCode.J)) {
            this.stage.getPlayer().setFire(true);
//            this.playerController.fire(weaponController, bulletsController);
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
        if (key == KeyCode.J) {
            this.stage.getPlayer().setFire(false);
//            this.playerController.fire(weaponController, bulletsController);
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
     * Loads the next for (final EnemyController enemyController : enemiesController) {
                    enemyController.controllerTick();
                    if(enemyController.isDead()) {
                        enemiesController.remove(enemyController);
                        stage.getEnemies().remove(enemyController.getCharacter());
                    }
                }
                welevel.
     */
    public void nextLevel() {
        // TODO
    }

    /**
     * Gets the class that handle the p    
    public void setFire(boolean b) {
        this.isShooting = b;
    }
    
    public boolean isShooting() {
        return this.isShooting;
    }layer control.
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
        final var set = new HashSet<Character>();
        set.add(stage.getPlayer());
        return set;
    }

    /**
     * Gets the main stage.
     * @return StageImpl
     */
    public StageImpl getStage() {
        return this.stage;
    }

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

    public void removeOldEnemies() {
        var remove = new LinkedList<EnemyController>();
        
        enemiesController.forEach(e -> {
            if (!e.isActive()
                    && stage.getLevel().getSegmentAtPosition(e.getCharacter().getPosition()).getOrigin().getX() < stage
                            .getLevel().getSegmentAtPosition(stage.getPlayer().getPosition()).getOrigin().getX()) {
                remove.add(e);
            }
        });
        
        if(!remove.isEmpty()) {
            remove.forEach(e -> removeEnemy(e));
        }
    }

	private void removeEnemy(final EnemyController enemyController) {
	    enemiesController.remove(enemyController);
	    stage.getEnemies().remove(enemyController.getCharacter());
	}
}
