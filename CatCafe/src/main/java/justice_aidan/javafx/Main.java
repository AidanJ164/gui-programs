/*
Complete the following checklist. If you partially completed an item, put a note how it can be checked for what is working for partial credit.


____ Followed the class OOP diagram
____ Observer pattern (ignores tiers)


____ 1.	Tier: Views and tile area
____ a. All objects (ignoring the sim area) (-1 for each missing)
____ b. Have a starting number of tiles in sim area
____ c. Able to add/remove a tile area properly (-33% for each error)
____ d. Info bar listed correctly with all the required elements (-25% for each error)
____ f. Tile Text correct in tile area (-25% per error)
____ g. Radio buttons update properly
____ h. Selecting a rectangle with “view” updates the tile area info (-50% per error)


____ 2a Tier: Advanced functionality
____ a. Next week button has some noticeable effect*
____ b. Tile areas updated properly on “next” (-33% per error)*
____ c. Sim info bar updated properly (-25% per error)
____ d. Selecting a tile after an update shows the new information


____ 2b: Layout
____ a. Location of all items in correct spot (-20% per error)
____ b. Layout still correct on window resize (-20%  for minor error)
____ c. Resize grid at minimum resets the grid and info (-50% if minor error)
____ d. Everything still working that is listed above with resize (-50% if minor error)



Final Tier: Extensions 30
Extension 1: <number> <points> <name>: <how to test/find if applicable>
Extension 2: <number> <points> <name>: <how to test/find if applicable>
Etc.


The grade you compute is the starting point for course staff, who reserve the right to change the grade if they disagree with your assessment and to deduct points for other issues they may encounter, such as errors in the submission process, naming issues, etc.

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


    @Override
    public void start(Stage stage) throws IOException {
        model = new CafeSim();
        layout = new Layout(model);
        controller = new Controller(model, layout);
        layout.setController(controller);

        stage.setScene(new Scene(layout.getRoot(), WIDTH, HEIGHT));

        stage.setTitle("Cat Cafe Simulator");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
