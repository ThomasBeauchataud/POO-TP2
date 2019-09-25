package sample;

public class Pigeon {

    private Position position;
    private int speed;
    private int timeToSleep;
    private boolean sleeping;

    public Pigeon() { }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTimeToSleep() {
        return timeToSleep;
    }

    public void setTimeToSleep(int timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public void moveTo(Position positionToReach) {

    }

}
