package edu.sdccd.cisc191.guiPackage;

import edu.sdccd.cisc191.calendarPackage.CalendarController;
import edu.sdccd.cisc191.calendarPackage.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AlertBox
{
    public static void display(String message)
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Alert");
        window.setHeight(100);
        window.setWidth(240);
        window.setResizable(false);

        VBox root = new VBox();
        Label label = new Label(message);
        label.setFont(new Font("Cambria", 15));
        label.setPadding(new Insets(20,50,20,50));
        label.setAlignment(Pos.CENTER);
        root.getChildren().add(label);
        Scene mainScene = new Scene(root, 240,100);
        window.setScene(mainScene);
        window.show();
    }
}
