package thread.layout;

import database.DatabaseInterface;
import entity.FoodInterface;
import javafx.scene.layout.Pane;
import thread.common.CatchThreadException;

public class LayoutFoodRemover implements Runnable {

    private DatabaseInterface database;
    private Pane root;

    public LayoutFoodRemover(DatabaseInterface database, Pane root) {
        this.database = database;
        this.root = root;
    }

    @Override
    @CatchThreadException
    public void run() {
        for(FoodInterface food : database.getFoods()) {
            if(food.isEaten()) {
                database.removeFood(food);
                root.getChildren().remove(food.getImageView());
            }
        }
    }
}
