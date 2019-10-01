package thread;

import database.DatabaseInterface;
import entity.Pigeon;
import entity.Position;

public class PigeonMoveTo implements Runnable {

    private DatabaseInterface database;
    private int pigeonId;
    private Position positionToReach;

    public PigeonMoveTo(DatabaseInterface database, int pigeonId, Position positionToReach) {
        this.database = database;
        this.pigeonId = pigeonId;
        this.positionToReach = positionToReach;
    }

    @Override
    public void run() {
        Pigeon pigeon = database.getPigeonById(pigeonId);
        while(true) {
            pigeon.moveTo(positionToReach);
            database.updatePigeon(pigeon);
            try {
                Thread.sleep(pigeon.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
