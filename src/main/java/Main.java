import database.ClassDatabase;
import database.DatabaseInterface;
import javafx.application.Application;
import javafx.stage.Stage;
import entity.Pigeon;
import entity.Position;
import sample.LayoutManager;
import sample.LayoutManagerInterface;

import static sample.ImageManager.*;
import static sample.LayoutManager.gridSize;

public class Main extends Application {

    private DatabaseInterface database = new ClassDatabase();
    private LayoutManagerInterface layoutManager = new LayoutManager(database);

    @Override
    public void start(Stage primaryStage) {
        this.loadPigeon();
        layoutManager.load(primaryStage);
    }

    private void loadPigeon () {
        database.addPigeon(new Pigeon(
            new Position(0, 0)
        ));
        database.addPigeon(new Pigeon(
            new Position(0, gridSize - pigeonSize)
        ));
        database.addPigeon(new Pigeon(
            new Position(gridSize - pigeonSize, 0)
        ));
        database.addPigeon(new Pigeon(
            new Position(gridSize - pigeonSize, gridSize - pigeonSize)
        ));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
