/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: This file holds the Controller class. It is the Controller in the MVC Pattern. It creates the event
 * handlers for the simulator buttons.
 ******************************************************************************************************************/
package justice_aidan.javafx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class Controller {
    private Layout layout;
    private CafeSim model;

    /*******************************************************************************************************************
     * Description: Constructor for the Controller. This binds the layout to the model to the layout by creating
     * event filters for the layout buttons.
     *
     * Parameters: CafeSim model - backend logic
     *             Layout layout - view
     * Returns: Controller
     ******************************************************************************************************************/
    public Controller(CafeSim model, Layout layout) {
        this.model = model;
        this.layout = layout;

        this.layout.getNextWeekButton().addEventFilter(ActionEvent.ACTION, new NextWeekListener());
        this.layout.getThreeGridButton().addEventFilter(ActionEvent.ACTION, new ChangeGridSize(3));
        this.layout.getFiveGridButton().addEventFilter(ActionEvent.ACTION, new ChangeGridSize(5));
        this.layout.getNineGridButton().addEventFilter(ActionEvent.ACTION, new ChangeGridSize(9));
        addTileEvents();
    }

    /*******************************************************************************************************************
     * Description: Add event filters to the Tile buttons.
     ******************************************************************************************************************/
    public void addTileEvents() {
        CafeSimView simView = layout.getCafeSimView();
        ObservableList<Node> tiles = simView.getChildren();
        for (Node tile: tiles) {
            tile.addEventFilter(ActionEvent.ACTION, new TileListener(tile));
        }
    }


    private class TileListener implements EventHandler<ActionEvent> {
        Node tile;

        /*******************************************************************************************************************
         * Description: Constructor for the TileListener.
         *
         * Parameters: Node tile - tile to add an event to
         * Returns: TileListener
         ******************************************************************************************************************/
        public TileListener(Node tile) {
            this.tile = tile;
        }

        /*******************************************************************************************************************
         * Description: Function to run when button is pressed.
         *
         * Parameters: ActionEvent e - action type
         ******************************************************************************************************************/
        @Override
        public void handle(ActionEvent e) {
            String radioSelection = layout.getRadioSelected();
            int index = Integer.parseInt(tile.getId());

            // If view is not selected, buy a tile
            if (!radioSelection.equals("View")) {
                model.buyTile(index, radioSelection);
            }
            // Specify what tile to show in tile info area
            else {
                model.setSelected(index);
            }
        }
    }


    private class NextWeekListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            model.nextWeek();
        }
    }

    private class ChangeGridSize implements EventHandler<ActionEvent> {
        private int size;

        /*******************************************************************************************************************
         * Description: Constructor for ChangeGridSize EventHandler.
         *
         * Parameters: int size - size to change the grid to
         * Returns: ChangeGridSize
         ******************************************************************************************************************/
        public ChangeGridSize(int size) {
            this.size = size;
        }
        @Override
        public void handle(ActionEvent e) {
            model.resetModel(size);
        }
    }
}


