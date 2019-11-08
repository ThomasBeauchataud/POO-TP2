package database;

import entity.*;
import managers.ConfigManager;

import java.util.ArrayList;

public class ClassDatabase implements DatabaseInterface {

    private static final int gridSize = ConfigManager.getInt("gridSize");
    private static final int imageSize = ConfigManager.getInt("imageSize");

    private ArrayList<FoodInterface> foodList;
    private ArrayList<PigeonInterface> pigeonList;
    private FearInterface fear;

    public ClassDatabase() {
        foodList = new ArrayList<>();
        pigeonList = new ArrayList<>();
        this.loadPigeon(); }

    @Override
    public void addPigeon(PigeonInterface pigeon) {
        pigeonList.add(pigeon);
    }

    @Override
    public void updatePigeon(PigeonInterface pigeon) {
        pigeonList.set(pigeonList.indexOf(pigeon), pigeon);
    }

    @Override
    public PigeonInterface[] getPigeons() {
        return this.pigeonList.toArray(new PigeonInterface[0]);
    }

    @Override
    public void addFood(FoodInterface food) {
        foodList.add(food);
    }

    @Override
    public void updateFood(FoodInterface food) {
        foodList.set(foodList.indexOf(food), food);
    }

    @Override
    public void removeFood(FoodInterface food) {
        foodList.remove(food);
    }

    @Override
    public FoodInterface[] getFoods() {
        return foodList.toArray(new FoodInterface[0]);
    }


    @Override
    public void removeFear(FearInterface fear) {
        this.fear = null;
    }

    @Override
    public void updateFear(FearInterface fear) {
        this.fear = fear;
    }

    @Override
    public FearInterface getFear() {
        return this.fear;
    }

    private void loadPigeon () {
        addPigeon(new Pigeon(new Position(0, 0)));
        addPigeon(new Pigeon(new Position(0, gridSize - imageSize)));
    }

}
