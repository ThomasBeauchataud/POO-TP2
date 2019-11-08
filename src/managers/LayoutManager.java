package managers;

import database.DatabaseInterface;
import entity.*;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import thread.FoodLifeCycle;

public class LayoutManager implements LayoutManagerInterface {

    private static final int gridSize = ConfigManager.getInt("gridSize");
    private static final int imageSize = ConfigManager.getInt("imageSize");

    private DatabaseInterface database;
    private ImageManagerInterface imageManager;
    private GridPane root;

    public LayoutManager(DatabaseInterface database, ImageManagerInterface imageManager) {
        this.database = database;
        this.imageManager = imageManager;
    }

    @Override
    public void load(Stage primaryStage) {
        try {
            root = new GridPane();

            for (int i = 0; i < gridSize / imageSize; i++) {
                ColumnConstraints column = new ColumnConstraints(imageSize);
                RowConstraints row = new RowConstraints(imageSize);
                root.getColumnConstraints().add(column);
                root.getRowConstraints().add(row);
            }
            primaryStage.setTitle("Pigeon Game");
            Scene scene = new Scene(root, gridSize, gridSize);

            this.displayPigeons(root);
            scene.setOnMouseClicked(mouseEvent ->
                    addFood(root, new Position((int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY()))
            );
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while trying to load the scene");
            System.exit(1);
        }
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    private void displayPigeons(GridPane root) {
        for(PigeonInterface pigeon : database.getPigeons()) {
            ImageView pigeonImageView = new ImageView(imageManager.getPigeonImage());
            GridPane.setConstraints(pigeonImageView, pigeon.getPosition().getX() / imageSize, pigeon.getPosition().getY() / imageSize);
            pigeon.setImageView(pigeonImageView);
            database.updatePigeon(pigeon);
            root.getChildren().add(pigeonImageView);
        }
    }

    private void addFood(GridPane root, PositionInterface position) {
        FoodInterface food = new Food(position);
        ImageView foodImageView = new ImageView(imageManager.getFoodImage());
        food.setImageView(foodImageView);
        database.addFood(food);
        GridPane.setConstraints(foodImageView, food.getPosition().getX() / imageSize, food.getPosition().getY() / imageSize);
        root.getChildren().add(foodImageView);
        Thread foodThread = new Thread(new FoodLifeCycle(food));
        foodThread.start();
    }

}
