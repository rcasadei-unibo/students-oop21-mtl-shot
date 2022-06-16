package controller;

import controller.enemy.EnemyController;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import model.StageImpl;
import model.character.Character;
import model.character.Enemy;
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
import view.EnemyView;
import view.GameView;

/**
 * The main controller. It contains all sub-controllers and it manages the game loop.
 */
public class Controller {
    private final PlayerController playerController;
	//private final EnemyController enemyController;
    private final Collection<EnemyController> enemiesController;
    private final StageImpl stage;
    private final BulletsController bulletsController;
    private final WeaponController weaponController;
    private final Timeline gameLoop;
	private boolean paused;
	/**
	 * Ticks per second. A unit that represent how many steps are calculated in a
	 * second.
	 */
	public static final double TPS = 120;

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
		this.bulletsController = new BulletsController(this);
		this.weaponController = new WeaponController(this);
		this.playerController = new PlayerController(this.stage.getLevel(),
				this.stage.getPlayer());
		
        //this.enemyController = new EnemyController(this.viewReference.getEnemyView(), this.stage.getLevel(), this.stage.getEnemy());
        //this.enemyController.getBrain().setPlayer(this.stage.getPlayer());
        
        this.stage.getEnemies().forEach(e -> enemiesController.add(new EnemyController(this.stage.getLevel(), e)));
        System.out.println(enemiesController.size());
		
		/*for(Map.Entry<Enemy, EnemyView> enemy : this.viewReference.getEnemiesView().entrySet()) {
		    System.out.println("cisono");
		    enemiesController.add(new EnemyController(enemy.getValue(), this.stage.getLevel(), enemy.getKey()));
		}*/
		
		for(EnemyController enemyController : this.enemiesController) {
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
            	//enemyController.brainTick();
            	//enemyController.controllerTick();
                for(EnemyController enemyController : enemiesController) {
                    //enemyController.brainTick();
                    enemyController.controllerTick();
                }
                weaponController.controllerTick();
                bulletsController.controllerTick();
                playerController.controllerTick();                
                if (!viewReference.getWindow().isFocused()) {
                    stage.getPlayer().reset();
                }
                //TODO: da sistemare
                gameView.displayBullets(getBullets());
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
            if (this.weaponController.tryToShoot(stage.getPlayer())) {
                this.bulletsController.addBullet(stage.getPlayer());
                System.out.println("Shooting...");
            }
        } else if (key.equals(KeyCode.R)) {
            stage.getPlayer().getWeapon().reload();
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
