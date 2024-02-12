/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: This file holds the Cat FloorArea type.
 ******************************************************************************************************************/
package justice_aidan.javafx;

public class Cat extends FloorArea{
    /*******************************************************************************************************************
     * Description: Cat Constructor
     *
     * Parameters: int weekNum - current week number
     * Returns: Cat
     ******************************************************************************************************************/
    public Cat(int weekNum) {
        super("Cat", 200, 30, 0, 52, weekNum, "cat.png");
    }

    /*******************************************************************************************************************
     * Description: Get the text to display on the button.
     *
     * Returns: String - Button text
     ******************************************************************************************************************/
    @Override
    public String getButtonString() {
        return super.getButtonString() + "\n" + (60 - getAge());
    }

    /*******************************************************************************************************************
     * Description: Get the overall details of the area
     *
     * Returns: String - floor area details.
     ******************************************************************************************************************/
    @Override
    public String toString() {
        int catAge = super.getAge();
        return super.toString() + "\nCat Age: " + catAge + "\nWeeks until adoption: " + (60 - catAge);
    }
}
