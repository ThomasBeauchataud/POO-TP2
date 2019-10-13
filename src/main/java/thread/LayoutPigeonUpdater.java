package thread;

import database.DatabaseInterface;
import entity.Pigeon;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import static sample.ImageManager.foodSize;

public class LayoutPigeonUpdater implements Runnable {

    private DatabaseInterface database;
    private GridPane root;
    private int id;

    LayoutPigeonUpdater(DatabaseInterface database, GridPane root, int id) {
        this.database = database;
        this.root = root;
        this.id = id;
    }

    @Override
    public void run() {
        ImageView pigeonImageView = (ImageView) root.getChildren().get(id + 1);
        Pigeon pigeon = database.getPigeonById(id);
        pigeonImageView.setLayoutX(pigeon.getPosition().getX());
        pigeonImageView.setLayoutY(pigeon.getPosition().getY());
        GridPane.setConstraints(pigeonImageView, pigeon.getPosition().getX() / foodSize, pigeon.getPosition().getY() / foodSize);
        root.getChildren().remove(id + 1);
        root.getChildren().add(id + 1, pigeonImageView);
    }

}
