package edu.sdccd.cisc191.calendarPackage;
import java.util.Date;
import java.util.Objects;

/**
 * This Class extends represents a single event that
 * manages fields: title, location, and start
 */
public class Event
{
    private String title;
    private String location;
    private Date start;
    private String description = "";

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
     * @return the Date of the event
     */
    public Date getStart()
    {
        return start;
    }
    public String getTitle()
    {
        return title;
    }
    public String getLocation()
    {
        return location;
    }
    public String getDescription() {
        return description;
    }
    /**
     * This method will check if a calling Event takes place before
     * than the passed argument event. Order of precedence goes date
     * (day month year), then title (alphabetical), then location (alphabetical).
     * @param eventP is the event to be checked with the calling
     *               object
     * @return the bool value for if the calling object is before.
     *         If they are the same event, it returns true
     */
    public boolean before(Event eventP)
    {
        if(this.sameDate(eventP.getStart()))
        {
           if(this.getTitle().compareTo(eventP.getTitle()) == 0)
           {
               if(this.getLocation().compareTo(eventP.getLocation()) <= 0)
                   return true;
           }
           else if(this.getTitle().compareTo(eventP.getTitle()) < 0)
               return true;
        }
        else if(this.getStart().before(eventP.getStart()))
            return true;
        return false;
    }

    /**
     * This method will check if a calling Event takes place after
     * than the passed argument event. Order of precedence goes date
     * (day month year), then title (alphabetical), then location (alphabetical).
     * @param eventP is the event to be checked with the calling
     *               object
     * @return the bool value for if the calling object is after
     *         If they are the same event, it returns true
     */
    public boolean after(Event eventP)
    {
        if(this.sameDate(eventP.getStart()))
        {
            if(this.getTitle().compareTo(eventP.getTitle()) == 0)
            {
                if(this.getLocation().compareTo(eventP.getLocation()) >= 0)
                    return true;
            }
            else if(this.getTitle().compareTo(eventP.getTitle()) > 0)
                return true;
        }
        else if(this.getStart().after(eventP.getStart()))
            return true;
        return false;
    }
    /**
     * This method will check if a calling Event takes place after
     * than the passed argument event
     * @param d is the Date to be checked with the calling
     *               object
     * @return the bool value for if the calling object has the same date
     *         as parameter d
     */
    public boolean sameDate(Date d)
    {
        return d.getDate() == this.getStart().getDate()
                && d.getMonth() == this.getStart().getMonth()
                && d.getYear() == this.getStart().getYear();
    }

    public boolean sameEvent(Event e)
    {
        return e.sameDate(this.getStart())
                && Objects.equals(e.getTitle(), this.title)
                && Objects.equals(e.getLocation(), this.location);

    }

}
