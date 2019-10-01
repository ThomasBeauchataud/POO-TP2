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
        pigeonList.add(pigeonList.size(), pigeon);
        return pigeonList.size() - 1;
    }

    @Override
    public void updatePigeon(Pigeon pigeon) {
        pigeonList.add(pigeon.getId(), pigeon);
    }
}
