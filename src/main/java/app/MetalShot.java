package app;


import controller.Controller;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import controller.TemporaryController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import util.Direction;
import util.Vector;
import view.BulletsView;

/**
 * TODO: write javadoc
 *
 */
public final class MetalShot extends Application {
	private TemporaryController controller;
	private Group mainGroup;
	private BulletsView bulletsView;
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
        
        this.controller = new TemporaryController(this);
        
        this.mainGroup = new Group();
        
        this.bulletsView = new BulletsView();
        
        var s = new Scene(mainGroup, 700, 700);
        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	
			@Override
			public void handle(KeyEvent event) {
				controller.keyPressed(event.getCode());
			}
			
        });
        
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(s);
        
        primaryStage.setTitle("Hello");
        primaryStage.show();
        
        this.controller.gameStart();
    }
    
    public void displayBullets(final Map<Vector, Direction> bullets) {
    	this.mainGroup.getChildren().removeAll(this.bulletsView.getImageViewList());
    	this.bulletsView.updateBullets(bullets.keySet().stream().collect(Collectors.toList()));
    	this.mainGroup.getChildren().addAll(this.bulletsView.getImageViewList());
    }

    public static void run(final String... args) {
        launch();
    }
    
    /**
     * TODO: write javadoc
     * @author Andrea Biagini
     *
     */
    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String...args) {
            Application.launch(MetalShot.class, args);
        }
    }
}
