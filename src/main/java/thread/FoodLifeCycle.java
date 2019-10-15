package thread;

import entity.FoodInterface;
import managers.ConfigManager;
import thread.common.CatchThreadException;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("InfiniteLoopStatement")
public class FoodLifeCycle implements Runnable {

    private static final int frequency = ConfigManager.getInt("frequency");

    private FoodInterface food;

    public FoodLifeCycle(FoodInterface food) {
        this.food = food;
    }

    @Override
    @CatchThreadException
    public void run() {
        while(true) {
            try {
                execute();
                TimeUnit.MILLISECONDS.sleep(frequency);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void execute() {
        this.food.gettingOld();
    }

}
