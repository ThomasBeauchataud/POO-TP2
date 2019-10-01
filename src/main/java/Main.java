import database.ClassDatabase;
import database.DatabaseInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import entity.Food;
import entity.Pigeon;
import entity.Position;
import thread.FoodLifeCycle;

import java.io.File;

public class Main extends Application {

    private Image foodImage = this.loadFoodImage();
    private Image pigeonImage = this.loadPigeonImage();
    private final int pigeonSize = 60;
    private final int foodSize = 20;
    private final int gridSize = 1000;

    private DatabaseInterface database = new ClassDatabase();

    @Override
    public void start(Stage primaryStage) {
        this.loadPigeon();

        GridPane root = new GridPane();
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
        foodImageView.setImage(this.foodImage);
        foodImageView.setLayoutX(food.getPosition().getX());
        foodImageView.setLayoutY(food.getPosition().getY());
        root.getChildren().add(foodImageView);
        foodThread.start();
    }

    private void displayPigeons(GridPane root) {
        for(Pigeon pigeon : database.getPigeonList()) {
            ImageView pigeonImageView = new ImageView(this.pigeonImage);
            //todo set pigeon ImageView Layout position
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

    private void loadPigeon () {
        database.addPigeon(new Pigeon(
            new Position(0, 0)
        ));
        database.addPigeon(new Pigeon(
            new Position(0, this.gridSize - this.pigeonSize)
        ));
        database.addPigeon(new Pigeon(
            new Position(this.gridSize - this.pigeonSize, 0)
        ));
        database.addPigeon(new Pigeon(
            new Position(this.gridSize - this.pigeonSize, this.gridSize - this.pigeonSize)
        ));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
