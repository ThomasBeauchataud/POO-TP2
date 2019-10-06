package thread;

import database.DatabaseInterface;
import entity.Food;
import entity.Pigeon;
import entity.Position;

import java.util.concurrent.TimeUnit;

import static sample.LayoutManager.frequency;

public class PigeonLifeCycle implements Runnable {

    private DatabaseInterface database;
    private int pigeonId;

    public PigeonLifeCycle(DatabaseInterface database, int pigeonId) {
        this.database = database;
        this.pigeonId = pigeonId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                lookForFood();
                TimeUnit.MILLISECONDS.sleep(frequency);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
            database.updatePigeon(pigeon);
            return;
        }
        Position positionToReach = bestFood.getPosition();
        pigeon.moveTo(positionToReach);

    }

}
