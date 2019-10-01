package database;

import sample.Food;
import sample.Pigeon;

import java.util.ArrayList;

public class ClassDatabase implements DatabaseInterface {

    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<Pigeon> pigeonList = new ArrayList<>();

    @Override
    public Pigeon getPigeonById(int id) {
        return pigeonList.get(id - 1);
    }

    @Override
    public int addPigeon(Pigeon pigeon) {
        int id = pigeonList.size();
        pigeon.setId(id);
        pigeonList.add(id, pigeon);
        return id;
    }

    @Override
    public void updatePigeon(Pigeon pigeon) {
        pigeonList.add(pigeon.getId(), pigeon);
    }

    @Override
    public Food getFoodById(int id) {
        return foodList.get(id - 1);
    }

    @Override
    public int addFood(Food food) {
        int id = foodList.size();
        food.setId(id);
        foodList.add(foodList.size(), food);
        return id;
    }

    @Override
    public void updateFood(Food food) {
        foodList.add(food.getId(), food);
    }
}
