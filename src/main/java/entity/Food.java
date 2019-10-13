package entity;

public class Food {

    private Position position;
    private int id;
    private int durability;
    private boolean eaten;
    private final int speed = 1;

    public Food() { }

    public Food(Position position) {
        this.position = position;
        this.durability = 10;
        this.eaten = false;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public void gettingOld() {
        this.durability--;
    }

}
