package entity;

import managers.ConfigManager;

public class Food extends LayoutEntity implements FoodInterface {

    private static final int defaultDurability = ConfigManager.getInt("defaultDurability");

    private PositionInterface position;
    private int durability;
    private boolean eaten;

    public Food(PositionInterface position) {
        this.position = position;
        this.durability = defaultDurability;
        this.eaten = false;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public boolean isEaten() {
        return eaten;
    }

    @Override
    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    @Override
    public void gettingOld() {
        if(durability > 0) {
            this.durability--;
        }
    }

}
