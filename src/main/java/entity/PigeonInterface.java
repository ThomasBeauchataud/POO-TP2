package entity;

public interface PigeonInterface extends LayoutEntityInterface {

    /**
     * Return the position of the Pigeon
     * @return PositionInterface
     */
    PositionInterface getPosition();

    /**
     * Return true if a Pigeon is too close to something at a Position
     * The proximity factor can be set in configurations
     * @param position PositionInterface
     * @return boolean
     */
    boolean isCloseTo(PositionInterface position);

    /**
     * Move a Pigeon to the Position at a high speed
     * The speed coefficient can be set in configuration
     * @param positionToReach PositionInterface
     */
    void rushTo(PositionInterface positionToReach);

    /**
     * Move a Pigeon to the Position at his normal speed
     * @param positionToReach PositionInterface
     */
    void moveTo(PositionInterface positionToReach);

}
