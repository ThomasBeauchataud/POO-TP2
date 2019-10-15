package thread.layout;

import entity.PigeonInterface;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import managers.ConfigManager;
import thread.common.CatchThreadException;

public class LayoutPigeonUpdater implements Runnable {

    private static final int imageSize = ConfigManager.getInt("imageSize");

    private Pane root;
    private PigeonInterface pigeon;

    public LayoutPigeonUpdater(Pane root, PigeonInterface pigeon) {
        this.root = root;
        this.pigeon = pigeon;
    }

    @Override
    @CatchThreadException
    public void run() {
        ImageView pigeonImageView = pigeon.getImageView();
        pigeonImageView.setLayoutX(pigeon.getPosition().getX());
        pigeonImageView.setLayoutY(pigeon.getPosition().getY());
        GridPane.setConstraints(pigeonImageView, pigeon.getPosition().getX() / imageSize, pigeon.getPosition().getY() / imageSize);
        root.getChildren().remove(pigeonImageView);
        root.getChildren().add(pigeonImageView);
    }

}
