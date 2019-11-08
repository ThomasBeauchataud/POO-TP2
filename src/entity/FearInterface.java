package entity;

public interface FearInterface extends LayoutEntityInterface {

    /**
     * Return the Position of the Fear
     * The Position is created randomly
     * @return PositionInterface
     */
    PositionInterface getPosition();

    /**
     * Return true is the Fear doesn't exists anymore
     * @return boolean
     */
    boolean isExpired();

    /**
     * Set if the Fear is still present or not
     * @param expired boolean
     */
    void setExpired(boolean expired);

}
