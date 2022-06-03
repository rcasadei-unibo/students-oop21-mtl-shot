package view;

import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.Controller;
import controller.menu.PauseMenuController;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import model.StageImpl;
import util.Direction;
import util.UserData;
import util.Vector2D;
import util.map.MapConstants;
import view.map.LevelView;
import view.player.PlayerView;

/**
 * The game main view. It contains all sub-views and handles the view refresh.
 * 
 */
public class GameView extends Scene {

	//public static final int VIEWRESIZE = 2;
	private final PlayerView playerView = new PlayerView();
	private final BulletsView bulletsView = new BulletsView();
	private final LevelView levelView;
	private final ImageView background = new ImageView(new Image(new FileInputStream("src/main/resources/menusResources/MainMenuBG.png")));
	private final ImageView enemy;

	private final Controller controller = new Controller(this);
	private final UserData userData;

	private final Group root;
	private Vector2D prevPos;
	private final Camera camera = new PerspectiveCamera();
	/**
	 * The GameView constructor.
	 * 
	 * @param username
	 * @throws IOException
	 */
	public GameView(final String username) throws IOException {
		super(new Group());
		this.userData = new UserData(username);
		this.levelView = new LevelView(this.controller.getStage().getLevel());
		final List<Node> totalList = new ArrayList<>();
		prevPos = new Vector2D(controller.getStage().getPlayer().getPosition());
		totalList.add(background);
		totalList.addAll(levelView.displaySegments(controller.getStage().getPlayer().getPosition()));
		totalList.add(playerView.getPlayerImageView());
		this.enemy = new ImageView(new Image(new FileInputStream("src/main/resources/person2.png")));
		totalList.add(enemy);
		this.root = new Group(totalList);
		this.setRoot(root);
		//this.setCamera(camera);
		controller.gameStart();
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(final KeyEvent event) {
				controller.keyPressed(event.getCode());
			}
		});
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(final KeyEvent event) {
				try {
					controller.keyReleased(event.getCode());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		this.setOnKeyTyped(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent event) {
			}
		});
	}

	/**
	 * TODO: Andrea Biagini.
	 * 
	 * @param bullets
	 */
	public void displayBullets(final Map<Vector2D, Direction> bullets) {
		this.root.getChildren().removeAll(this.bulletsView.getImageViewList());
		this.bulletsView.updateBullets(bullets.keySet().stream().collect(Collectors.toList()));
		this.root.getChildren().addAll(this.bulletsView.getImageViewList());
	}

	/**
	 * Gets the visible part of the player.
	 * 
	 * @return PlayerView
	 */
	public PlayerView getPlayerView() {
		return this.playerView;
	}

	public LevelView getLevelView() {
		return this.levelView;
	}

	public BulletsView getBulletsView() {
		return this.bulletsView;
	}

	/**
	 * TODO: Matteo Susca.
	 * 
	 * @param pos
	 */
	public void setEnemyPos(final Vector2D pos) {
		this.enemy.setX(pos.getX() * MapConstants.getTilesize());
	}

	/**
	 * Updates the current visual frame using the info of the stage.
	 * 
	 * @param stage
	 */
	public void refresh(final StageImpl stage) {
		playerView.updateCharacter(stage.getPlayer().getPosition(), stage.getPlayer().isCrouching(),
				stage.getPlayer().getAim().getDirection());

		if (!stage.getLevel().getSegmentAtPosition(stage.getPlayer().getPosition()).equals(stage.getLevel().getSegmentAtPosition(prevPos))) {
			prevPos = new Vector2D(stage.getPlayer().getPosition());
			this.root.getChildren().removeAll(levelView.getDisplayed());
			this.root.getChildren().addAll(levelView.displaySegments(stage.getPlayer().getPosition()));
			TranslateTransition tt = new TranslateTransition(Duration.millis(1000), this.root);
			tt.setToX(new Vector2D(stage.getLevel().getSegmentAtPosition(stage.getPlayer().getPosition()).getOrigin()).getX() * MapConstants.getTilesize() * -1);
			ParallelTransition pt = new ParallelTransition();
			pt.getChildren().add(tt);
			pt.play();
		}
	}

	public Controller getController() {
		return this.controller;
	}

	public UserData getUserData() {
		return this.userData;
	}

	public void displayPauseMenu() throws IOException {
		final Group group = new Group(root);
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PauseMenu.fxml"));
		group.getChildren().add(loader.load());
		final PauseMenuController pmc = (PauseMenuController) loader.getController();
		pmc.setSize(this.getWidth(), this.getHeight());
		pmc.setGameView(this);
		this.setRoot(group);
	}

	public void disposePauseMenu() {
		final Group group = new Group(root);
		this.setRoot(group);
		this.controller.gameStart();
	}
}
