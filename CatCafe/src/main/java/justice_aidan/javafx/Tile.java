package justice_aidan.javafx;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Tile {
    private FloorArea floorArea;
    private PropertyChangeSupport subject;

    public Tile() {
        subject = new PropertyChangeSupport(this);
        floorArea = new Empty();
    }

    public void addObserver(PropertyChangeListener obv) {
        subject.addPropertyChangeListener(obv);
    }

    public String getName() {
        return floorArea.getName();
    }
    public void nextWeek() {}
}
