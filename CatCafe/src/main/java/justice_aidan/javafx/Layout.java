/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: Holds the overall layout of the simulator. It is the main View portion in the MVC pattern. It provides
 * a GUI for the user to interact with the simulator.
 ******************************************************************************************************************/
package justice_aidan.javafx;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;

public class Layout implements PropertyChangeListener {
    private BorderPane root;
    private VBox leftVBox;
    private Controller controller;
    private CafeSimView view;
    private Button nextWeek;
    private Button threeGrid;
    private Button fiveGrid;
    private Button nineGrid;
    private CafeSim model;
    private ToggleGroup group;
    private Text topDetails;
    private Text leftDetails;
    private int selected = 0;
    private ImageView infoImage;

    /*******************************************************************************************************************
     * Description: Layout Constructor. Root View for the MVC pattern. Provides the base look of the GUI for the cafe.
     *
     * Parameters: CafeSim model - backend logic of sim
     * Returns: Layout
     ******************************************************************************************************************/
    public Layout(CafeSim model) {
        this.model = model;
        this.model.addObserver(this);
        view = new CafeSimView(model);

        root = new BorderPane();
        root.setMaxHeight(Double.MAX_VALUE);
        root.setMaxWidth(Double.MAX_VALUE);

        // Create different panels
        root.setTop(makeTopDetails());
        makeLeftDetails();
        root.setLeft(leftVBox);
        root.setCenter(view);
        root.setBottom(makeBottomDetails());
    }

    public CafeSimView getCafeSimView() {
        return view;
    }

    public Button getFiveGridButton() {
        return fiveGrid;
    }

    public Button getNextWeekButton() {
        return nextWeek;
    }

    public Button getNineGridButton() {
        return nineGrid;
    }

    public String getRadioSelected(){
        return ((RadioButton)(group.getSelectedToggle())).getText();
    }

    public BorderPane getRoot() {
        return root;
    }

    public Button getThreeGridButton() {
        return threeGrid;
    }

    /*******************************************************************************************************************
     * Description: Make the bottom panel. This panel contains the radio buttons for buying tiles, next week button, and
     * resize buttons.
     *
     * Returns: Node - bottom panel
     ******************************************************************************************************************/
    private Node makeBottomDetails() {
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        ObservableList<Node> rows = bottom.getChildren();

        // Create radio buttons and add to VBox
        rows.add(makeToggleGroup());

        // Create next week and resize buttons
        HBox options = new HBox();
        nextWeek = new Button("Next Week");
        options.getChildren().add(nextWeek);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        options.getChildren().add(spacer);

        options.getChildren().add(new Text("Resize:"));
        threeGrid = new Button("3x3");
        fiveGrid = new Button("5x5");
        nineGrid = new Button("9x9");
        options.getChildren().add(threeGrid);
        options.getChildren().add(fiveGrid);
        options.getChildren().add(nineGrid);
        rows.add(options);
        bottom.setStyle("-fx-background-color: #806859");

        return bottom;
    }

    /*******************************************************************************************************************
     * Description: Make tile information panel.
     ******************************************************************************************************************/
    private void makeLeftDetails() {
        leftVBox = new VBox(5);
        leftVBox.setAlignment(Pos.CENTER);
        infoImage = new ImageView();
        infoImage.setFitHeight(100);
        infoImage.setFitWidth(100);
        leftDetails = new Text();
        leftVBox.getChildren().add(infoImage);
        leftVBox.getChildren().add(leftDetails);
        leftVBox.setStyle("-fx-background-color: #bd9982");
    }

    /*******************************************************************************************************************
     * Description: Make the radio buttons that contains the tile types for purchasing and view selection.
     *
     * Returns: Node - Radio Buttons
     ******************************************************************************************************************/
    private Node makeToggleGroup() {
        HBox toggle = new HBox();
        toggle.setAlignment(Pos.CENTER);
        group = new ToggleGroup();
        RadioButton tableBtn = new RadioButton("Table");
        RadioButton catBtn = new RadioButton("Cat");
        RadioButton kittenBtn = new RadioButton("Kitten");
        RadioButton emptyBtn = new RadioButton("Empty");
        RadioButton viewBtn = new RadioButton("View");
        tableBtn.setToggleGroup(group);
        catBtn.setToggleGroup(group);
        kittenBtn.setToggleGroup(group);
        emptyBtn.setToggleGroup(group);
        viewBtn.setToggleGroup(group);
        group.selectToggle(viewBtn);
        toggle.getChildren().add(tableBtn);
        toggle.getChildren().add(catBtn);
        toggle.getChildren().add(kittenBtn);
        toggle.getChildren().add(emptyBtn);
        toggle.getChildren().add(viewBtn);

        return toggle;
    }

    /*******************************************************************************************************************
     * Description: Make the store information panel.
     *
     * Returns: Node - Store information
     ******************************************************************************************************************/
    private Node makeTopDetails() {
        VBox top = new VBox(5);
        top.setAlignment(Pos.CENTER);
        ObservableList<Node> topChildren = top.getChildren();
        topDetails = new Text();
        topChildren.add(topDetails);
        top.setStyle("-fx-background-color: #806859");

        return top;
    }

    /*******************************************************************************************************************
     * Description: Describes what to do when a property in the subject changes. It updates the layout based on what
     * information was updated.
     *
     * Parameters: PropertyChangeEvent evt - provides information about the property change
     ******************************************************************************************************************/
    // GRADING: 1.B OBSERVER-TILE AREA
    public void propertyChange(PropertyChangeEvent evt) {
        // Remake tiles on size change
        if (evt.getPropertyName().equals("sizeChange") ) {
            view.makeTiles(model.getTiles());
            controller.addTileEvents();
            selected = 0;
        }
        // Change tile info area based on new selection
        else if (evt.getPropertyName().equals("viewChange")) {
            view.removeSelected(selected);
            selected = Integer.parseInt(evt.getNewValue().toString());
        }
        // Update Store information
        topDetails.setText("Week: " + model.getTimeSinceReset() +
                "\nFilled: " + model.getFilled() +
                "\nFunds: " + model.getFunds() +
                "\nAdopted: " + model.getAdopted());
        leftDetails.setText(view.getTileInfo(selected));
        view.setSelected(selected);
        setTileInfoImage();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /*******************************************************************************************************************
     * Description: Load in and set the tile information panel image.
     ******************************************************************************************************************/
    private void setTileInfoImage() {
        // Clear image
        infoImage.setImage(null);
        try {
            // Load in image from relative path.
            String imageURL = model.getTiles().get(selected).getImageURL();
            FileInputStream file = new FileInputStream("src/main/java/justice_aidan/javafx/" + imageURL);
            Image image = new Image(file);
            infoImage.setImage(image);
        } catch (Exception exception) {
            System.out.println("Could not open image");
        }
    }
}
