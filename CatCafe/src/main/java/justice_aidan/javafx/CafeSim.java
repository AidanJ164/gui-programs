package justice_aidan.javafx;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CafeSim {
    private int timeSinceReset = 0;
    private int funds = 0;
    private int size = 3;
    private PropertyChangeSupport subject;

    public CafeSim() {
        subject = new PropertyChangeSupport(this);
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

        subject.firePropertyChange("sizeChange", old, size);
    }

    public int getSize() {
        return size;
    }
}
