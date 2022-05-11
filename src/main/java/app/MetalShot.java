package app;

import java.awt.Dimension;
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

/**
 * TODO
 * @author sysosus
 *
 */
public final class MetalShot extends Application {
	private Controller controller;
	private ImageView player;
    private ImageView enemy;
    private Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
    	this.controller = new Controller(this);

    	FileInputStream person = new FileInputStream("src/main/resources/person.png");
    	FileInputStream person2 = new FileInputStream("src/main/resources/person2.png");

    	this.player = new ImageView(new Image(person));
    	this.enemy = new ImageView(new Image(person2));

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
/**
 * TODO
 * @author sysosus
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

    public void setEnemyPos(Vector pos) {
    	this.enemy.setX(pos.getX());
    }
    
    public void setPlayerPos(Vector pos) {
    	this.player.setX(pos.getX());
    }

    public double getX() {
    	return this.enemy.getX();
    }
    
    public Dimension getDim() {
    	return new Dimension(this.res);
    }
}
