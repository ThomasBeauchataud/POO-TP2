package entity;

import managers.ConfigManager;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.abs;

public class Pigeon extends LayoutEntity implements PigeonInterface {

    private static final int speed = ConfigManager.getInt("pigeonSpeed");
    private static final int pigeonSecurityRange = ConfigManager.getInt("pigeonSecurityRange");
    private static final int highSpeed = ConfigManager.getInt("highSpeed");

    private PositionInterface position;

    public Pigeon(PositionInterface position) {
        this.position = position;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

    @Override
    public boolean isCloseTo(PositionInterface position) {
        int xDiff = position.getX() - this.position.getX();
        int yDiff = position.getY() - this.position.getY();
        return sqrt(pow(xDiff, 2) + pow(yDiff, 2)) < pigeonSecurityRange;
    }

    @Override
    public void rushTo(PositionInterface positionToReach) {
        int i = 0;
        while(i < speed * highSpeed && !positionToReach.toString().equals(position.toString())) {
            move(positionToReach);
            i++;
        }
    }

    @Override
    public void moveTo(PositionInterface positionToReach) {
        int i = 0;
        while(i < speed && !positionToReach.toString().equals(position.toString())) {
            move(positionToReach);
            i++;
        }
    }

    private void move(PositionInterface positionToReach) {
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
    }

}
