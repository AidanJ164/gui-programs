package justice_aidan.javafx;

import javafx.scene.control.Button;

public class TileView extends Button {
    private Tile tile;
    public TileView(Tile tile) {
        this.setMaxWidth(1000000);
        this.setMaxHeight(1000000);
        this.setText("-" + tile.getName().charAt(0) + "-");
        this.tile = tile;
    }
}
