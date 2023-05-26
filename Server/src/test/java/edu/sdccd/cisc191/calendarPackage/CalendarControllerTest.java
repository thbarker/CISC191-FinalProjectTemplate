package edu.sdccd.cisc191.calendarPackage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalendarControllerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
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

    /*
    The Following Test uses external references for the testing features from https://www.baeldung.com/java-testing-system-out-println
    to allow me to test the outPut Stream results due to the calling of teh System.out.println method
     */

    /**
     * BeforeEach method to set up the outStream for Print test
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * This test method tests the print method within the CalendarController Class
     * which will cover the course objectives: Lambdas and Stream API
     */
    @Test
    void testPrint()
    {
        CalendarController myCal = new CalendarController();
        myCal.addEvent(new Event("A Event", new Date(123,2,1)));
        myCal.addEvent(new Event("D Event", new Date(123,6,15)));
        myCal.addEvent(new Event("C Event", new Date(125,2,15)));

        myCal.print();

        assertTrue(outputStreamCaptor.toString().trim().startsWith("A Event, @No location specified, Wed Mar 01 00:00:00 PST 2023"));
        assertTrue(outputStreamCaptor.toString().trim().endsWith("C Event, @No location specified, Sat Mar 15 00:00:00 PDT 2025"));
        assertTrue(outputStreamCaptor.toString().trim().contains("D Event, @No location specified, Sat Jul 15 00:00:00 PDT 2023"));
    }

    /**
     * After Each Method to reset the outStream
     */
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
