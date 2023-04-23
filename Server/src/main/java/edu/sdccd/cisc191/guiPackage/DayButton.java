package edu.sdccd.cisc191.guiPackage;

import edu.sdccd.cisc191.calendarPackage.CalendarController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

import java.util.Date;

public class DayButton extends Button
{

    private Date date;
    private boolean disabled;
    private boolean current;

    /**
     * This is the constructor for this class that takes 3 arguments
     * and initializes the DayButton to the correct values
     * @param d is the date that the button will have
     * @param month is the month that the button will display with
     * @param cal is the Calendar Controller than will control the Day
     *            Buttons
     */

    public DayButton(Date d, int month, CalendarController cal)
    {
        date = d;
        this.setText(Integer.toString(date.getDate()));
        if(month == d.getMonth())
        {
            if(date.getDate() == cal.getCurrentDate().getDate()
                    && date.getMonth() == cal.getCurrentDate().getMonth()
                    && date.getYear() == cal.getCurrentDate().getYear())
                setCurrentStyle();
            else
                setDefaultStyle();
            if(cal.hasEvent(date))
            {
                Circle circle = new Circle(5,5,5);
                circle.setStyle("-fx-fill: #007500");
                this.setGraphic(circle);
            }
            disabled = false;
        }
        else
        {
            setDisabledStyle();
            disabled = true;
            if(cal.hasEvent(date))
            {
                Circle circle = new Circle(5,5,5);
                circle.setStyle("-fx-fill: #383838");
                this.setGraphic(circle);
            }
        }
        this.setAlignment(Pos.TOP_CENTER);
    }

    /**
     * Setter for the Date
     * @param d is the date to set
     */
    public void setDate(Date d)
    {
        date = d;
    }
    /**
     * Getter for the Date
     * @return the date to get
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * This method takes one argument and handles a DayButton Click
     * @param cal is the Calendar Controller to be manipulated
     */
    public void handleClick(CalendarController cal)
    {
        if(!disabled)
        {
            cal.setCurrentDate(new Date(this.getDate().getTime()));
            if(date.getTime() == cal.getCurrentDate().getTime())
                setCurrentStyle();
            else
                setDefaultStyle();
        }

    }

    /**
     * This method sets the style of the DayButton to a selected state
     */
    public void setCurrentStyle()
    {
        setStyle("-fx-background-color: rgba(234,234,234,0);" +
                " -fx-border-color: #00428d; " +
                "-fx-border-width: 3px; " +
                "-fx-pref-width: 125px; " +
                "-fx-pref-height: 100px;");
        setOnMouseEntered(e -> {
            setStyle("-fx-background-color: rgba(0,0,0,0.1);" +
                    " -fx-border-color: #00428d; " +
                    "-fx-border-width: 3px; " +
                    "-fx-pref-width: 125px; " +
                    "-fx-pref-height: 100px;");
        });
        setOnMouseExited(e -> {
            setStyle("-fx-background-color: rgba(234,234,234,0);" +
                    " -fx-border-color: #00428d; " +
                    "-fx-border-width: 3px; " +
                    "-fx-pref-width: 125px; " +
                    "-fx-pref-height: 100px;");
        });
    }

    /**
     * This method sets the style of the DayButton to an unselected state
     */
    public void setDefaultStyle()
    {
        setStyle("-fx-background-color: rgba(234,234,234,0);" +
                " -fx-border-color: #1a1a1a; " +
                "-fx-border-width: 1px; " +
                "-fx-pref-width: 125px; " +
                "-fx-pref-height: 100px;");
        setOnMouseEntered(e -> {
            setStyle("-fx-background-color: rgba(0,0,0,0.1);" +
                    " -fx-border-color: #1a1a1a; " +
                    "-fx-border-width: 1px; " +
                    "-fx-pref-width: 125px; " +
                    "-fx-pref-height: 100px;");
        });
        setOnMouseExited(e -> {
            setStyle("-fx-background-color: rgba(196,196,196,0);" +
                    " -fx-border-color: #1a1a1a; " +
                    "-fx-border-width: 1px; " +
                    "-fx-pref-width: 125px; " +
                    "-fx-pref-height: 100px;");
        });
    }

    /**
     * This method sets the style of the DayButton to a disable state
     */
    private void setDisabledStyle()
    {
        setStyle("-fx-background-color: rgba(0,0,0,0.31);" +
                " -fx-border-color: #1a1a1a; " +
                "-fx-border-width: 1px; " +
                "-fx-pref-width: 125px; " +
                "-fx-pref-height: 100px;");
    }

    /**
     * Getter for disabled
     * @return disabled boolean
     */
    public boolean disabled()
    {
        return disabled;
    }
}
