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
import util.map.MapConstants;
import view.character.EnemyView;
import view.character.PlayerView;
import view.map.CameraManager;
import view.map.LevelView;
import controller.Controller;
import controller.menu.HUD;
import controller.menu.GameOverMenuController;
import controller.menu.PauseMenuController;
import controller.menu.WinMenuController;

/**
 * The game main View. It contains all sub views and handles the View refresh.
 * 
 */
public class GameView extends Scene {

    private final PlayerView playerView = new PlayerView();
    private final Map<Enemy, EnemyView> enemiesView = new HashMap<>();
    private final BulletsView bulletsView = new BulletsView(1);
    private final LevelView levelView;
    private final ImageView background = new ImageView(
            new Image(ClassLoader.getSystemResourceAsStream("backgroundLarge.png")));
    private final Controller controller;
    private final Group root;
    private final CameraManager cameraManager;
    private final HUD hudController;
    private GridPane pauseMenu;
    private final Node hud;
    private static final double SCALINGFACTOR = 1.75;

    /**
     * The GameView constructor.
     * 
     * @param controller
     * @throws IOException               if the text map is not present
     * @throws InstanceNotFoundException if player spawn is not set in any text map
     */
    public GameView(final Controller controller) throws IOException, InstanceNotFoundException {
        super(new Group());
        this.controller = controller;
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

        this.setOnKeyPressed(e -> {
            controller.keyPressed(e.getCode());
        });
        this.setOnKeyReleased(e -> controller.keyReleased(e.getCode()));
    }

    /**
     * Returns the View side of the player.
     * 
     * @return the PlayerView
     */
    public PlayerView getPlayerView() {
        return this.playerView;
    }

    /**
     * Returns the View side of the map.
     * 
     * @return the LevelView
     */
    public LevelView getLevelView() {
        return this.levelView;
    }

    /**
     * Returns the View side of the bullets.
     * 
     * @return the BulletsView.
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

        final TranslateTransition btt = new TranslateTransition(Duration.millis(1), this.background);
        btt.setToX(cameraManager.getOffset() * MapConstants.getTilesize() - cameraManager.HORIZONTALDEFAULT);
        final ParallelTransition bpt = new ParallelTransition();
        bpt.getChildren().add(btt);
        bpt.play();

        this.hudController.setSize(CameraManager.HORIZONTALDEFAULT * SCALINGFACTOR, CameraManager.VERTICALDEFAULT * SCALINGFACTOR);

        cameraManager.updateCamera();

        if (stage.getEnemies().size() != enemiesView.keySet().size()) {
            removeEnemies(stage.getEnemies());
        }

        for (final Enemy enemy : stage.getEnemies()) {
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
        this.controller.getUserData().setLpLeft(stage.getPlayer().getHealth().getHealth());
        hudController.refresh(this.controller.getUserData());
    }

    /**
     * Refresh the pauseMenu with the current size.
     */
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
        final var loader = new FXMLLoader(getClass().getResource("/fxml/PauseMenu.fxml"));

        try {
            this.pauseMenu = (GridPane) loader.load();
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
        this.controller.resume();
        this.setRoot(group);
    }

    /**
     * Display the win menu.
     * 
     * @throws IOException if the fxml sheet doesn't exist.
     */
    public void displayWinMenu() {
        root.getChildren().remove(this.hud);
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
        wmc.setInfoToDisplay(this.controller.getUserData(), this.controller.getStage().getPlayer().getLives());
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
        root.getChildren().remove(this.hud);
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
                this.controller.getStage().getLevel().getSegments().size(), this.controller.getUserData());
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
     * Returns the View side of the Enemies.
     * @return the View of the Enemies.
     */
    public Map<Enemy, EnemyView> getEnemiesView() {
        return enemiesView;
    }

    @SuppressWarnings("unlikely-arg-type")
    private void removeEnemies(final Collection<Enemy> enemies) {
        final List<EnemyView> removable = new LinkedList<>();
        final List<ImageView> remove = new LinkedList<>();
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

    /**
     * Returns the main controller of this scene.
     * @return the main controller of this scene
     */
    public Controller getController() {
        return this.controller;
    }

    /**
     * Returns the CameraManager.
     * @return the CameraManager.
     */
    public CameraManager getCameraManager() {
        return this.cameraManager;
    }
}
