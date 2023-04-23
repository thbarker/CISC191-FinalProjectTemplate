package edu.sdccd.cisc191.guiPackage;

import edu.sdccd.cisc191.calendarPackage.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EventLabel extends Button
{
    private Label title;
    private Label location;
    private Event event;
    public EventLabel(Event event)
    {
        this.event = event;
        title = new Label(event.getTitle());
        location = new Label(event.getLocation());
        this.setGraphic(setText(false));
        this.setStyle("-fx-background-color: rgba(234,234,234,0);" +
                " -fx-border-color: rgba(26,26,26,0); " +
                "-fx-pref-width: 300px; " +
                "-fx-pref-height: 60px;");
        this.setOnMouseEntered(e -> {
            this.setStyle("-fx-background-color: rgba(234,234,234,0);" +
                    " -fx-border-color: rgba(26,26,26,0); " +
                    "-fx-pref-width: 300px; " +
                    "-fx-pref-height: 60px;");
            this.setGraphic(setText(true));
        });
        this.unselected();
    }

    public void selected()
    {
        this.setOnMouseExited(e -> {
            this.setStyle("-fx-background-color: rgba(234,234,234,0);" +
                    " -fx-border-color: rgba(26,26,26,0); " +
                    "-fx-pref-width: 300px; " +
                    "-fx-pref-height: 60px;");
            this.setGraphic(setText(true));
        });
    }

    public void unselected()
    {
        this.setGraphic(setText(false));
        this.setOnMouseExited(e -> {
            this.setStyle("-fx-background-color: rgba(234,234,234,0);" +
                    " -fx-border-color: rgba(26,26,26,0); " +
                    "-fx-pref-width: 300px; " +
                    "-fx-pref-height: 60px;");
            this.setGraphic(setText(false));
        });
    }
    private VBox setText(boolean bold)
    {
        VBox vbox = new VBox();
        vbox.getChildren().add(title);
        if(location.getText() != "")
        {
            if (location.getText().charAt(0) != '\t')
                location = new Label("\t" + location.getText());
            else
                location = new Label(location.getText());
        }
        if(bold == true)
        {
            location.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
            title.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
        }
        else
        {
            location.setFont(new Font("Cambria", 15));
            title.setFont(new Font("Cambria", 20));
        }
        return vbox;
    }

    public Label getTitle()
    {
        return title;
    }

    public Label getLocation()
    {
        return location;
    }

    public Event getEvent()
    {
        return event;
    }
}
