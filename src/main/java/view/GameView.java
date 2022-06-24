package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.StageImpl;
import model.character.Enemy;
import model.weapons.R99;
import util.UserData;
import util.map.MapConstants;
import view.map.CameraManager;
import view.map.LevelView;
import view.player.PlayerView;
import controller.Controller;
import controller.menu.HUD;
import controller.menu.GameOverMenuController;
import controller.menu.PauseMenuController;
import controller.menu.WinMenuController;

/**
 * The game main view. It contains all sub-views and handles the view refresh.
 * 
 */
public class GameView extends Scene {

    private final PlayerView playerView = new PlayerView();
    private final Map<Enemy, EnemyView> enemiesView = new HashMap<>();
    private final BulletsView bulletsView = new BulletsView(1);
    private final LevelView levelView;
    private final ImageView background = new ImageView(
            new Image(ClassLoader.getSystemResourceAsStream("menusResources/MainMenuBG.png")));
    private final Controller controller = new Controller(this);
    private final UserData userData;
    private final Group root;
    private final CameraManager cameraManager;
    private final HUD hudController;

    private FXMLLoader loader;
    private GridPane pauseMenu;

    private Node hud;

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

        for (final Enemy enemy : controller.getStage().getEnemies()) {
            enemiesView.put(enemy, new EnemyView());
        }
        for (final EnemyView enemyView : this.enemiesView.values()) {
            totalList.add(enemyView.getCharacterImageView());
        }
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("fxml/HUD.fxml"));
        hud = loader.load();
        this.hudController = (HUD) loader.getController();
        this.root = new Group(totalList);

        this.setRoot(root);
        this.cameraManager = new CameraManager(controller, root, levelView, this);
        this.setCamera(cameraManager.getCamera());
        root.getChildren().add(hud);
        controller.gameStart();

        this.setOnKeyPressed(e -> {
            controller.keyPressed(e.getCode());
        });
        this.setOnKeyReleased(e -> controller.keyReleased(e.getCode()));
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

        final TranslateTransition tt = new TranslateTransition(Duration.millis(1), this.hud);
        tt.setToX(cameraManager.getOffset() * MapConstants.getTilesize());
        final ParallelTransition pt = new ParallelTransition();
        this.hud.toFront();
        pt.getChildren().add(tt);
        pt.play();

        this.hudController.setSize(1920 * 1.75, 1080 * 1.75);

        cameraManager.updateCamera();

        if (stage.getEnemies().size() != enemiesView.keySet().size()) {
            removeEnemies(stage.getEnemies());
        }

        for (Enemy enemy : stage.getEnemies()) {
            enemiesView.get(enemy).updateCharacter(enemy);
        }

        playerView.updateCharacter(stage.getPlayer());
        if (stage.getPlayer().getWeapon().equals(new R99())) {
            playerView.setWeapon(CharacterSprites.playerIdleRifle, CharacterSprites.playerIdleUpRifle,
                    CharacterSprites.playerRunRifle, CharacterSprites.playerRunUpRifle,
                    CharacterSprites.playerCrouchIdleRifle, CharacterSprites.playerCrouchRunRifle);
        }

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
        userData.setLpLeft(stage.getPlayer().getHealth().getHealth());
        hudController.refresh(userData);
    }

    /**
     * Gets the data of the person who's playing Metal Shot.
     * 
     * @return UserData
     */
    public UserData getUserData() {
        return this.userData;
    }

    public void menuRefresh() {
        this.pauseMenu.setPrefSize(this.getWidth(), this.getHeight());
    }

    /**
     * Display the pause menu.
     * 
     * @throws IOException if the fxml sheet doesn't exist.
     */
    public void displayPauseMenu() {
        cameraManager.resetCamera();
        final Group group = new Group(root);
        loader = new FXMLLoader(getClass().getResource("/fxml/PauseMenu.fxml"));

        GridPane pauseMenu = null;
        try {
            pauseMenu = (GridPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        group.getChildren().add(pauseMenu);
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
     * Display the win menu.
     * 
     * @throws IOException if the fxml sheet doesn't exist.
     */
    public void displayWinMenu() {
        final Group group = new Group(root);
        final var loader = new FXMLLoader(getClass().getResource("/fxml/WinMenu.fxml"));

        Node winMenu = null;
        try {
            winMenu = (Node) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        group.getChildren().add(winMenu);
        final WinMenuController wmc = (WinMenuController) loader.getController();
        wmc.setInfoToDisplay(userData, this.controller.getStage().getPlayer().getLives());
        this.setRoot(group);
    }

    /**
     * Dispose of the win menu.
     */
    public void disposeWinMenu() {
        final Group group = new Group(root);
        this.setRoot(group);
    }

    /**
     * Display the game over menu.
     * 
     * @throws IOException if the fxml sheet doesn't exist.
     */
    public void displayGameOverMenu() {
        final Group group = new Group(root);
        final var loader = new FXMLLoader(getClass().getResource("/fxml/GameOverMenu.fxml"));

        Node goMenu = null;
        try {
            goMenu = (Node) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        group.getChildren().add(goMenu);
        final GameOverMenuController gomc = (GameOverMenuController) loader.getController();
        gomc.setInfoToDisplay(
                this.controller.getStage().getLevel().getSegments()
                        .indexOf(this.controller.getStage().getLevel()
                                .getSegmentAtPosition(this.controller.getStage().getPlayer().getPosition()))
                        + 1,
                this.controller.getStage().getLevel().getSegments().size(), this.userData);
        this.setRoot(group);
    }

    /**
     * Dispose of the game over menu.
     */
    public void disposeGameOverMenu() {
        final Group group = new Group(root);
        this.setRoot(group);
    }

    /**
     * 
     * @return bla
     */
    public Map<Enemy, EnemyView> getEnemiesView() {
        return enemiesView;
    }

    private void removeEnemies(final Collection<Enemy> enemies) {
        List<EnemyView> removable = new LinkedList<>();
        List<ImageView> remove = new LinkedList<>();
        enemiesView.forEach((k, v) -> {
            if (!enemies.contains(k)) {
                removable.add(enemiesView.get(k));
                remove.add(enemiesView.get(k).getCharacterImageView());
            }
        });

        removable.forEach(e -> {
            enemiesView.remove(e);
        });

        this.root.getChildren().removeAll(remove);
    }

    public CameraManager getCameraManager() {
        return this.cameraManager;
    }
}
