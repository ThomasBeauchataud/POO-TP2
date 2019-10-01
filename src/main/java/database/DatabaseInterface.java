package database;

import sample.Pigeon;

public interface DatabaseInterface {

    public Pigeon getPigeonById(int id);

    public int addPigeon (Pigeon pigeon);

    public void updatePigeon (Pigeon pigeon);


}
