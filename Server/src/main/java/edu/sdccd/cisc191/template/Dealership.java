package edu.sdccd.cisc191.template;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

/**
 * the dealership class is used to manage an arraylist of car objects.
 * the arraylist can be modified or accessed from the various methods implemented in this class.
 */
public class Dealership implements Interface {
    /**
     *instance variable that stores all cars in the dealership
     */
    private Car[] allCars = new Car[0];
    /**
     * instance variable for the location
     */
    private String location;

    /**
     * @param location constructor to create a new dealership. requires a location
     */
    public Dealership(String location){
        this.location = location;
    }


    /**
     * @return  @inheritDoc
     */
    public Car[] getAllCars(){
        return allCars;
    }

    /**
     * @param toAdd this method takes a car object and adds it to the end of the arraylist by expanding it
     */
    @Override
    public void addCar(Car toAdd){

        Car[] destArray = new Car[allCars.length+1];
        for(int i=0; i<allCars.length; i++){
            destArray[i] = allCars[i];
        }
        destArray[destArray.length - 1] = toAdd;
        allCars = destArray;
    }

    /**
     * @return  @inheritDoc
     */
    public String getLocation(){
        return location;
    }

    /**
     * @param car used to remove a car from the all cars array
     */
    public void removeCar(Car car){
        Car[] destArray = new Car[allCars.length - 1];

        for (int i = 0, k = 0; i < allCars.length; i++) {
            if (allCars[i] == car) {
                continue;
            }
            destArray[k++] = allCars[i];
        }
        allCars = destArray;
    }

    /**
     * @param criteria this method sorts the arraylist based on the criteria
     *                 there are 4 criteria, brand, model, year, and miles
     */
    public void sort(String criteria){
        Car[] tempArr = new Car[allCars.length];
        int index = 0;
        while(allCars.length>0){
            int maxIndex = 0;
            for(int i =0; i<allCars.length; i++){
                if(criteria=="Brand"){
                    String currentBrand = allCars[i].getBrand();
                    String maxBrand = allCars[maxIndex].getBrand();
                    if(currentBrand.compareTo(maxBrand)<0){
                        maxIndex = i;
                    }
                }
                if(criteria=="Model"){
                    String currentModel = allCars[i].getModel();
                    String maxModel = allCars[maxIndex].getModel();
                    if(currentModel.compareTo(maxModel)<0){
                        maxIndex = i;
                    }
                }
                if(criteria=="Year"){
                    int currentYear = parseInt(allCars[i].getYear());
                    int maxYear = parseInt(allCars[maxIndex].getYear());
                    if(currentYear>maxYear){
                        maxIndex = i;
                    }
                }
                if(criteria=="Miles"){
                    int currentMiles = parseInt(allCars[i].getMiles());
                    int maxMiles = parseInt(allCars[maxIndex].getMiles());
                    if(currentMiles>maxMiles){
                        maxIndex = i;
                    }
                }
            }
            tempArr[index++] = allCars[maxIndex];
            removeCar(allCars[maxIndex]);
        }
        allCars = tempArr;
    }
    public String toString(){
        String ret = "[";
        for(int i=0; i<allCars.length; i++){
            ret+=allCars[i];
            if(i<allCars.length-1){
                ret+=", ";
            }
        }
        return ret+"]";
    }
}
