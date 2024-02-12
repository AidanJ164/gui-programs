/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: Holds the CafeSim class. This holds all the backend logic of the simulator.
 ******************************************************************************************************************/

package justice_aidan.javafx;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CafeSim {
    private int timeSinceReset = 0;
    private int funds = 0;
    private int size = 3;
    private PropertyChangeSupport subject;
    private ArrayList<Tile> tiles;
    private int numAdopted = 0;

    /*******************************************************************************************************************
     * Description: Constructor for the CafeSim. The CafeSim is the base model in the MVC Pattern. It creates and houses
     * all the necessary data and functions for the logic behind the simulator.
     *
     * Returns: CafeSim
     ******************************************************************************************************************/
    public CafeSim() {
        subject = new PropertyChangeSupport(this);
        tiles = new ArrayList<Tile>();
        // Make standard 3x3 grid
        for (int i = 0; i < 9; i++) {
            tiles.add(new Tile());
        }
    }

    /*******************************************************************************************************************
     * Description: Adds an observer to the model.
     *
     * Parameters: PropertyChangeListener obv - observer
     ******************************************************************************************************************/
    public void addObserver(PropertyChangeListener obv) {
        // GRADING: 1.B SUBJECT-TILE AREA
        subject.addPropertyChangeListener(obv);
    }

    /*******************************************************************************************************************
     * Description: Purchases the indicated tile type at the specified index.
     *
     * Parameters: int index - index in tile array to buy tile
     *             String type - type of tile to purchase
     ******************************************************************************************************************/
    public void buyTile(int index, String type) {
        // Buy tile
        tiles.get(index).changeType(type);
        // Subtract funds
        funds -= tiles.get(index).getInitialCost();

        // GRADING: 1.B TRIGGER-TILE AREA
        subject.firePropertyChange("buyTile", null, type);
    }

    public int getAdopted() {
        return numAdopted;
    }

    /*******************************************************************************************************************
     * Description: Get the amount of non-empty tile spaces.
     *
     * Returns: int - number of non-empty tiles
     ******************************************************************************************************************/
    public int getFilled() {
        int filled = 0;
        for (Tile tile: tiles) {
            if (tile.getName() != "Empty") {
                filled++;
            }
        }
        return filled;
    }

    public int getFunds() {
        return funds;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public int getTimeSinceReset() {
        return timeSinceReset;
    }

    /*******************************************************************************************************************
     * Description: Updates the sim for the next week. Subtract weekly cost from each tile and add any revenue from any
     * tables. Calls nextWeek() for each tile and alerts the View that information has changed.
     ******************************************************************************************************************/
    public void nextWeek() {
        timeSinceReset++;

        // Update each tile
        for (Tile tile: tiles) {
            funds -= tile.getWeeklyCost();
            funds += tile.getRevenue();
            tile.nextWeek();
            // Find out if animal was adopted to
            if (tile.gotAdopted()) {
                numAdopted++;
            }
        }
        // GRADING: 1.B TRIGGER-TILE AREA
        subject.firePropertyChange("nextWeek", null, null);
    }

    /*******************************************************************************************************************
     * Description: Resets the model when grid size changes.
     *
     * Parameters: int size - size of cafe grid
     ******************************************************************************************************************/
    public void resetModel(int size) {
        timeSinceReset = 0;
        funds = 0;
        this.size = size;
        setSize(size);
    }

    /*******************************************************************************************************************
     * Description: Set the selected tile and let the view know to update the tile information panel.
     *
     * Parameters: int index - index of selected tile
     ******************************************************************************************************************/
    public void setSelected(int index) {
        // GRADING: 1.B TRIGGER-TILE AREA
        subject.firePropertyChange("viewChange", null, index);
    }

    /*******************************************************************************************************************
     * Description: Set the size of the grid.
     *
     * Parameters: int size - size of grid
     ******************************************************************************************************************/
    public void setSize(int size) {
        this.size = size;

        tiles.clear();
        for (int i = 0; i < size * size; i++) {
            tiles.add(new Tile());
        }

        subject.firePropertyChange("sizeChange", null, size);
    }
}
