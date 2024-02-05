package justice_aidan.javafx;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class CafeSimView {
    private GridPane tiles;

    public CafeSimView() {
        tiles = new GridPane();

        for (int i = 0; i < 3; i++) {
            RowConstraints rc = new RowConstraints();
            //rc.setFillHeight(true);
            //rc.setVgrow(Priority.ALWAYS);
            rc.setPercentHeight(100.0/3.0);
            tiles.getRowConstraints().add(rc);
        }
        for (int i = 0; i < 3; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            //cc.setFillWidth(true);
            //cc.setHgrow(Priority.ALWAYS);
            cc.setPercentWidth(100.0/3.0);
            tiles.getColumnConstraints().add(cc);
        }

        for (int i = 0; i < 9; i++) {
            Button button = new Button(Integer.toString(i));
            button.setMaxWidth(1000000);
            button.setMaxHeight(1000000);
            tiles.add(button, i % 3, i / 3);
        }
    }


    public GridPane getCafeView() {
        return tiles;
    }
}
