package view.menu;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
/**
 * 
 *
 */
public class MainMenu extends TilePane {
    private static final String STARTBUTTONPATH = "src\\main\\resources\\StartButton.png";
    private static final String STARTBUTTONPRESSEDPATH = "src\\main\\resources\\StartButtonPressed.png";

    /**
     * 
     * @throws IOException
     */
    public MainMenu() throws IOException {
        final ImageView startButtonImg = new ImageView(new Image(new FileInputStream(STARTBUTTONPATH)));
        final ImageView startButtonPressedImg = new ImageView(new Image(new FileInputStream(STARTBUTTONPRESSEDPATH)));
        final Button startButton = new Button("", startButtonImg);
        startButton.setOnMousePressed(new EventHandler<>() {

            @Override
            public void handle(final MouseEvent event) {
                startButton.setGraphic(startButtonPressedImg);
            }
        });
        startButton.setOnMousePressed(new EventHandler<>() {

            @Override
            public void handle(final MouseEvent event) {
                startButton.setGraphic(startButtonImg);
            }
        });
        this.getChildren().add(startButton);
    }
}
