package edu.sdccd.cisc191.calendarPackage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalendarControllerTest {
    /**
     * This test method tests the insert sorted "addEvent()" method
     * to determine if the course objective: searching/sorting was covered
     * as well as the course objectives: generics
     */
    @Test
    void testAddEvent()
    {
        CalendarController myCal = new CalendarController();

        myCal.addEvent(new Event("A Event", new Date(123,2,1)));
        myCal.addEvent(new Event("D Event", new Date(123,6,15)));
        myCal.addEvent(new Event("C Event", new Date(125,2,15)));
        myCal.addEvent(new Event("E Event", new Date(123,0,0)));
        myCal.addEvent(new Event("B Event", new Date(123,10,1)));

        ArrayList<Event> list = myCal.getEventList();

        String str = "";

        for(Event e: list)
            str += e.getTitle();

        assertTrue(str.startsWith("E Event"));
        assertTrue(str.endsWith("C Event"));
    }
    /**
     * This test method tests the binary search "find()" method
     * to determine if the course objective: recursion was covered
     */
    @Test
    void testBinarySearch()
    {
        CalendarController myCal = new CalendarController();
        myCal.addEvent(new Event("A Event", new Date(123,2,1)));
        myCal.addEvent(new Event("D Event", new Date(123,6,15)));
        myCal.addEvent(new Event("C Event", new Date(125,2,15)));
        myCal.addEvent(new Event("E Event", new Date(123,0,0)));
        myCal.addEvent(new Event("B Event", new Date(123,10,1)));

        assertEquals(1, myCal.find(new Event("A Event", new Date(123,2,1))));
        assertEquals(2, myCal.find(new Event("D Event", new Date(123,6,15))));
        assertEquals(4, myCal.find(new Event("C Event", new Date(125,2,15))));
        assertEquals(0, myCal.find(new Event("E Event", new Date(123,0,0))));
        assertEquals(3, myCal.find(new Event("B Event", new Date(123,10,1))));
    }
}
