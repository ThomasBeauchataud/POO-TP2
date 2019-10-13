import database.ClassDatabase;
import database.DatabaseInterface;
import javafx.application.Application;
import javafx.stage.Stage;
import sample.LayoutManager;
import sample.LayoutManagerInterface;

public class Main extends Application {

    private DatabaseInterface database = new ClassDatabase();
    private LayoutManagerInterface layoutManager = new LayoutManager(database);

    @Override
    public void start(Stage primaryStage) {
        layoutManager.load(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
