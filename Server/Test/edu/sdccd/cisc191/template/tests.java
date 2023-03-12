package edu.sdccd.cisc191.template;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class tests {
    /**
     *
     * Module 1, adding object to array by using the addCar method found in Dealership.java
     *
     */
    @Test
    void addCar() {
        Dealership tester = new Dealership("San Diego");
        tester.addCar(new Ford("Bronco", 2017, 50000));
        ArrayList<Car> returnVal = new ArrayList<>();
        returnVal.add(new Ford("Bronco", 2017, 50000));
        assertEquals( returnVal.toString(), tester.getAllCars().toString(), "should return an arraylist of 1 car");

    }

    /**
     * Module 2: creates a 2d array of cars by creating an array of dealerships, which themselves have an array of cars.
     * Loops through the 2d array using the oldest car method found in JavaFX.java, which returns the oldest car
     */
    @Test
    void getOldestCarTest() {
        ArrayList<Dealership> allDealerships = new ArrayList<>();
        Dealership dealership1 = new Dealership("San Diego");
        Dealership dealership2 = new Dealership("Los Angeles");
        Dealership dealership3 = new Dealership("San Francisco");

        dealership1.addCar(new Ford("F-150",2017, 20000));
        dealership1.addCar(new Ford("Mustang",2012, 60000));
        dealership1.addCar(new Toyota("Corolla",2008, 120000));
        dealership1.addCar(new Toyota("Camry",2020, 20000));

        dealership2.addCar(new Honda("CR-V", 2015, 55000));
        dealership2.addCar(new Honda("Pilot", 2022, 5000));
        dealership2.addCar(new Ford("Explorer", 2016, 90000));
        Car maxCar = new Toyota("Prius", 2007, 115000);
        dealership3.addCar(maxCar);
        dealership3.addCar(new Honda("Accord", 2017, 85000));
        dealership3.addCar(new Ford("Bronco", 2019, 40000));

        allDealerships.add(dealership1);
        allDealerships.add(dealership2);
        allDealerships.add(dealership3);
        Car oldestCar;
        oldestCar = JavaFX.getOldestCar(allDealerships);
        assertEquals(maxCar, oldestCar);

    }

    /**
     * Module 3: creates JavaFX object without error.
     */
    @Test
    void JavaFXTest(){
        JavaFX tester = new JavaFX();
    }

    /**
     * Module 4: the Car class helps to simply the project by breaking up Server.java into objects which are easier to use
     * Module 5: the Ford class inherits from the Car class, and then assigned as a Car, showing polymorphism.
     */
    @Test
    void carTest(){
        Car tester = new Ford("F-150",2017, 20000);
        Car tester2 = new Ford("F-150",2010, 50000);
        assertEquals(tester.getBrand(), tester2.getBrand());

    }

    /**
     * Module 6: This class is first defined in the interface, and is implemented in the dealership class
     */
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