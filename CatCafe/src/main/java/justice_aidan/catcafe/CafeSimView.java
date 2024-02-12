/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: This holds the CafeSimView class. It handles the button grid portion of the layout.
 ******************************************************************************************************************/
package justice_aidan.catcafe;

import javafx.scene.layout.*;
import java.util.ArrayList;

public class CafeSimView extends GridPane {

    /*******************************************************************************************************************
     * Description: Constructor for CafeSimView. Creates the View for the grid of buttons.
     *
     * Parameters: CafeSim model - backend logic of the simulator
     * Returns: CafeSimView
     ******************************************************************************************************************/
    public CafeSimView(CafeSim model) {
        makeTiles(model.getTiles());
        setStyle("-fx-background-color: #bd9982");
    }

    public String getTileInfo(int index) {
        return getChildren().get(index).toString();
    }

    /*******************************************************************************************************************
     * Description: Create the TileView Buttons for each tile space.
     *
     * Parameters: ArrayList<Tile> floorTiles - list of Tiles
     ******************************************************************************************************************/
    public void makeTiles(ArrayList<Tile> floorTiles) {
        int size = (int)Math.sqrt(floorTiles.size());

        // Clear old tiles and constraints
        getChildren().clear();
        getRowConstraints().clear();
        getColumnConstraints().clear();

        // Create row and column constraints
        for (int i = 0; i < size; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0/size);
            getRowConstraints().add(rc);
        }
        for (int i = 0; i < size; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0/size);
            getColumnConstraints().add(cc);
        }

        // Create TileViews
        for (int i = 0; i < size * size; i++) {
            add(new TileView(floorTiles.get(i), i), i % size, i / size);
        }
    }

    public void removeSelected(int index) {
        getChildren().get(index).setStyle("");
    }

    public void setSelected(int index) {
        getChildren().get(index).setStyle("-fx-border-color: red");
    }
}
