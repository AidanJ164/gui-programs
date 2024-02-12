/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: Holds the Table FloorArea type. Table is the only floor type that has revenue every week.
 ******************************************************************************************************************/
package justice_aidan.catcafe;

public class Table extends FloorArea {

    /*******************************************************************************************************************
     * Description: Table type constructor.
     *
     * Parameters: int weekNum - current week number
     * Returns: Table
     ******************************************************************************************************************/
    public Table(int weekNum) {
        super("Table", 300, 50, 150, 0, weekNum, "table.png");
    }

    /*******************************************************************************************************************
     * Description: Get the text to display on the button.
     *
     * Returns: String - Button text
     ******************************************************************************************************************/
    @Override
    public String getButtonString() {
        return super.getButtonString() + "\n$" + getRevenue();
    }

    /*******************************************************************************************************************
     * Description: Get the overall details of the area
     *
     * Returns: String - floor area details.
     ******************************************************************************************************************/
    @Override
    public String toString() {
        return super.toString() + "\nTotal Revenue: " + super.getTotalRevenue();
    }
}
