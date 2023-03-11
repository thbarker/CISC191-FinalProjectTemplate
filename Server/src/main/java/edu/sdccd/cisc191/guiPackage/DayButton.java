package edu.sdccd.cisc191.guiPackage;

import edu.sdccd.cisc191.calendarPackage.CalendarController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Date;

public class DayButton extends Button
{

    private Date date;
    private boolean disabled;
    private boolean current;

    public DayButton(Date d, int month, CalendarController cal)
    {
        date = d;
        this.setText(Integer.toString(date.getDate()));
        if(month == d.getMonth())
        {
            if(date.getTime() == cal.getCurrentDate().getTime())
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

    public void setDate(Date d)
    {
        date = d;
    }

    public Date getDate()
    {
        return date;
    }

    public void handleClick(CalendarController cal)
    {
        if(!disabled)
        {
            cal.setCurrentDate(new Date(this.getDate().getTime()));
            cal.updateArray();
            if(date.getTime() == cal.getCurrentDate().getTime())
                setCurrentStyle();
            else
                setDefaultStyle();
        }

    }

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

    private void setDisabledStyle()
    {
        setStyle("-fx-background-color: rgba(0,0,0,0.31);" +
                " -fx-border-color: #1a1a1a; " +
                "-fx-border-width: 1px; " +
                "-fx-pref-width: 125px; " +
                "-fx-pref-height: 100px;");
    }

    public boolean disabled()
    {
        return disabled;
    }
}
