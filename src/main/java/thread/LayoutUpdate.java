package thread;

import database.DatabaseInterface;
import entity.Pigeon;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.concurrent.TimeUnit;

import static sample.ImageManager.foodSize;
import static sample.LayoutManager.frequency;

public class LayoutUpdate implements Runnable {

    private DatabaseInterface database;
    private GridPane root;

    public LayoutUpdate(DatabaseInterface database, GridPane root) {
        this.database = database;
        this.root = root;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 1; i < database.getPigeonsCount(); i++) {
                ImageView pigeonImageView = (ImageView) root.getChildren().get(i);
                Pigeon pigeon = database.getPigeonById(i);
                pigeonImageView.setLayoutX(pigeon.getPosition().getX());
                pigeonImageView.setLayoutY(pigeon.getPosition().getY());
                GridPane.setConstraints(pigeonImageView, pigeon.getPosition().getX() / foodSize, pigeon.getPosition().getY() / foodSize);
                root.getChildren().remove(i);
                root.getChildren().add(i, pigeonImageView);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(frequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
