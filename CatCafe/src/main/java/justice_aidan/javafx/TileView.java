/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: Holds the TileView class. TileView is the observer of Tile. It watches for changes in a tile to update
 * its text whenever a change is made to the Tile.
 ******************************************************************************************************************/
package justice_aidan.javafx;

import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TileView extends Button implements PropertyChangeListener{
    private Tile tile;

    /*******************************************************************************************************************
     * Description: TileView Constructor. Observes a Tile and checks for data changes so that it can update the text
     * on the button.
     *
     * Parameters: Tile tile - tile to observe
     *             int index - index in Tile array to set as ID
     * Returns: TileView
     ******************************************************************************************************************/
    public TileView(Tile tile, int index) {
        this.setMaxWidth(1000000);
        this.setMaxHeight(1000000);
        this.tile = tile;
        setTextAlignment(TextAlignment.CENTER);
        tile.addObserver(this);
        setId(Integer.toString(index));
    }

    /*******************************************************************************************************************
     * Description: Describes what to do when the Tile has an update. Sets the text of the button.
     *
     * Parameters: PropertyChangeEvent e - describes the change event
     ******************************************************************************************************************/
    // GRADING: OBSERVE
    public void propertyChange(PropertyChangeEvent e) {
        setText(tile.getButtonString());
    }

    /*******************************************************************************************************************
     * Description: Gets the text for the tile information panel.
     *
     * Returns: String - tile details
     ******************************************************************************************************************/
    @Override
    public String toString() {
        return tile.getDetails();
    }
}
