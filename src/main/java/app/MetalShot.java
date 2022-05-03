package app;

import controller.BulletsController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public final class MetalShot extends Application {
	
	private BulletsController bc;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Label message = new Label("Hello, JavaFX!"); 
        message.setFont(new Font(100));
        
        ImageView iv = new ImageView();
        
        this.bc = new BulletsController();
        
        var s = new Scene(message);
        s.setOnKeyPressed(new EventHandler<Event>() {
        	
			@Override
			public void handle(Event event) {
				bc.addBullet();
			}
			
        });
        
        primaryStage.setScene(s);
        primaryStage.setTitle("Hello");
        primaryStage.show();
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
}
