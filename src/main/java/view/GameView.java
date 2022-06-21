package view;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import model.StageImpl;
import model.character.Enemy;
import view.map.CameraManager;
import view.map.LevelView;
import view.player.PlayerView;
import controller.Controller;
import controller.menu.PauseMenuController;

import util.UserData;

/**
 * The game main view. It contains all sub-views and handles the view refresh.
 * 
 */
public class GameView extends Scene {
    
    private final PlayerView playerView = new PlayerView();
    private final Map<Enemy, EnemyView> enemiesView = new HashMap<>();
    private final BulletsView bulletsView = new BulletsView(1);
    private final LevelView levelView;
    private final ImageView background = new ImageView(new Image(new FileInputStream("src/main/resources/menusResources/MainMenuBG.png")));
    private final Controller controller = new Controller(this);
    private final UserData userData;
	private final Group root;
	private final CameraManager cameraManager;

	/**
	 * The GameView constructor.
	 * 
	 * @param username
	 * @throws IOException
	 */
	public GameView(final String username) throws IOException, InstanceNotFoundException {
		super(new Group());
		this.userData = new UserData(username);
		this.levelView = new LevelView(this.controller.getStage().getLevel());
		final List<Node> totalList = new ArrayList<>();
		totalList.add(background);
		totalList.addAll(levelView.displaySegments(controller.getStage().getPlayer().getPosition()));
		totalList.add(playerView.getCharacterImageView());

		for(Enemy enemy : controller.getStage().getEnemies()) {
		    enemiesView.put(enemy, new EnemyView());		    
		}
		for(EnemyView enemyView : this.enemiesView.values()) {
		    totalList.add(enemyView.getCharacterImageView());
		}
		this.root = new Group(totalList);
		this.setRoot(root);
		this.cameraManager = new CameraManager(controller, root, levelView);
		this.setCamera(cameraManager.getCamera());
		controller.gameStart();

		this.setOnKeyPressed(e -> {

            try {
                controller.keyPressed(e.getCode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.setOnKeyReleased(e -> controller.keyReleased(e.getCode()));
        this.setOnKeyTyped(e -> {
        });
    }

    /**
     * Gets the visible part of the player.
     * 
     * @return PlayerView
     */
    public PlayerView getPlayerView() {
        return this.playerView;
    }

    /**
     * Gets the visible part of the map.
     * 
     * @return LevelView
     */
    public LevelView getLevelView() {
        return this.levelView;
    }

    /**
     * Gets the visible part of the bullets.
     * 
     * @return BulletsView.
     */
    public BulletsView getBulletsView() {
        return this.bulletsView;
    }

	/**
	 * Updates the current visual frame using the info of the stage.
	 * 
	 * @param stage
	 */
    public void refresh(final StageImpl stage) {

        cameraManager.updateCamera();

        List<EnemyView> removable = new LinkedList<>();
        enemiesView.forEach((k, v) -> {
            if (!stage.getEnemies().contains(k)) {
                removable.add(enemiesView.get(k));
            }
        });

        List<ImageView> remove = new LinkedList<>();
        removable.forEach(e -> {
            enemiesView.remove(e);
            remove.add(e.getCharacterImageView());
        });

        this.root.getChildren().removeAll(remove);

        for (Enemy enemy : stage.getEnemies()) {
            enemiesView.get(enemy).updateCharacter(enemy);
        }

        playerView.updateCharacter(stage.getPlayer());

        // Updates bullets
        if (stage.getBullets().size() != this.bulletsView.getImageViewList().size()) {
            this.root.getChildren().removeAll(this.bulletsView.getImageViewList());
            this.bulletsView
                    .updateBullets(stage.getBullets().stream().map(b -> b.getPosition()).collect(Collectors.toList()));
            this.root.getChildren().addAll(this.bulletsView.getImageViewList());
        } else {
            this.bulletsView
                    .updateBullets(stage.getBullets().stream().map(b -> b.getPosition()).collect(Collectors.toList()));
        }
	}

    /**
     * Gets the data of the person who's playing Metal Shot.
     * 
     * @return UserData
     */
    public UserData getUserData() {
        return this.userData;
    }

    /**
     * Display the pause menu.
     * 
     * @throws IOException if the fxml sheet doesn't exist.
     */
    public void displayPauseMenu() throws IOException {
        final Group group = new Group(root);
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PauseMenu.fxml"));
        group.getChildren().add(loader.load());
        final PauseMenuController pmc = (PauseMenuController) loader.getController();
        pmc.setSize(this.getWidth(), this.getHeight());
        pmc.setGameView(this);
        this.setRoot(group);
    }

    /**
     * Dispose of the pause menu.
     */
    public void disposePauseMenu() {
        final Group group = new Group(root);
        this.setRoot(group);
        this.controller.gameStart();
    }

    /**
     * 
     * @return bla
     */
    public Map<Enemy, EnemyView> getEnemiesView() {

        System.out.println("size " + enemiesView.size());
        return enemiesView;
    }
}
