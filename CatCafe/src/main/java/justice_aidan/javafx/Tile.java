package justice_aidan.javafx;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Tile {
    private FloorArea floorArea;
    private PropertyChangeSupport subject;

    // maybe do a mustPay variable that CafeSim grabs for each node
    // when tile is created, increment mustPay by initial cost, when nextWeek(), set to weekly cost
    // Doesn't work, need to update cost right away

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
    public String getDetails() {
        return floorArea.toString();
    }

    public int getWeeklyCost() {
        return floorArea.getWeeklyCost();
    }
    public void nextWeek() {
        floorArea.nextWeek();
        // If cat, check age == 60
        if (floorArea.getName() == "Cat" && floorArea.getAge() == 60) {
            floorArea = new Empty();
        }
        // If kitten, check age == 14
        if (floorArea.getName() == "Kitten" && floorArea.getAge() == 14) {
            floorArea = new Empty();
        }
    }
}
