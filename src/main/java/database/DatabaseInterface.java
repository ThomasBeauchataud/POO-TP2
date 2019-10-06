package database;

import entity.Food;
import entity.Pigeon;

import java.util.ArrayList;

public interface DatabaseInterface {

    public Pigeon getPigeonById(int id);

    public int addPigeon (Pigeon pigeon);

    public void updatePigeon (Pigeon pigeon);

    ArrayList<Pigeon> getPigeonList();

    public Food getFoodById(int id);

    public int addFood (Food food);

    public void updateFood (Food food);

    Food[] getFoods();

}
