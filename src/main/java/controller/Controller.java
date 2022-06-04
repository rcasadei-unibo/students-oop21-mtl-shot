package controller;

import controller.enemy.EnemyController;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import view.GameView;

/**
 * 
 *
 */
public class Controller {
    private final PlayerController playerController;
	private final EnemyController enemyController;
    private final StageImpl stage;
    private BulletsController bulletsController;
    private WeaponController weaponController;
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
	 */
	public Controller(final GameView gameView) throws IOException {
		final TextMap textMap = new TextMap(ClassLoader.getSystemResource("map.txt").getPath());
		this.stage = new StageImpl(textMap);
		this.viewReference = gameView;
		this.bulletsController = new BulletsController(this);
		this.weaponController = new WeaponController(this);
		this.playerController = new PlayerController(this.viewReference.getPlayerView(), this.stage.getLevel(),
				this.stage.getPlayer());
		
        this.enemyController = new EnemyController(this.viewReference.getEnemyView(), this.stage.getLevel(), this.stage.getEnemy());
        this.enemyController.getBrain().setPlayer(this.stage.getPlayer());
        
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

	public void gameStart() {
		gameLoop.play();
		paused = false;
	}

	public void gamePause() throws IOException {
		if (!paused) {
			gameLoop.pause();
			this.viewReference.displayPauseMenu();
		}
		paused = true;
	}

	/**
	 * Handle the input key from standard input on it's press.
	 * 
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
	 * 
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
		if (key == KeyCode.P) {
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
