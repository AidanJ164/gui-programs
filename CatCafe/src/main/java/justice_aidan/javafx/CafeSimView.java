package justice_aidan.javafx;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class CafeSimView {
    private GridPane tiles;

    public CafeSimView(int size) {
        tiles = new GridPane();
        changeSize(size);
    }

    public void changeSize(int size) {
        tiles.getChildren().clear();
        tiles.getRowConstraints().clear();
        tiles.getColumnConstraints().clear();

        for (int i = 0; i < size; i++) {
            RowConstraints rc = new RowConstraints();
            //rc.setFillHeight(true);
            //rc.setVgrow(Priority.ALWAYS);
            rc.setPercentHeight(100.0/size);
            tiles.getRowConstraints().add(rc);
        }
        for (int i = 0; i < size; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            //cc.setFillWidth(true);
            //cc.setHgrow(Priority.ALWAYS);
            cc.setPercentWidth(100.0/size);
            tiles.getColumnConstraints().add(cc);
        }

        for (int i = 0; i < size * size; i++) {
            Button button = new Button("cat");
            button.setMaxWidth(1000000);
            button.setMaxHeight(1000000);
            tiles.add(button, i % size, i / size);
        }
    }

    public GridPane getCafeView() {
        return tiles;
    }
}
