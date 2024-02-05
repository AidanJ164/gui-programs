package justice_aidan.javafx;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Layout implements PropertyChangeListener {
    private BorderPane root;
    private Controller controller;
    private CafeSimView view;
    private Button nextWeek;
    private CafeSim model;

    public Layout(CafeSim model) {
        view = new CafeSimView();

        root = new BorderPane();
        root.setMaxHeight(Double.MAX_VALUE);
        root.setMaxWidth(Double.MAX_VALUE);
        //root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-border-color: black");

        ObservableList<Node> nodes = root.getChildren();

        root.setTop(makeTopDetails());
        root.setLeft(makeLeftDetails());
        root.setCenter(view.getCafeView());
        root.setBottom(makeBottomDetails());

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private VBox makeTopDetails() {
        VBox top = new VBox(5);
        top.setAlignment(Pos.CENTER);
        ObservableList<Node> topChildren = top.getChildren();
        topChildren.add(new Text("Week: "));
        topChildren.add(new Text("Filled: "));
        topChildren.add(new Text("Funds: "));
        topChildren.add(new Text("Adopted: "));

        return top;
    }

    private VBox makeLeftDetails() {
        VBox left = new VBox(5);
        left.setAlignment(Pos.CENTER);
        ObservableList<Node> leftChildren = left.getChildren();
        leftChildren.add(new Text("Type"));
        leftChildren.add(new Text("Floor Changed: "));
        leftChildren.add(new Text("Floor Age: "));
        leftChildren.add(new Text("Total Cost: "));

        return left;
    }

    private Node makeBottomDetails() {
         VBox bottom = new VBox();
         bottom.setAlignment(Pos.CENTER);
         ObservableList<Node> rows = bottom.getChildren();

         Text temp = new Text("options");
         rows.add(new Text("options"));

         HBox options = new HBox();
         nextWeek = new Button("Next Week");
         options.getChildren().add(nextWeek);

         return bottom;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void propertyChange(PropertyChangeEvent evt) {

    }

    public Button getNextWeekButton() {
        return nextWeek;
    }
}
