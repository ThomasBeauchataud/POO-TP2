package entity;

public interface FoodInterface extends LayoutEntityInterface {

    /**
     * Return the Position of the Food
     * @return PositionInterface
     */
    PositionInterface getPosition();

    /**
     * Return the durability of the Food
     * When the food is created, its equals to 10
     * @return int
     */
    int getDurability();

    /**
     * Return true if the Food has been eaten by a Pigeon
     * @return boolean
     */
    boolean isEaten();

    /**
     * Set if a Food has been eaten of not
     * @param eaten boolean
     */
    void setEaten(boolean eaten);

    /**
     * Decrease the durability of the Food
     */
    void gettingOld();

}
