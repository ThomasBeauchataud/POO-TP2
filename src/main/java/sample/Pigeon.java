package sample;

import static java.lang.Math.abs;

public class Pigeon {

    private Position position;
    private int id;
    private final int speed = 10;
    private final int timeToSleep = 10;
    private boolean sleeping;

    public Pigeon() { }

    public Pigeon(Position position) {
        this.position = position;
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

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public int getSpeed() {
        return speed;
    }

    public void moveTo(Position positionToReach) {
        int i = 0;
        while(i < this.speed) {
            int xDiff = positionToReach.getX() - this.position.getX();
            int yDiff = positionToReach.getY() - this.position.getY();
            if(abs(xDiff) < abs(yDiff)) {
                if(yDiff < 0) {
                    this.position.setY(this.position.getY() - 1);
                } else {
                    this.position.setY(this.position.getY() + 1);
                }
            } else {
                if(xDiff < 0) {
                    this.position.setX(this.position.getX() - 1);
                } else {
                    this.position.setX(this.position.getX() + 1);
                }
            }
            i++;
        }
    }

}
