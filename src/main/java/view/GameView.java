package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.StageImpl;
import util.Direction;
import util.Vector2D;
import util.map.MapConstants;
import view.map.MapView;
import view.player.PlayerView;

/**
 * The game main view. It contains all sub-views and handles the view refresh.
 * 
 */
public class GameView {

    private static final double VIEWRESIZE = 1d;
    private final PlayerView playerView;
    private final BulletsView bulletsView;
    private final Group mainGroup;
    private final ImageView enemy;
    private final Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
    private final Controller controller;

    /**
     * The GameView constructor.
     * @param stage the stage where the GameView is launched
     * @throws IOException if files are not found.
     */
    public GameView(final Stage stage) throws IOException {
        this.playerView = new PlayerView(VIEWRESIZE);
        this.bulletsView = new BulletsView(VIEWRESIZE);
        this.controller = new Controller(this);
        final Scene mainScene;
        final MapView mapView = new MapView(controller.getMapController(), VIEWRESIZE);
        final List<Node> totalList = new ArrayList<>();
        totalList.addAll(mapView.getNodes());
        totalList.add(playerView.getPlayerImageView());
        FileInputStream person2 = new FileInputStream("src/main/resources/person2.png");
        this.enemy = new ImageView(new Image(person2));
        totalList.add(enemy);
        this.mainGroup = new Group(totalList);
        mainScene = new Scene(mainGroup);
        stage.setScene(mainScene);
        stage.setTitle("メタルショット");
        stage.show();
        controller.gameStart();
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                controller.keyPressed(event.getCode());
            }
        });
        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                controller.keyReleased(event.getCode());
            }
        });
        mainScene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent event) {
            }
        });
    }

    /**
     * TODO: Andrea Biagini.
     * @param bullets
     */
    public void displayBullets(final Map<Vector2D, Direction> bullets) {
        this.mainGroup.getChildren().removeAll(this.bulletsView.getImageViewList());
        this.bulletsView.updateBullets(bullets.keySet().stream().collect(Collectors.toList()));
        this.mainGroup.getChildren().addAll(this.bulletsView.getImageViewList());
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
     * Updates the current visual frame using the info of the stage.
     * @param stage
     */
    public void refresh(final StageImpl stage) {
        playerView.updateCharacter(stage.getPlayer().getPosition(), stage.getPlayer().isCrouching(),
                stage.getPlayer().getAim().getDirection());
    }

    /**
     * TODO: Matteo Susca.
     * @param pos
     */
    public void setEnemyPos(final Vector2D pos) {
        this.enemy.setX(pos.getX() * MapConstants.getTilesize());
    }

    /**
     * TODO: Matteo Susca.
     * @return double
     */
    public double getX() {
        return this.enemy.getX();
    }

    /**
     * TODO: Matteo Susca.
     * @return dim
     */
    public Dimension getDim() {
        return new Dimension(this.res);
    }
}
