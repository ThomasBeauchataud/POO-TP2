package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import thread.FoodLifeCycle;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    private Image foodImage = this.loadFoodImage();
    private ArrayList<Food> foodList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        HBox box = new HBox();
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(box, 300, 275);
        scene.setOnMouseClicked(mouseEvent  -> {
                addFood(box, new Position((int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY()));
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addFood(HBox box, Position position) {
        Food food = new Food(position);
        foodList.add(food);
        Thread foodThread = new Thread(new FoodLifeCycle(food));
        ImageView foodImageView = new ImageView();
        foodImageView.setImage(this.foodImage);
        box.getChildren().add(foodImageView);
        foodThread.start();
    }

    private Image loadFoodImage() {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/food.png");
        return new Image(file.toURI().toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
