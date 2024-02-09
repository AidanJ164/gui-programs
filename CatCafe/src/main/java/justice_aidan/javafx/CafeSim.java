package justice_aidan.javafx;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CafeSim {
    private int timeSinceReset = 0;
    private int funds = 0;
    private int size = 3;
    private PropertyChangeSupport subject;
    private ArrayList<Tile> tiles;

    public CafeSim() {
        subject = new PropertyChangeSupport(this);
        tiles = new ArrayList<Tile>();
        for (int i = 0; i < 9; i++) {
            tiles.add(new Tile());
        }
    }

    public void nextWeek() {
        System.out.println("hi");
    }

    public void addObserver(PropertyChangeListener obv) {
        subject.addPropertyChangeListener(obv);
    }

    public void setSize(int size) {
        int old = this.size;
        this.size = size;

        tiles.clear();
        for (int i = 0; i < size * size; i++) {
            tiles.add(new Tile());
        }

        subject.firePropertyChange("sizeChange", old, size);
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
