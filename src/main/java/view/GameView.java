package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.Controller;
import controller.menu.PauseMenuController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import model.StageImpl;
import util.Direction;
import util.UserData;
import util.Vector2D;
import view.map.MapView;
import view.player.PlayerView;

/**
 * The game main view. It contains all sub-views and handles the view refresh.
 * 
 */
public class GameView extends Scene {

    private static final double VIEWRESIZE = 1d;
    private final PlayerView playerView = new PlayerView(VIEWRESIZE);
    private final BulletsView bulletsView = new BulletsView(VIEWRESIZE);
    private final MapView mapView;
    //private final ImageView enemy;

    private final Controller controller = new Controller(this);
    private final UserData userData;

    private final Group root;
    /**
     * The GameView constructor.
     * @param username
     * @throws IOException 
     */
    public GameView(final String username) throws IOException {
        super(new Group());
        this.userData = new UserData(username);
        this.mapView = new MapView(controller.getMapController(), VIEWRESIZE);
        final List<Node> totalList = new ArrayList<>();
        totalList.addAll(mapView.getNodes());
        totalList.add(playerView.getPlayerImageView());
        //this.enemy = new ImageView(new Image(new FileInputStream("src/main/resources/person2.png")));
        //totalList.add(enemy);
        this.root = new Group(totalList);
        this.setRoot(root);
        controller.gameStart();
        this.setOnKeyPressed(e -> {
            try {
                controller.keyPressed(e.getCode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.setOnKeyReleased(e -> controller.keyReleased(e.getCode()));
        this.setOnKeyTyped(e -> { });
    }

    /**
     * TODO: Andrea Biagini.
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

    /**
     * Gets the visible part of the map.
     * 
     * @return MapView
     */
    public MapView getMapView() {
        return this.mapView;
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
     * TODO: Matteo Susca.
     * @param pos
     */
    public void setEnemyPos(final Vector2D pos) {
        //this.enemy.setX(pos.getX() * MapConstants.getTilesize());
    }

    /**
     * Updates the current visual frame using the info of the stage.
     * @param stage
     */
    public void refresh(final StageImpl stage) {
        playerView.updateCharacter(stage.getPlayer().getPosition(), stage.getPlayer().isCrouching(),
                stage.getPlayer().getAim().getDirection());
    }

    /**
     * Gets the datas of the person who's playing Metal Shot.
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
     * Dispose the pause menu.
     */
    public void disposePauseMenu() {
        final Group group = new Group(root);
        this.setRoot(group);
        this.controller.gameStart();
    }
}
