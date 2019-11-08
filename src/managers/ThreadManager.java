package managers;

import database.DatabaseInterface;
import entity.PigeonInterface;
import javafx.scene.layout.Pane;
import thread.FearLifeCycle;
import thread.PigeonLifeCycle;

public class ThreadManager implements ThreadManagerInterface {

    private DatabaseInterface database;
    private Pane root;
    private ImageManagerInterface imageManager;

    public ThreadManager(DatabaseInterface database, Pane root, ImageManagerInterface imageManager) {
        this.database = database;
        this.root = root;
        this.imageManager = imageManager;
    }

    @Override
    public void start() {
        for(PigeonInterface pigeon : database.getPigeons()) {
            new Thread(new PigeonLifeCycle(database, pigeon, root)).start();
        }
        new Thread(new FearLifeCycle(database, root, imageManager)).start();
    }
}
