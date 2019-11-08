package entity;

/**
 * the Position of an Entity on the Pane
 */
public interface PositionInterface {

    /**
     * Return the X parameter of the position
     * @return int
     */
    int getX();

    /**
     * Set the X parameter of the position
     * If the parameter is out of the view, its reset in
     * @param x int
     */
    void setX(int x);

    /**
     * Return the Y parameter of the position
     * @return int
     */
    int getY();

    /**
     * Set the Y parameter of the position
     * If the parameter is out of the view, its reset in
     * @param y int
     */
    void setY(int y);

    /**
     * Return the opposed Position from the parameter position with this Position as symmetrical point at a minimal range
     * @param position PositionInterface the opposed Position
     * @param range int the minimal range
     * @return PositionInterface
     */
    PositionInterface getOpposition(PositionInterface position, int range);

}
