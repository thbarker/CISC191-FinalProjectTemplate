package edu.sdccd.cisc191.template;

import java.util.ArrayList;

/**
 * this interface defined the methods used in the dealership class
 */
public interface Interface {
    /**
     * @return the get all cars returns an arraylist of all car objects added to the dealership
     * it returns the list in the order the objects were added or sorted
     */
    public ArrayList<Car> getAllCars();

    /**
     * @param newCar this method takes a car object and adds it to the end of the arraylist
     */
    public void addCar(Car newCar);

    /**
     * @return this returns the location of the dealership
     */
    public String getLocation();

    /**
     * @param car used to remove a car from the arraylist.
     *            The Car object that is passed into the method
     *            must be the one in the arraylist
     *
     */
    public void removeCar(Car car);

    /**
     * @param criteria this method sorts the arraylist based on the criteria
     *                  there are 4 criteria, brand, model, year, and miles
     */
    public void sort(String criteria);

}
