package app;



import java.io.FileInputStream;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import util.Vector;

public final class MetalShot extends Application {
	private Controller controller;
    private Circle circle = new Circle(0,0,20);
    private ImageView enemy;
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
    	this.controller = new Controller(this);
    	
    	FileInputStream input = new FileInputStream("src/main/resources/person.png");
    	
    	this.enemy = new ImageView(new Image(input));
    	
    	StackPane root = new StackPane();
    	
    	System.out.println(enemy.getX());
    	
    	
    	root.getChildren().add(enemy);
        primaryStage.setScene(new Scene(root,1000,500));
        primaryStage.setTitle("Hello");
        primaryStage.show();
        

    	/*for(int i = 0; i<10;i++) {
        	circle.setTranslateX(i*50);
        }
        
    	System.out.println(circle.getCenterX());
    	System.out.println(circle.getLayoutX());
    	System.out.println(circle.getTranslateX());*/
        
        
        this.controller.gameStart();
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
