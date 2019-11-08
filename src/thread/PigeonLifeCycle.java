package thread;

import database.DatabaseInterface;
import entity.*;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import managers.ConfigManager;
import thread.layout.LayoutFoodRemover;
import thread.layout.LayoutPigeonUpdater;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("InfiniteLoopStatement")
public class PigeonLifeCycle implements Runnable {

    private static final int frequency = ConfigManager.getInt("frequency");
    private static final int pigeonSecurityRange = ConfigManager.getInt("pigeonSecurityRange");

    private DatabaseInterface database;
    private PigeonInterface pigeon;
    private Runnable layoutPigeonUpdater;
    private Runnable layoutFoodUpdater;

    public PigeonLifeCycle(DatabaseInterface database, PigeonInterface pigeon, Pane root) {
        this.database = database;
        this.pigeon = pigeon;
        layoutPigeonUpdater = new LayoutPigeonUpdater(root, pigeon);
        layoutFoodUpdater = new LayoutFoodRemover(database, root);
    }

    @Override
    public void run() {
        while (true) {
            try {
                execute();
                TimeUnit.MILLISECONDS.sleep(frequency);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void execute() {
        FoodInterface[] foods = database.getFoods();
        FearInterface fear = database.getFear();
        if(fear != null && pigeon.isCloseTo(fear.getPosition())) {
            PositionInterface positionToReach = pigeon.getPosition().getOpposition(fear.getPosition(), pigeonSecurityRange);
            pigeon.rushTo(positionToReach);
            Platform.runLater(layoutPigeonUpdater);
            return;
        }
        FoodInterface bestFood = null;
        for(FoodInterface food : foods) {
            if(food.getDurability() != 0) {
                if(bestFood == null || bestFood.getDurability() < food.getDurability()) {
                    bestFood = food;
                }
            }
        }
        if (bestFood != null){
            PositionInterface positionToReach = bestFood.getPosition();
            pigeon.moveTo(positionToReach);
            if(bestFood.getPosition().toString().equals(pigeon.getPosition().toString())) {
                bestFood.setEaten(true);
                eatenTrigger(pigeon, bestFood);
                database.updateFood(bestFood);
                Platform.runLater(layoutFoodUpdater);
            }
            database.updatePigeon(pigeon);
            Platform.runLater(layoutPigeonUpdater);
        }
    }

    private void eatenTrigger(PigeonInterface pigeon, FoodInterface food) { }

}
