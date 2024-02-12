/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: Holds the Kitten FloorArea type.
 ******************************************************************************************************************/
package justice_aidan.catcafe;

public class Kitten extends FloorArea {

    /*******************************************************************************************************************
     * Description: Kitten tile Constructor
     *
     * Parameters: int weekNum - current week number
     * Returns: Kitten
     ******************************************************************************************************************/
    public Kitten(int weekNum) {
        super("Kitten", 200, 20, 0, 10, weekNum, "cat.png");
    }

    /*******************************************************************************************************************
     * Description: Get the text to display on the button.
     *
     * Returns: String - Button text
     ******************************************************************************************************************/
    @Override
    public String getButtonString() {
        return super.getButtonString() + "\n" + (14 - getAge());
    }

    /*******************************************************************************************************************
     * Description: Get the overall details of the area
     *
     * Returns: String - floor area details.
     ******************************************************************************************************************/
    @Override
    public String toString() {
        int catAge = super.getAge();
        return super.toString() + "\nCat Age: " + catAge + "\nWeeks until adoption: " + (14 - catAge);
    }
}
