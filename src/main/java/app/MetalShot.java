package app;

import java.awt.Toolkit;
import java.io.FileInputStream;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.Vector;

public final class MetalShot extends Application {
	private Controller controller;
    private ImageView enemy;
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
    	this.controller = new Controller(this);
    	
    	FileInputStream input = new FileInputStream("src/main/resources/person.png");
    	
    	this.enemy = new ImageView(new Image(input));
    	

        setCirclePos(new Vector(825,0));
    	
    	final Group mainGroup = new Group(enemy);
    	
    	System.out.println(enemy.getX());
    	
        primaryStage.setScene(new Scene(mainGroup,1000,500));
        primaryStage.setTitle("Hello");
        primaryStage.show();
        controller.gameStart();
        
        
    }
    
    public static void run(final String... args) {
        launch();
    }
    
    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String...args) {
            Application.launch(MetalShot.class, args);
        }
    }
    
    public void setCirclePos(Vector pos) {
    	this.enemy.setX(pos.getX());
    }
    
    public double getX() {
    	return this.enemy.getX();
    }
}
