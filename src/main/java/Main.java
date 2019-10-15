import database.ClassDatabase;
import database.DatabaseInterface;
import javafx.application.Application;
import javafx.stage.Stage;
import managers.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        DatabaseInterface database = new ClassDatabase();
        ImageManagerInterface imageManager = new ImageManager();
        LayoutManagerInterface layoutManager = new LayoutManager(database, imageManager);
        layoutManager.load(primaryStage);
        ThreadManagerInterface threadManager = new ThreadManager(database, layoutManager.getRoot(), imageManager);
        threadManager.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
