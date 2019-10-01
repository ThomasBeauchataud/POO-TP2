package sample;

import javafx.scene.image.Image;

import java.io.File;

public class ImageManager {

    public static Image foodImage = loadFoodImage();
    public static Image pigeonImage = loadPigeonImage();

    public static int pigeonSize = 60;
    public static int foodSize = 20;

    private static Image loadFoodImage() {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/food.png");
        return new Image(file.toURI().toString());
    }

    private static Image loadPigeonImage() {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/pigeon.png");
        return new Image(file.toURI().toString());
    }

}
