package justice_aidan.javafx;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CafeSimView extends GridPane {
    private CafeSim model;

    public CafeSimView(CafeSim model) {
        this.model = model;
        makeTiles();
    }

    public void makeTiles() {
        ArrayList<Tile> floorTiles = model.getTiles();
        int size = model.getSize();
        ObservableList<Node> tiles = this.getChildren();

        getChildren().clear();
        getRowConstraints().clear();
        getColumnConstraints().clear();

        for (int i = 0; i < size; i++) {
            RowConstraints rc = new RowConstraints();
            //rc.setFillHeight(true);
            //rc.setVgrow(Priority.ALWAYS);
            rc.setPercentHeight(100.0/size);
            getRowConstraints().add(rc);
        }
        for (int i = 0; i < size; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            //cc.setFillWidth(true);
            //cc.setHgrow(Priority.ALWAYS);
            cc.setPercentWidth(100.0/size);
            getColumnConstraints().add(cc);
        }

        for (int i = 0; i < size * size; i++) {
            /*
            Button button = new Button("cat");
            button.setMaxWidth(1000000);
            button.setMaxHeight(1000000); */
            add(new TileView(floorTiles.get(i)), i % size, i / size);
        }
    }
}
