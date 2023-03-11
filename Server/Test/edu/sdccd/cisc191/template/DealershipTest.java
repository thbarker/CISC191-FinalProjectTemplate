package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DealershipTest {

    @Test
    void getAllCars() {
        Dealership tester = new Dealership("San Diego");
        tester.addCar(new Ford("Bronco", 2017, 50000));
        tester.addCar(new Toyota("Highlander", 2012, 90000));
        ArrayList<Car> returnVal = new ArrayList<>();
        returnVal.add(new Ford("Bronco", 2017, 50000));
        returnVal.add(new Toyota("Highlander", 2012, 90000));
        assertEquals( returnVal.toString(), tester.getAllCars().toString(), "should return an arraylist of 2 cars");
    }
    @Test
    void addCar() {
        Dealership tester = new Dealership("San Diego");
        tester.addCar(new Ford("Bronco", 2017, 50000));
        ArrayList<Car> returnVal = new ArrayList<>();
        returnVal.add(new Ford("Bronco", 2017, 50000));
        assertEquals( returnVal.toString(), tester.getAllCars().toString(), "should return an arraylist of 1 car");

    }

    @Test
    void getLocation() {
        Dealership tester = new Dealership("San Diego");
        assertEquals("San Diego", tester.getLocation(), "should return san diego");
    }

    @Test
    void removeCar() {
        Dealership tester = new Dealership("San Diego");
        tester.addCar(new Ford("Bronco", 2017, 50000));
        tester.addCar(new Toyota("Highlander", 2012, 90000));

        tester.removeCar(tester.getAllCars().get(1));

        ArrayList<Car> returnVal = new ArrayList<>();
        returnVal.add(new Ford("Bronco", 2017, 50000));
        assertEquals( returnVal.toString(), tester.getAllCars().toString(), "should remove the car");
    }

    @Test
    void sort() {
        Dealership tester = new Dealership("San Diego");
        tester.addCar(new Ford("Bronco", 2017, 50000));
        tester.addCar(new Toyota("Highlander", 2012, 90000));
        tester.addCar(new Toyota("Highlander", 2007, 150000));
        tester.addCar(new Toyota("Highlander", 2010, 76000));
        tester.sort("Miles");

        ArrayList<Car> returnVal = new ArrayList<>();
        returnVal.add(new Toyota("Highlander", 2007, 150000));
        returnVal.add(new Toyota("Highlander", 2012, 90000));
        returnVal.add(new Toyota("Highlander", 2010, 76000));
        returnVal.add(new Ford("Bronco", 2017, 50000));

        assertEquals( returnVal.toString(), tester.getAllCars().toString(), "should return in order of miles");
    }
}