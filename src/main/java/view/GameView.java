package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.FileInputStream;

import javax.management.InstanceNotFoundException;

import controller.Controller;
import controller.menu.PauseMenuController;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.StageImpl;
import model.character.Enemy;
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
    //private final EnemyView enemyView = new EnemyView();
    private final Map<Enemy,EnemyView> enemiesView = new HashMap<>();
    private final BulletsView bulletsView = new BulletsView(1);
    private final LevelView levelView;
    private final ImageView background = new ImageView(new Image(new FileInputStream("src/main/resources/menusResources/MainMenuBG.png")));

    private final Controller controller = new Controller(this);
    private final UserData userData;

	private final Group root;
	private Vector2D prevPosSegment;
	private final Camera camera = new PerspectiveCamera();
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
		prevPosSegment = new Vector2D(controller.getStage().getPlayer().getPosition());
		totalList.add(background);
		totalList.addAll(levelView.displaySegments(controller.getStage().getPlayer().getPosition()));
		totalList.add(playerView.getCharacterImageView());
		//totalList.add(enemyView.getCharacterImageView());
		for(Enemy enemy : controller.getStage().getEnemies()) {
		    enemiesView.put(enemy, new EnemyView());		    
		}
		for(EnemyView enemyView : this.enemiesView.values()) {
		    totalList.add(enemyView.getCharacterImageView());
		}
		this.root = new Group(totalList);
		this.setRoot(root);
		this.setCamera(camera);
		//camera.setScaleX(0.5);
		//camera.setScaleY(0.5);
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
		if(stage.getPlayer().getSpeed().getX() > 0 
				&& stage.getPlayer().getPosition().getX()-(camera.getTranslateX()/MapConstants.getTilesize()) > 4
				&& stage.getLevel().getDistance(stage.getLevel().getSegmentAtPosition(stage.getPlayer().getPosition())) - stage.getPlayer().getPosition().getX() > 26) {
			camera.setTranslateX(playerView.getCharacterImageView().xProperty().get() - (4*MapConstants.getTilesize()));
		}
		
		for(Enemy enemy : stage.getEnemies()) {
		    enemiesView.get(enemy).updateCharacter(enemy.getPosition(), enemy.isCrouching(), enemy.getAim().getDirection());
		}
		
		playerView.updateCharacter(stage.getPlayer().getPosition(), stage.getPlayer().isCrouching(),
				stage.getPlayer().getAim().getDirection());
		/*enemyView.updateCharacter(stage.getEnemy().getPosition(), stage.getEnemy().isCrouching(),
                stage.getEnemy().getAim().getDirection());*/
		
		// Updates bullets
    	if (stage.getBullets().size() != this.bulletsView.getImageViewList().size()) {
    		this.root.getChildren().removeAll(this.bulletsView.getImageViewList());
    		this.bulletsView.updateBullets(stage.getBullets().stream().map(b -> b.getPosition()).collect(Collectors.toList()));
    		this.root.getChildren().addAll(this.bulletsView.getImageViewList());    		
    	} else {
    		this.bulletsView.updateBullets(stage.getBullets().stream().map(b -> b.getPosition()).collect(Collectors.toList()));
    	}

		if (!stage.getLevel().getSegmentAtPosition(stage.getPlayer().getPosition()).equals(stage.getLevel().getSegmentAtPosition(prevPosSegment))) {
			prevPosSegment = new Vector2D(stage.getPlayer().getPosition());
			this.root.getChildren().removeAll(levelView.getDisplayed());
			this.root.getChildren().addAll(levelView.displaySegments(stage.getPlayer().getPosition()));
			TranslateTransition tt = new TranslateTransition(Duration.millis(1000), this.root);
			tt.setToX(new Vector2D(stage.getLevel().getSegmentAtPosition(stage.getPlayer().getPosition()).getOrigin()).getX() * MapConstants.getTilesize() * -1);
			ParallelTransition pt = new ParallelTransition();
			
			pt.getChildren().add(tt);
			pt.play();
		}
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
     * Dispose of the pause menu.
     */
    public void disposePauseMenu() {
        final Group group = new Group(root);
        this.setRoot(group);
        this.controller.gameStart();
    }

    public Map<Enemy, EnemyView> getEnemiesView() {

        System.out.println("size " + enemiesView.size());
        // TODO Auto-generated method stub
        return enemiesView;
    }
}
