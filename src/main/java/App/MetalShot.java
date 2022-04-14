package App;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import utilities.Vector;

public final class MetalShot extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        /*final Label message = new Label("Hello, JavaFX!"); 
        message.setFont(new Font(100));
        primaryStage.setScene(new Scene(message));
        primaryStage.setTitle("Hello");
        primaryStage.show();*/
        this.playertest();
    }

    private void playertest() {
        final Player player = new PlayerBuilder()
                .health(new SimpleHealth())
                .hitbox(new Vector(1, 2))
                .lives(3)
                .position(new Vector(0,0))
                .build();
        
      //--------SIMPLE MOVEMENT-------------
        System.out.println("sx x4");
        System.out.println(player.getPosition() + " " + player.getSpeed());
        player.setLeft(true);
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        System.out.println(player.getPosition() + " " + player.getSpeed());
        
        System.out.println("rallenta");
        player.setLeft(false);
        while (player.getSpeed().getX() != 0) {
            player.moveEntity();
            System.out.println(player.getPosition() + " " + player.getSpeed());
        }
        
        player.setPosition(new Vector(0,0));
        System.out.println("dx x4");
        player.setRight(true);
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        System.out.println(player.getPosition() + " " + player.getSpeed());
        
        System.out.println("rallenta");
        player.setRight(false);
        while (player.getSpeed().getX() != 0) {
            player.moveEntity();
            System.out.println(player.getPosition() + " " + player.getSpeed());
        }
        
        player.setPosition(new Vector(0,0));
        System.out.println("jump");
        player.setJump(true);
        for (int i = 0; i < 60; i++) {
            System.out.println(player.getPosition() + " " + player.getSpeed());
            player.moveEntity();
        }
        player.setFall(false);
        player.setJump(false);
        player.setSpeed(new Vector(0,0));
        
        //---------COMBO MOVEMENT-------------
        player.setPosition(new Vector(0,0));
        System.out.println("dx sx x4");
        System.out.println(player.getPosition() + " " + player.getSpeed());
        player.setLeft(true);
        player.setRight(true);
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        player.moveEntity();
        System.out.println(player.getPosition() + " " + player.getSpeed());
        player.setLeft(false);
        player.setRight(false);
        
        player.setPosition(new Vector(0,0));
        System.out.println("jump dx");
        System.out.println(player.getPosition() + " " + player.getSpeed());
        player.setRight(true);
        player.setJump(true);
        for (int i = 0; i < 50; i++) {
            player.moveEntity();
            System.out.println(player.getPosition() + " " + player.getSpeed());
        }
        player.setRight(false);
        player.setJump(false);
        player.setFall(false);
        player.setSpeed(new Vector(0,0));
        
        player.setPosition(new Vector(0,0));
    }

    public static void run(final String... args) {
        launch();
    }

    // Defining the main methods directly within JavaFXApp may be problematic:
    // public static void main(final String[] args) {
    //        run();
    // }

    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String...args) {
            Application.launch(MetalShot.class, args);
        }
    }
}
