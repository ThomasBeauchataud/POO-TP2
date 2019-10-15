package database;

import entity.*;

/**
 * The database to store Objects
 */
public interface DatabaseInterface {

    /**
     * Add a Pigeon in the database
     * @param pigeon PigeonInterface
     */
    void addPigeon (PigeonInterface pigeon);

    /**
     * Update an existing Pigeon in the database
     * @param pigeon PigeonInterface
     */
    void updatePigeon (PigeonInterface pigeon);

    /**
     * Return the list of Pigeons
     * @return PigeonInterface[]
     */
    PigeonInterface[] getPigeons();

    /**
     * Add a Food in the database
     * @param food FoodInterface
     */
    void addFood (FoodInterface food);

    /**
     * Update an existing Food in the database
     * @param food FoodInterface
     */
    void updateFood (FoodInterface food);

    /**
     * Remove an existing Food in from database
     * @param food FoodInterface
     */
    void removeFood(FoodInterface food);

    /**
     * Return the list of Foods
     * @return FoodInterface[]
     */
    FoodInterface[] getFoods();

    /**
     * Remove the Fear from the database
     * @param fear FearInterface
     */
    void removeFear(FearInterface fear);

    /**
     * Update the Fear in the database
     * @param fear FearInterface
     */
    void updateFear(FearInterface fear);

    /**
     * Get the Fear from the database
     * @return FearInterface
     */
    FearInterface getFear();

}
