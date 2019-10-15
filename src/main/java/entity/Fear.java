package entity;

import javafx.scene.image.ImageView;
import managers.ConfigManager;

public class Fear extends LayoutEntity implements FearInterface {

   private static final int gridSize = ConfigManager.getInt("gridSize");

    private PositionInterface position;
    private boolean expired;

    public Fear(ImageView imageView) {
        setImageView(imageView);
        position = new Position((int)(Math.random() * gridSize), (int)(Math.random() * gridSize));
        expired = false;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

}
