package database;

import entity.Food;
import entity.Pigeon;
import entity.Position;

import java.util.ArrayList;

public class ClassDatabase implements DatabaseInterface {

    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<Pigeon> pigeonList = new ArrayList<>();

    public ClassDatabase() {
        this.loadPigeon();
    }

    @Override
    public Pigeon getPigeonById(int id) {
        return pigeonList.get(id);
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
        pigeonList.set(pigeon.getId(), pigeon);
    }

    @Override
    public ArrayList<Pigeon> getPigeonList() {
        return this.pigeonList;
    }

    @Override
    public Food getFoodById(int id) {
        return foodList.get(id);
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

    @Override
    public Food[] getFoods() {
        return foodList.toArray(new Food[0]);
    }

    @Override
    public int getPigeonsCount() {
        return pigeonList.size();
    }

    private void loadPigeon () {
        addPigeon(new Pigeon(
                new Position(0, 0)
        ));
        /*addPigeon(new Pigeon(
            new Position(0, gridSize - pigeonSize)
        ));
        addPigeon(new Pigeon(
            new Position(gridSize - pigeonSize, 0)
        ));
        addPigeon(new Pigeon(
            new Position(gridSize - pigeonSize, gridSize - pigeonSize)
        ));*/
    }

}
