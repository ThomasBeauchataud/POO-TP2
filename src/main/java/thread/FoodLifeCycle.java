package thread;

import entity.Food;

public class FoodLifeCycle implements Runnable {

    private Food food;

    public FoodLifeCycle(Food food) {
        this.food = food;
    }

    @Override
    public void run() {
        while(true) {
            this.food.gettingOld();
            try {
                Thread.sleep(this.food.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
