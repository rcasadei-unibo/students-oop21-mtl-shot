package view;

import java.io.FileInputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import model.StageImpl;
import util.Direction;
import util.UserData;
import util.Vector2D;
import util.map.MapConstants;
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
    private final ImageView enemy;

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
        this.enemy = new ImageView(new Image(new FileInputStream("src/main/resources/person2.png")));
        totalList.add(enemy);
        this.root = new Group(totalList);
        this.setRoot(root);
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
     * Gets the visible part of the player.
     * 
     * @return PlayerView
     */
    public PlayerView getPlayerView() {
        return this.playerView;
    }

    public MapView getMapView() {
        return this.mapView;
    }

    public BulletsView getBulletsView() {
        return this.bulletsView;
    }

    /**
     * TODO: Matteo Susca.
     * @param pos
     */
    public void setEnemyPos(final Vector2D pos) {
        this.enemy.setX(pos.getX() * MapConstants.getTilesize());
    }

    /**
     * Updates the current visual frame using the info of the stage.
     * @param stage
     */
    public void refresh(final StageImpl stage) {
        playerView.updateCharacter(stage.getPlayer().getPosition(), stage.getPlayer().isCrouching(),
                stage.getPlayer().getAim().getDirection());
        
     // Updates bullets
    	if (stage.getBullets().size() != this.bulletsView.getImageViewList().size()) {
    		this.root.getChildren().removeAll(this.bulletsView.getImageViewList());
    		this.bulletsView.updateBullets(stage.getBullets().stream().map(b -> b.getPosition()).collect(Collectors.toList()));
    		this.root.getChildren().addAll(this.bulletsView.getImageViewList());    		
    	} else {
    		this.bulletsView.updateBullets(stage.getBullets().stream().map(b -> b.getPosition()).collect(Collectors.toList()));
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
