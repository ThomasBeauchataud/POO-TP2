package sample;

import database.DatabaseInterface;
import entity.Food;
import entity.Pigeon;
import entity.Position;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import thread.FoodLifeCycle;
import thread.LayoutUpdate;
import thread.PigeonLifeCycle;

import static sample.ImageManager.*;

public class LayoutManager implements LayoutManagerInterface {

    public static final int gridSize = 800;
    public static final int frequency = 100;

    private DatabaseInterface database;

    public LayoutManager(DatabaseInterface database) {
        this.database = database;
    }

    @Override
    public void load(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setGridLinesVisible(true); // TODO remove this when debug is done

        // Add 40 cases to the grid
        //todo change static 40 to calculation with gridSize and imageSize
        for (int i = 0; i < 40; i++) {
            ColumnConstraints column = new ColumnConstraints(foodSize);
            RowConstraints row = new RowConstraints(foodSize);
            root.getColumnConstraints().add(column);
            root.getRowConstraints().add(row);
        }
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, gridSize, gridSize);
        this.displayPigeons(root);
        scene.setOnMouseClicked(mouseEvent  ->
                addFood(root, new Position((int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY()))
        );
        primaryStage.setScene(scene);
        primaryStage.show();

        Thread layoutThread = new Thread(new LayoutUpdate(database, root));
        layoutThread.start();
    }

    private void displayPigeons(GridPane root) {
        for(Pigeon pigeon : database.getPigeonList()) {
            ImageView pigeonImageView = new ImageView(pigeonImage);
            pigeonImageView.setLayoutX(pigeon.getPosition().getX());
            pigeonImageView.setLayoutY(pigeon.getPosition().getY());
            GridPane.setConstraints(pigeonImageView, pigeon.getPosition().getX() / foodSize, pigeon.getPosition().getY() / foodSize);
            root.getChildren().add(pigeonImageView);
            Thread pigeonThread = new Thread(new PigeonLifeCycle(database, pigeon.getId()));
            pigeonThread.start();
        }
    }

    private void addFood(GridPane root, Position position) {
        Food food = new Food(position);
        database.addFood(food);
        ImageView foodImageView = new ImageView();
        foodImageView.setImage(foodImage);
        foodImageView.setLayoutX(food.getPosition().getX());
        foodImageView.setLayoutY(food.getPosition().getY());
        // Position object on the grid
        GridPane.setConstraints(foodImageView, food.getPosition().getX() / foodSize, food.getPosition().getY() / foodSize);
        root.getChildren().add(foodImageView);
        Thread foodThread = new Thread(new FoodLifeCycle(food));
        foodThread.start();
    }

}
