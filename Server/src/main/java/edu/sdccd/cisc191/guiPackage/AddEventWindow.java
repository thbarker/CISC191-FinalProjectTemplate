package edu.sdccd.cisc191.guiPackage;

import edu.sdccd.cisc191.calendarPackage.CalendarController;
import edu.sdccd.cisc191.calendarPackage.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.Date;

public class AddEventWindow
{
    public static void display(CalendarController calendarP)
    {
        String eventTitle;
        String EventLocation;
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Event");

        window.setWidth(500);
        window.setHeight(300);

        TextField titleField = new TextField();
        titleField.setText("New Event");
        titleField.setMaxWidth(200);
        titleField.setAlignment(Pos.CENTER);

        Button addButton = new Button("Enter");
        addButton.setOnAction(e -> {
            calendarP.addEvent(new Event(titleField.getText(), "Los Angeles", calendarP.getCurrentDate()));
            window.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());




        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(addButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(titleField,buttonBox);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

    }
}
