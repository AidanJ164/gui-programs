/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: This file holds the Empty FloorArea type.
 ******************************************************************************************************************/
package justice_aidan.javafx;

public class Empty extends FloorArea {

    /*******************************************************************************************************************
     * Description: Empty tile Constructor
     *
     * Parameters: int weekNum - current week number
     * Returns: Empty
     ******************************************************************************************************************/
    public Empty(int weekNum) {
        super("Empty", 200, 10, 0, 0, weekNum, "empty.png");
    }
}
