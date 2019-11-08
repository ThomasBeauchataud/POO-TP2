package thread.layout;

import database.DatabaseInterface;
import entity.FearInterface;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import managers.ConfigManager;

public class LayoutFearDisplay implements Runnable {

    private static final int imageSize = ConfigManager.getInt("imageSize");

    private DatabaseInterface database;
    private Pane root;

    public LayoutFearDisplay(DatabaseInterface database, Pane root) {
        this.database = database;
        this.root = root;
    }

    @Override
    public void run() {
        FearInterface fear = database.getFear();
        ImageView fearImageView = fear.getImageView();
        fearImageView.setLayoutX(fear.getPosition().getX());
        fearImageView.setLayoutY(fear.getPosition().getY());
        GridPane.setConstraints(fearImageView, fear.getPosition().getX() / imageSize, fear.getPosition().getY() / imageSize);
        root.getChildren().add(fearImageView);
    }

}
