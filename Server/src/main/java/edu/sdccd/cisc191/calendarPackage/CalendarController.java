package edu.sdccd.cisc191.calendarPackage;

import java.util.ArrayList;
import java.util.Date;

public class CalendarController
{

    private ArrayList<Event> eventList; //List of events for calendar

    private static final Date today = new Date(); //Selected Date to Manipulate

    private Date currentDate; //Date to represent the month to start
    // the calendar on and be manipulated when the calendar is manipulated

    private Date[][] dateArray; //2D array of dates that represent the current month's dates to be displayed

    /**
     * Default Constructor initializes a new arraylist
     * of type Event
     */
    public CalendarController()
    {
        eventList = new ArrayList<>();
        currentDate = new Date(today.getTime());
        updateArray();
    }
    /**
     * This method adds an event to the calendar, sorted by
     * time of the event.
     * @param e is the Event to be inserted sorted
     */
    public void addEvent(Event e)
    {
        int i;
        for(i = 0; i < eventList.size(); i++)
        {
            if(eventList.size() == i || e.before(eventList.get(i)))
                break;
        }
        eventList.add(i, e);
    }
    /**
     * Print method to output the event list to the screen
     */
    public void print()
    {
        for(int i = 0; i < eventList.size(); i++)
        {
            eventList.get(i).print();
        }
    }

    public boolean hasEvent(Date d)
    {
        for(Event e: eventList)
        {
            if(e.getStart().getDate() == d.getDate()
               && e.getStart().getMonth() == d.getMonth()
               && e.getStart().getYear() == d.getYear())
                return true;
        }
        return false;
    }

    public Date getToday()
    {
        return today;
    }
    public Date getCurrentDate()
    {
        return currentDate;
    }

    public void setCurrentDate(Date d)
    {
        currentDate = d;
    }

    public void nextMonth()
    {
        if (currentDate.getMonth() == 11)
        {
            currentDate.setMonth(0);
            currentDate.setYear(currentDate.getYear() + 1);
        }
        else
            currentDate.setMonth(currentDate.getMonth() + 1);
    }

    public void lastMonth()
    {
        if (currentDate.getMonth() == 0)
        {
            currentDate.setMonth(11);
            currentDate.setYear(currentDate.getYear() - 1);
        }
        else
            currentDate.setMonth(currentDate.getMonth() - 1);
    }

    public String getMonthAlpha()
    {
        int m = currentDate.getMonth();

        String mAlpha;
        switch(m)
        {
            case 0: mAlpha = "January"; break;
            case 1: mAlpha = "February"; break;
            case 2: mAlpha = "March"; break;
            case 3: mAlpha = "April"; break;
            case 4: mAlpha = "May"; break;
            case 5: mAlpha = "June"; break;
            case 6: mAlpha = "July"; break;
            case 7: mAlpha = "August"; break;
            case 8: mAlpha = "September"; break;
            case 9: mAlpha = "October"; break;
            case 10: mAlpha = "November"; break;
            default: mAlpha = "December"; break;
        }
        return mAlpha;
    }

    public int getCurrentYear()
    {
        return currentDate.getYear()+1900;
    }

    /**
     This method returns the Date of the first of the current month
     */
    public Date getFirstSunday()
    {
        long millisecondsInADay = 1000 * 60 * 60 * 24;
        Date temp = new Date(currentDate.getTime());
        temp.setDate(1);
        while(temp.getDay() > 0)
            temp = new Date(temp.getTime()-millisecondsInADay);
        return temp;
    }

    public void updateArray()
    {
        long millisecondsInADay = 1000 * 60 * 60 * 24;
        dateArray = new Date[6][7];
        Date temp = new Date(this.getFirstSunday().getTime());
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                dateArray[i][j] = new Date(temp.getTime());

                temp = new Date(temp.getTime()+millisecondsInADay);
            }
        }
    }

    public Date getDate(int i, int j)
    {
        return dateArray[i][j];
    }

    public void today()
    {
        currentDate = new Date(today.getTime());
    }
}
