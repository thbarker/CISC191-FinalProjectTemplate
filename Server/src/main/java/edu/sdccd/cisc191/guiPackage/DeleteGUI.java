package edu.sdccd.cisc191.guiPackage;

import edu.sdccd.cisc191.calendarPackage.CalendarController;
import edu.sdccd.cisc191.calendarPackage.Event;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DeleteGUI
{
    private ArrayList<Event> eventList;
    private int index;
    public void display(CalendarController cal)
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Delete Event");
        window.setMaxHeight(800);
        window.setMinHeight(200);
        window.setMinWidth(300);
        window.setMaxWidth(300);
        window.setResizable(false);

        VBox root = new VBox();

        eventList = new ArrayList<>(cal.getCurrentEvents());

        Scene mainScene = new Scene(root, 300,200);
        window.setScene(mainScene);
        window.show();
    }
}
