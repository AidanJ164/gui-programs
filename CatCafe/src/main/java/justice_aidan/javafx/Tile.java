/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: Holds the Tile Class. Tile is observed by TileView so that information can be displayed to the
 * screen when updated. It holds a FloorArea type.
 ******************************************************************************************************************/
package justice_aidan.javafx;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Tile {
    private FloorArea floorArea;
    private PropertyChangeSupport subject;
    private int weekNum = 0;
    private boolean adopted = false;

    /*******************************************************************************************************************
     * Description: Tile Constructor. Holds a floor area and is the subject that is observed by the TileView.
     *
     * Returns: Tile
     ******************************************************************************************************************/
    public Tile() {
        subject = new PropertyChangeSupport(this);
        floorArea = new Empty(0);
    }

    /*******************************************************************************************************************
     * Description: Add an observer to watch for changes in the Tile.
     *
     * Parameters: PropertyChangeListener obv - observer
     ******************************************************************************************************************/
    public void addObserver(PropertyChangeListener obv) {
        // GRADING: SUBJECT
        subject.addPropertyChangeListener(obv);
        // initialize the Button text
        // GRADING: TRIGGER
        subject.firePropertyChange("init", null, null);
    }

    /*******************************************************************************************************************
     * Description: Change the FloorArea type.
     *
     * Parameters: String type - type of FloorArea to set
     ******************************************************************************************************************/
    public void changeType(String type) {
        switch (type){
            case "Cat":
                floorArea = new Cat(weekNum);
                break;
            case "Kitten":
                floorArea = new Kitten(weekNum);
                break;
            case "Table":
                floorArea = new Table(weekNum);
                break;
            case "Empty":
                floorArea = new Empty(weekNum);
                break;
        }
        // GRADING: TRIGGER
        subject.firePropertyChange("typeChange", null, type);
    }
    public String getButtonString() {
        return floorArea.getButtonString();
    }

    public String getDetails() {
        return floorArea.toString();
    }
    public String getImageURL() {
        return floorArea.getImageURL();
    }
    public int getInitialCost() {
        return floorArea.getInitialCost();
    }
    public String getName() {
        return floorArea.getName();
    }
    public int getRevenue() {
        return floorArea.getRevenue();
    }

    public int getWeeklyCost() {
        return floorArea.getWeeklyCost();
    }

    public boolean gotAdopted() {
        return adopted;
    }

    /*******************************************************************************************************************
     * Description: Update tile on a new week.
     ******************************************************************************************************************/
    public void nextWeek() {
        // Update floorArea
        floorArea.nextWeek();
        weekNum++;
        adopted = false;

        // If cat, check age == 60
        if (floorArea.getName().equals("Cat") && floorArea.getAge() == 60) {
            floorArea.setAge(52);
            adopted = true;
        }
        // If kitten, check age == 14
        else if (floorArea.getName().equals("Kitten") && floorArea.getAge() == 14) {
            floorArea.setAge(10);
            adopted = true;
        }

        // GRADING: TRIGGER
        subject.firePropertyChange("nextWeek", null, floorArea.getAge());
    }
}
