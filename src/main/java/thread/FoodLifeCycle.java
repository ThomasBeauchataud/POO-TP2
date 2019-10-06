package thread;

import entity.Food;

import java.util.concurrent.TimeUnit;

import static sample.LayoutManager.frequency;

public class FoodLifeCycle implements Runnable {

    private Food food;

    public FoodLifeCycle(Food food) {
        this.food = food;
    }

    @Override
    public void run() {
        try {
            while(true) {
                this.food.gettingOld();
                TimeUnit.MILLISECONDS.sleep(frequency);
                TimeUnit.MILLISECONDS.sleep(food.getSpeed());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
