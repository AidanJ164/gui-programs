package justice_aidan.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;
import java.util.EventListener;

public class Controller {
    private Layout layout;
    private CafeSim model;


    public Controller(CafeSim model, Layout layout) {
        this.model = model;
        this.layout = layout;

        this.layout.getNextWeekButton().addEventFilter(ActionEvent.ACTION, new NextWeekListener());
        this.layout.getThreeGridButton().addEventFilter(ActionEvent.ACTION, new ThreeGridListener());
        this.layout.getFiveGridButton().addEventFilter(ActionEvent.ACTION, new FiveGridListener());
        this.layout.getNineGridButton().addEventFilter(ActionEvent.ACTION, new NineGridListener());
    }
    private class NextWeekListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            model.nextWeek();
        }
    }

    private class ThreeGridListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            model.setSize(3);
        }
    }
    private class FiveGridListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            model.setSize(5);
        }
    }
    private class NineGridListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            model.setSize(9);
        }
    }
}


