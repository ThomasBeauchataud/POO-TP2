package thread;

import database.DatabaseInterface;
import entity.Food;
import javafx.scene.layout.GridPane;

public class LayoutFoodUpdater implements Runnable {

    private DatabaseInterface database;
    private GridPane root;

    LayoutFoodUpdater(DatabaseInterface database, GridPane root) {
        this.database = database;
        this.root = root;
    }

    @Override
    public void run() {
        for(int id = 0 ; id < database.getFoodsCount() ; id ++) {
            Food food = database.getFoodById(id);
            if(food.isEaten()) {
                database.removeFood(food);
                root.getChildren().remove(id + 1 + database.getPigeonsCount());
            }
        }
    }
}
