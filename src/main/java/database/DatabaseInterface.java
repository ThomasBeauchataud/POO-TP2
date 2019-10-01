package database;

import sample.Food;
import sample.Pigeon;

public interface DatabaseInterface {

    public Pigeon getPigeonById(int id);

    public int addPigeon (Pigeon pigeon);

    public void updatePigeon (Pigeon pigeon);

    public Food getFoodById(int id);

    public int addFood (Food food);

    public void updateFood (Food food);
}
