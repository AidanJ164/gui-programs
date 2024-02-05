package justice_aidan.javafx;

import javafx.scene.layout.HBox;

public class Layout {
    private HBox root;
    private Controller controller;
    private CafeSimView view;

    public Layout() {
        controller = new Controller(this);
        view = new CafeSimView();
    }

    public HBox getRoot() {
        return root;
    }
}
