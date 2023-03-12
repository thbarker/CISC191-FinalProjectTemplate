package edu.sdccd.cisc191.template;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * the dealership class is used to manage an arraylist of car objects.
 * the arraylist can be modified or accessed from the various methods implemented in this class.
 */
public class Dealership implements Interface {
    /**
     *instance variable that stores all cars in the dealership
     */
    private ArrayList<Car> allCars = new ArrayList<>();
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
    public ArrayList<Car> getAllCars(){
        return allCars;
    }

    /**
     * @param toAdd this method takes a car object and adds it to the end of the arraylist
     */
    @Override
    public void addCar(Car toAdd){
        allCars.add(toAdd);
    }

    /**
     * @return  @inheritDoc
     */
    public String getLocation(){
        return location;
    }

    /**
     * @param car used to remove a car from the arraylist.
     *            The Car object that is passed into the method
     *            must be the one in the arraylist
     */
    public void removeCar(Car car){
        allCars.remove(allCars.indexOf(car));
    }

    /**
     * @param criteria this method sorts the arraylist based on the criteria
     *                 there are 4 criteria, brand, model, year, and miles
     */
    public void sort(String criteria){
        ArrayList<Car> tempArr = new ArrayList<>();
        while(allCars.size()>0){
            int maxIndex = 0;
            for(int i =0; i<allCars.size(); i++){
                if(criteria=="Brand"){
                    String currentBrand = allCars.get(i).getBrand();
                    String maxBrand = allCars.get(maxIndex).getBrand();
                    if(currentBrand.compareTo(maxBrand)<0){
                        maxIndex = i;
                    }
                }
                if(criteria=="Model"){
                    String currentModel = allCars.get(i).getModel();
                    String maxModel = allCars.get(maxIndex).getModel();
                    if(currentModel.compareTo(maxModel)<0){
                        maxIndex = i;
                    }
                }
                if(criteria=="Year"){
                    int currentYear = parseInt(allCars.get(i).getYear());
                    int maxYear = parseInt(allCars.get(maxIndex).getYear());
                    if(currentYear>maxYear){
                        maxIndex = i;
                    }
                }
                if(criteria=="Miles"){
                    int currentMiles = parseInt(allCars.get(i).getMiles());
                    int maxMiles = parseInt(allCars.get(maxIndex).getMiles());
                    if(currentMiles>maxMiles){
                        maxIndex = i;
                    }
                }
            }
            tempArr.add(allCars.get(maxIndex));
            allCars.remove(maxIndex);
        }
        allCars = tempArr;
    }
}
