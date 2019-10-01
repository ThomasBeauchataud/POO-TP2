package entity;

public class Food {

    private Position position;
    private int id;
    private int durability;
    private final int speed = 1;

    public Food() { }

    public Food(Position position) {
        this.position = position;
        this.durability = 10;
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

    public int getSpeed() {
        return speed;
    }

    public void gettingOld() {
        this.durability--;
    }

}
