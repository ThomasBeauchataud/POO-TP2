package thread;

import database.DatabaseInterface;
import entity.Food;
import entity.Pigeon;
import entity.Position;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

import java.util.concurrent.TimeUnit;

import static sample.LayoutManager.frequency;

@SuppressWarnings("InfiniteLoopStatement")
public class PigeonLifeCycle implements Runnable {

    private DatabaseInterface database;
    private int pigeonId;
    private Runnable layoutPigeonUpdater;
    private Runnable layoutFoodUpdater;

    public PigeonLifeCycle(DatabaseInterface database, int pigeonId, GridPane root) {
        this.database = database;
        this.pigeonId = pigeonId;
        layoutPigeonUpdater = new LayoutPigeonUpdater(database, root, pigeonId);
        layoutFoodUpdater = new LayoutFoodUpdater(database, root);
    }

    @Override
    public void run() {
        try {
            while (true) {
                lookForFood();
                TimeUnit.MILLISECONDS.sleep(frequency);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void lookForFood() {
        Food[] foods = database.getFoods();
        Food bestFood = null;
        for(Food food : foods) {
            if(food.getDurability() != 0) {
                if(bestFood == null || bestFood.getDurability() < food.getDurability()) {
                    bestFood = food;
                }
            }
        }
        Pigeon pigeon = database.getPigeonById(pigeonId);
        if (bestFood == null){
            if(pigeon.getSleeping() == Pigeon.timeToSleep) {
                return;
            }
            pigeon.setSleeping(pigeon.getSleeping() + 1);
            return;
        }
        Position positionToReach = bestFood.getPosition();
        pigeon.moveTo(positionToReach);
        if(bestFood.getPosition().toString().equals(pigeon.getPosition().toString())) {
            bestFood.setEaten(true);
            database.updateFood(bestFood);
            Platform.runLater(layoutFoodUpdater);
        }
        database.updatePigeon(pigeon);
        Platform.runLater(layoutPigeonUpdater);
    }

}
