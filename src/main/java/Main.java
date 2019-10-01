import database.ClassDatabase;
import database.DatabaseInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import entity.Food;
import entity.Pigeon;
import entity.Position;
import thread.FoodLifeCycle;

import static sample.ImageManager.*;

public class Main extends Application {

    private final int gridSize = 1000;

    private DatabaseInterface database = new ClassDatabase();

    @Override
    public void start(Stage primaryStage) {
        this.loadPigeon();

        GridPane root = new GridPane();
        root.setGridLinesVisible(true); // TODO remove this when debug is done

        // Add 40 cases to the grid
        for (int i = 0; i < 40; i++) {
            ColumnConstraints column = new ColumnConstraints(foodSize);
            RowConstraints row = new RowConstraints(foodSize);
            root.getColumnConstraints().add(column);
            root.getRowConstraints().add(row);
        }
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 800, 600);
        this.displayPigeons(root);
        scene.setOnMouseClicked(mouseEvent  ->
                addFood(root, new Position((int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY()))
        );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addFood(GridPane root, Position position) {
        Food food = new Food(position);
        database.addFood(food);
        Thread foodThread = new Thread(new FoodLifeCycle(food));
        ImageView foodImageView = new ImageView();
        foodImageView.setImage(foodImage);
        foodImageView.setLayoutX(food.getPosition().getX());
        foodImageView.setLayoutY(food.getPosition().getY());
        // Position object on the grid
        GridPane.setConstraints(foodImageView, food.getPosition().getX() / foodSize, food.getPosition().getY() / foodSize);
        root.getChildren().add(foodImageView);
        foodThread.start();
    }

    private void displayPigeons(GridPane root) {
        for(Pigeon pigeon : database.getPigeonList()) {
            ImageView pigeonImageView = new ImageView(pigeonImage);
            //todo set pigeon ImageView Layout position
            root.getChildren().add(pigeonImageView);
        }
    }

    private void loadPigeon () {
        database.addPigeon(new Pigeon(
            new Position(0, 0)
        ));
        database.addPigeon(new Pigeon(
            new Position(0, this.gridSize - pigeonSize)
        ));
        database.addPigeon(new Pigeon(
            new Position(this.gridSize - pigeonSize, 0)
        ));
        database.addPigeon(new Pigeon(
            new Position(this.gridSize - pigeonSize, this.gridSize - pigeonSize)
        ));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
