package edu.sdccd.cisc191.calendarPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;


/**
 * This Class Controls the Calendar aspect of the Application.
 * It's constructor takes no arguments, and initializes a new Calendar
 * Controller with no events and the current Date set to Today
 */
public class CalendarController
{

    private ArrayList<Event> eventList; //List of events for calendar
    private static final Date today = new Date(); //Today's date as a static final Date

    private Date currentDate; //Date to represent the user's current Date choice

    private Date[][] dateArray; //2D array of dates that represent the current month's dates to be displayed

    /**
     * Default Constructor initializes a new arraylist
     * of type Event, new current Date as Today's date
     */
    public CalendarController()
    {
        eventList = new ArrayList<>();
        currentDate = new Date(today.getTime());
        updateArray();
    }
    /**
     * This method adds an event to the calendar, sorted by
     * date of the event.
     * @param e is the Event to be inserted sorted
     */
    /*This code first checks to see if the e parameter is null.
        If it is, then a NullPointerException is thrown.
        Otherwise, the event is added to the list in sorted order.
        If the event title is blank, then a message is printed to the console and
        the event are not added to the list.*/
    public void addEvent(Event e) {
        if (e == null) {
            throw new NullPointerException("e cannot be null");
        }
        if (e.getTitle().isEmpty() || e.getDescription().isEmpty()) {
            System.out.println("Event title and description cannot be blank.");
            return;
        }

        int i;
        for(i = 0; i < eventList.size(); i++) {
            if(e.before(eventList.get(i))) {
                break;
            }
        }
        eventList.add(i, e);
    }
    /**
     * Print method to output the event list to the screen
     */
    public void print()
    {
        Stream<Event> eventStream = eventList.stream();
        eventStream.forEach(n -> System.out.println(n.toString()));
    }
    /**
     * hasEvent method to return the boolean value representing
     * if the Calendar has an event on a given date.
     * @param d is the date to check for
     * @return boolean for if the date has an Event
     */
    public boolean hasEvent(Date d)
    {
        for(Event e: eventList)
        {
            if(e.sameDate(d))
                return true;
        }
        return false;
    }

    /**
     * This method returns today's date
     * @return Date of today's current date
     */
    public Date getToday()
    {
        return today;
    }

    /**
     * This method returns the current Date
     * @return currentDate
     */
    public Date getCurrentDate()
    {
        return currentDate;
    }

    /**
     * This method sets the current Date
     * @param d Date to set to the current Date
     */
    public void setCurrentDate(Date d)
    {
        currentDate = d;
    }

    /**
     * This method updates the CalendarController to depict the next
     * month in the Calendar
     */
    public void nextMonth()
    {
        if (currentDate.getMonth() == 11)
        {
            currentDate.setMonth(0);
            currentDate.setYear(currentDate.getYear() + 1);
        }
        else
            currentDate.setMonth(currentDate.getMonth() + 1);
        updateArray();
    }

    /**
     * This method updates the CalendarController to depict the previous
     * month in the Calendar
     */
    public void prevMonth()
    {
        if (currentDate.getMonth() == 0)
        {
            currentDate.setMonth(11);
            currentDate.setYear(currentDate.getYear() - 1);
        }
        else
            currentDate.setMonth(currentDate.getMonth() - 1);
        updateArray();
    }
    /**
     * This method returns the String of the Current Month
     * @return String of the month name in alphabetical context
     */
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

    /**
     * This method returns the current Date's year as an int
     * @return int for the current year
     */
    public int getCurrentYear()
    {
        return currentDate.getYear()+1900;
    }

    /**
     * This method returns the Date of the first of the current month
     * @return Date of the first sunday of the current month to display
     */
    private Date getFirstSunday()
    {
        long millisecondsInADay = 1000 * 60 * 60 * 24;
        Date temp = new Date(currentDate.getTime());
        temp.setDate(1);
        while(temp.getDay() > 0)
            temp = new Date(temp.getTime()-millisecondsInADay);
        return temp;
    }

    /**
     * This method updates the CalendarController's 2D Date Array
     * with the correct values to be displayed
     */
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

    /**
     * This method returns the date of the Calendar's date array
     * at i,j
     * @param i is the first index
     * @param j is the second index
     * @return Date which is the date at the index i,j
     */
    public Date getDate(int i, int j)
    {
        return dateArray[i][j];
    }

    /**
     * This method sets the current Date to Today
     */
    public void today()
    {
        currentDate = new Date(today.getTime());
        updateArray();
    }

    /**
     * This method returns an Event at index 'index'
     * @param index is the index of the Arraylist eventList
     * @return Event of whatever index was passed
     */
    public Event getEvent(int index)
    {
        return eventList.get(index);
    }

    /**
     * This method returns an int to represent the size of the eventList
     * @return int as the current Size of EventList
     */
    public int size()
    {
        return eventList.size();
    }

    /**
     * This is a getter for the eventList field
     * @return ArrayList of type Event of all the events in the list
     */
    public ArrayList<Event> getEventList()
    {
        return eventList;
    }

    /**
     * This method returns an Event Arraylist with all the info for
     * Events on the Current Day
     * @return ArrayList of type Event with 2 parts seperated by a ","
     *         The title and the location
     */
    public ArrayList<Event> getCurrentEvents()
    {
        ArrayList<Event> list  = new ArrayList();
        for(int i = 0; i < eventList.size(); i++)
        {
            if(eventList.get(i).sameDate(currentDate))
                list.add(eventList.get(i));
        }
        return list;
    }

    /**
     * This method searches the Calendar's eventList arrayList
     * and returns the index of the first appearance of Event e
     * @param e is the event to search for
     * @return index that the first appearance is. -1 if not found
     */
    public int find(Event e)
    {
        return binarySearch(eventList, e,0, eventList.size() - 1);
    }

    /**
     * This is a helper method for the find method that implements a recursive binary search
     * @param eventList is the arraylist of type Event to search
     * @param e is the event to search for
     * @param left is the beginning element to search for
     * @param right is the final element to search for
     * @return the index of the event's first appearance, -1 if not found
     */
    private int binarySearch(ArrayList<Event> eventList, Event e, int left, int right)
    {
        if (left > right) {
            return -1; // target not found
        }

        int mid = (left + right) / 2;

        if (eventList.get(mid).sameEvent(e))
            return mid;
        else if (eventList.get(mid).before(e))
            return binarySearch(eventList, e, mid + 1, right);
        else
            return binarySearch(eventList, e, left, mid - 1);

    }
    /**
     * This method removes an Event from the list
     * @param index is the index of the Event to be removed
     */
    public void remove(int index)
    {
        if(index < 0 || index >= eventList.size())
            return;
        eventList.remove(index);
    }

}
