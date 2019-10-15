package managers;

import javafx.scene.image.Image;

import java.io.File;

public class ImageManager implements ImageManagerInterface {

    private Image pigeonImage;
    private Image foodImage;
    private Image fearImage;

    public ImageManager() {
        loadImage();
    }

    @Override
    public Image getFoodImage() {
        return foodImage;
    }

    @Override
    public Image getPigeonImage() {
        return pigeonImage;
    }

    @Override
    public Image getFearImage() {
        return fearImage;
    }

    private void loadImage() {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/pigeon.png");
        pigeonImage = new Image(file.toURI().toString());
        file = new File(System.getProperty("user.dir") + "/src/main/resources/food.png");
        foodImage = new Image(file.toURI().toString());
        file = new File(System.getProperty("user.dir") + "/src/main/resources/fear.png");
        fearImage = new Image(file.toURI().toString());
    }

}
