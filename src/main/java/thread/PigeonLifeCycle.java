package thread;

import database.DatabaseInterface;

public class PigeonLifeCycle implements Runnable {

    private DatabaseInterface database;
    private int pigeonId;

    public PigeonLifeCycle(DatabaseInterface database, int pigeonId) {
        this.database = database;
        this.pigeonId = pigeonId;
    }

    @Override
    public void run() {

    }

}
