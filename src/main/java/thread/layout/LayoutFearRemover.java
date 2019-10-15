package thread.layout;

import database.DatabaseInterface;
import entity.FearInterface;
import javafx.scene.layout.Pane;
import thread.common.CatchThreadException;

public class LayoutFearRemover implements Runnable {

    private DatabaseInterface database;
    private Pane root;

    public LayoutFearRemover(DatabaseInterface database, Pane root) {
        this.database = database;
        this.root = root;
    }

    @Override
    @CatchThreadException
    public void run() {
        FearInterface fear = database.getFear();
        if(fear.isExpired()) {
            database.removeFear(fear);
            root.getChildren().remove(fear.getImageView());
        }
    }

}
