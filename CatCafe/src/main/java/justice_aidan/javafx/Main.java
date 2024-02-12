/*
Author: Aidan Justice
Class: CSC 468 Spring 2024
Description: This program is a simple cat cafe simulation. The user is able to purchase 4 different tile types: Empty,
Table, Cat, and Kitten. The simulator displays basic information about the store and individual information about each tile.
The user can resize the store and advance through the weeks by pressing the "Next Week" button.
Last Tier Passed: 2b.d

Complete the following checklist. If you partially completed an item, put a note how it can be checked for what is working for partial credit.


__X_ Followed the class OOP diagram
__X_ Observer pattern (ignores tiers)


__X_ 1.	Tier: Views and tile area
__X_ a. All objects (ignoring the sim area) (-1 for each missing)
__X_ b. Have a starting number of tiles in sim area
__X_ c. Able to add/remove a tile area properly (-33% for each error)
__X_ d. Info bar listed correctly with all the required elements (-25% for each error)
__X_ f. Tile Text correct in tile area (-25% per error)
__X_ g. Radio buttons update properly
__X_ h. Selecting a rectangle with “view” updates the tile area info (-50% per error)


__X_ 2a Tier: Advanced functionality
__X_ a. Next week button has some noticeable effect*
__X_ b. Tile areas updated properly on “next” (-33% per error)*
__X_ c. Sim info bar updated properly (-25% per error)
__X_ d. Selecting a tile after an update shows the new information


__X_ 2b: Layout
__X_ a. Location of all items in correct spot (-20% per error)
__X_ b. Layout still correct on window resize (-20%  for minor error)
__X_ c. Resize grid at minimum resets the grid and info (-50% if minor error)
__X_ d. Everything still working that is listed above with resize (-50% if minor error)

Final Tier: Extensions 30
Extension 1: 1b 15pt Tile-Info Observer : Tile area information tile automatically updates with tile area data changes.
Extension 2: 2a 5pt Mark Tile Being Shown: Can be tested by selecting a tile while having the "View" radio button selected.
Extension 3: 2c 10pt Add Image to Tile Info: Can be tested by adding one of each tile type and then viewing the image on each.

The grade you compute is the starting point for course staff, who reserve the right to change the grade if they disagree
with your assessment and to deduct points for other issues they may encounter,
such as errors in the submission process, naming issues, etc.
 */

package justice_aidan.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;
    private Layout layout;
    private CafeSim model;
    private Controller controller;

    /*******************************************************************************************************************
     * Description: Starts the Cat Cafe Simulation
     *
     * Parameters: Stage stage - javafx Stage
     ******************************************************************************************************************/
    @Override
    public void start(Stage stage) throws IOException {
        // Create MVC
        model = new CafeSim();
        layout = new Layout(model);
        controller = new Controller(model, layout);
        layout.setController(controller);

        stage.setScene(new Scene(layout.getRoot(), WIDTH, HEIGHT));

        // Fire initial property change
        model.resetModel(3);

        stage.setTitle("Cat Cafe Simulator");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
