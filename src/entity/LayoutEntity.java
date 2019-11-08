package entity;

import javafx.scene.image.ImageView;

@SuppressWarnings("WeakerAccess")
abstract class LayoutEntity {

    private ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

}
