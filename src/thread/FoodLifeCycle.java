package thread;

import entity.FoodInterface;
import managers.ConfigManager;

import java.util.concurrent.TimeUnit;

public class FoodLifeCycle implements Runnable {

    private static final int frequency = ConfigManager.getInt("frequency");

    private FoodInterface food;
    private boolean firstEnd = true;

    public FoodLifeCycle(FoodInterface food) {
        this.food = food;
    }

    @Override
    public void run() {
        while(true) {
            try {
                execute();
                if(food.getDurability() == 0) {
                    if(firstEnd) {
                        firstEnd = false;
                    } else {
                        break;
                    }
                }
                TimeUnit.MILLISECONDS.sleep(frequency);
                if(food.isEaten()) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void execute() {
        this.food.gettingOld();
    }

}
