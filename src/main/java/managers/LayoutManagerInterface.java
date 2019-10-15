package managers;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface LayoutManagerInterface {

    /**
     * Load the Layout
     * @param primaryStage Stage
     */
    void load(Stage primaryStage);

    /**
     * Return the Pane of the Scene
     * @return Pane
     */
    Pane getRoot();

}
