package edu.sdccd.cisc191.guiPackage;


import edu.sdccd.cisc191.calendarPackage.CalendarController;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.Date;


public class NextButton extends Button
{
    public NextButton()
    {
        Path backArrow = new Path();
        double arrowWidth = 10;
        double arrowHeight = 20;
        this.setStyle("-fx-background-color: rgba(255,255,255,0)");
        this.setPrefSize(50,30);

        backArrow.getElements().addAll(
                new MoveTo(0,0),
                new LineTo(arrowWidth, arrowHeight / 2),
                new LineTo(0, arrowHeight)
        );

        backArrow.setStroke(Color.BLACK);
        backArrow.setFill(Color.TRANSPARENT);
        backArrow.setStrokeWidth(1.5);

        this.setGraphic(backArrow);
    }

    public void handleClick(CalendarController cal)
    {
        cal.nextMonth();
    }
}
