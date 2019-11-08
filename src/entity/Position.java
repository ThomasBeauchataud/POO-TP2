package entity;

import managers.ConfigManager;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Position implements PositionInterface {

    private static final int gridSize = ConfigManager.getInt("gridSize");

    private int x;
    private int y;

    public Position(int x, int y) {
        if(x < 0) {
            x = 0;
        }
        if(x > gridSize) {
            x = gridSize;
        }
        if(y < 0) {
            y = 0;
        }
        if(y > gridSize) {
            y = gridSize;
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        if(x < 0) {
            x = 0;
        }
        if(x > gridSize) {
            x = gridSize;
        }
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        if(y < 0) {
            y = 0;
        }
        if(y > gridSize) {
            y = gridSize;
        }
        this.y = y;
    }

    @Override
    public PositionInterface getOpposition(PositionInterface position, int range) {
        double vecX = this.getX() - position.getX();
        double vecY = this.getY() - position.getY();
        double norm = sqrt(pow(vecX,2) + pow(vecY,2));
        double k = sqrt((range - norm)*1.0 / norm*1.0);
        int vecRealX = (int)(vecX * k);
        int vecRealY = (int)(vecY * k);
        return new Position(this.getX() + vecRealX, this.getY() + vecRealY);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
