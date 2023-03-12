package edu.sdccd.cisc191.guiPackage;

import edu.sdccd.cisc191.calendarPackage.CalendarController;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.Date;

/**
 * This Class extends from the JavaFX Button Class and implements stylist
 * and functional changes that allow it to perform the role of a back button.
 * It is used to navigate the Calendar by setting the Calendar back one month
 */
public class BackButton extends Button
{
    public BackButton()
    {
        Path backArrow = new Path();
        double arrowWidth = 10;
        double arrowHeight = 20;
        this.setStyle("-fx-background-color: rgba(255,255,255,0)");
        this.setPrefSize(50,30);

        backArrow.getElements().addAll(
                new MoveTo(arrowWidth, 0),
                new LineTo(0, arrowHeight / 2),
                new LineTo(arrowWidth, arrowHeight)
        );

        backArrow.setStroke(Color.BLACK);
        backArrow.setFill(Color.TRANSPARENT);
        backArrow.setStrokeWidth(1.5);

        this.setGraphic(backArrow);
    }

    public void handleClick(CalendarController cal)
    {
        cal.prevMonth();
    }

}
