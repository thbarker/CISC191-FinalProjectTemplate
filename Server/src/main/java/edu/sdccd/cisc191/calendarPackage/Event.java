package edu.sdccd.cisc191.calendarPackage;
import java.util.Date;

public class Event
{
    private String title;
    private String location;
    private Date start;

    /**
     * Default Constructor initializes a new Event with
     * all the default values and the title New Event
     */
    public Event()
    {
        title = "New Event";
        location = "";
        start = new Date();
    }
    /**
     * Constructor that takes in title, date, and time
     * @param titleP is the string for the title
     * @param startP is the start
     */
    public Event(String titleP, Date startP)
    {
        title = titleP;
        location = "";
        start = startP;
    }
    /**
     * Constructor that takes in title, location, date, and time
     * @param titleP is the string for the title
     * @param locationP is the location of the Event
     * @param startP is the date
     */
    public Event(String titleP, String locationP, Date startP)
    {
        title = titleP;
        location = locationP;
        start = startP;
    }
    /**
     * This getter method returns the date of the Event
     */
    public Date getStart()
    {
        return start;
    }
    /**
     * This getter method returns the time of the event
     */
    public String toString()
    {
        String temp;
        temp = title + ", "
                + location + ", "
                + start.toString();
        return temp;
    }
    /**
     * This method will check if a calling Event takes place before
     * than the passed argument event
     * @param eventP is the event to be checked with the calling
     *               object
     * @return the bool value for if the calling object is before
     */
    public boolean before(Event eventP)
    {
        if (this.start.before(eventP.start))
            return true;
        return false;
    }

    /**
     * This method will check if a calling Event takes place after
     * than the passed argument event
     * @param eventP is the event to be checked with the calling
     *               object
     * @return the bool value for if the calling object is after
     */
    public boolean after(Event eventP)
    {
        if (this.start.after(eventP.start))
            return true;
        return false;
    }
}
