package app;

import javafx.application.Application;
 /**
  * 
  *
  */
public final class App {
    private App() {
        // the constructor will never be called directly.
    }

    public static void main(final String... args) {
        Application.launch(MetalShot.class, args);
    }
}
