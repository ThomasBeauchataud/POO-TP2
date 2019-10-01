package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import thread.FoodLifeCycle;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    private Image foodImage = this.loadFoodImage();
    private Image pigeonImage = this.loadPigeonImage();
    private ArrayList<Food> foodList = this.loadFood();
    private ArrayList<Pigeon> pigeonList = this.loadPigeon();
    private final int pigeonSize = 20;
    private final int foodSize = 20;
    private final int gridSize = 1000;

    @Override
    public void start(Stage primaryStage) {
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
        Scene scene = new Scene(root, 800, 800);
        this.initPigeon(root);
        scene.setOnMouseClicked(mouseEvent  ->
                addFood(root, new Position((int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY()))
        );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addFood(GridPane root, Position position) {
        Food food = new Food(position);
        foodList.add(food);
        Thread foodThread = new Thread(new FoodLifeCycle(food));
        ImageView foodImageView = new ImageView();
        foodImageView.setImage(this.foodImage);
        foodImageView.setLayoutX(food.getPosition().getX());
        foodImageView.setLayoutY(food.getPosition().getY());

        // Position object on the grid
        GridPane.setConstraints(foodImageView, food.getPosition().getX() / foodSize, food.getPosition().getY() / foodSize);

        root.getChildren().add(foodImageView);
        foodThread.start();
    }

    private void initPigeon(GridPane root) {
        for(Pigeon pigeon : this.pigeonList) {
            ImageView pigeonImageView = new ImageView(this.pigeonImage);
            //pigeonImageView
            root.getChildren().add(pigeonImageView);
        }
    }

    private Image loadFoodImage() {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/food.png");
        return new Image(file.toURI().toString());
    }

    private Image loadPigeonImage() {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/pigeon.png");
        return new Image(file.toURI().toString());
    }

    private ArrayList<Pigeon> loadPigeon () {
        ArrayList<Pigeon> pigeonList = new ArrayList<>();
        pigeonList.add(new Pigeon(
            new Position(0, 0)
        ));
        pigeonList.add(new Pigeon(
            new Position(0, this.gridSize - this.pigeonSize)
        ));
        pigeonList.add(new Pigeon(
            new Position(this.gridSize - this.pigeonSize, 0)
        ));
        pigeonList.add(new Pigeon(
            new Position(this.gridSize - this.pigeonSize, this.gridSize - this.pigeonSize)
        ));
        return pigeonList;
    }

    private ArrayList<Food> loadFood () {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
