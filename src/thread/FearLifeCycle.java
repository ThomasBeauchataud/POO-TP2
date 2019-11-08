package thread;

import database.DatabaseInterface;
import entity.Fear;
import entity.FearInterface;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import managers.ConfigManager;
import managers.ImageManagerInterface;
import thread.layout.LayoutFearDisplay;
import thread.layout.LayoutFearRemover;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("InfiniteLoopStatement")
public class FearLifeCycle implements Runnable {

    private static final int fearTime = ConfigManager.getInt("fearTime");
    private static final int fearFrequency = ConfigManager.getInt("fearFrequency");

    private DatabaseInterface database;
    private ImageManagerInterface imageManager;
    private Runnable layoutFearRemover;
    private Runnable layoutFearDisplay;

    public FearLifeCycle(DatabaseInterface database, Pane root, ImageManagerInterface imageManager) {
        this.database = database;
        this.imageManager = imageManager;
        this.layoutFearRemover = new LayoutFearRemover(database, root);
        this.layoutFearDisplay = new LayoutFearDisplay(database, root);
    }

    @Override
    public void run() {
        while(true) {
            try {
                TimeUnit.MILLISECONDS.sleep(fearTime + (int)(Math.random()*(fearFrequency - fearTime)));
                execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void execute() throws Exception {
        FearInterface fear = new Fear(new ImageView(imageManager.getFearImage()));
        database.updateFear(fear);
        Platform.runLater(layoutFearDisplay);
        TimeUnit.MILLISECONDS.sleep(fearTime);
        fear.setExpired(true);
        database.updateFear(fear);
        Platform.runLater(layoutFearRemover);
    }

}
