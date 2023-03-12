package edu.sdccd.cisc191.template;

/**
 * The car class is a parent class for the different car brands.
 * Methods common to all cars are defined here,
 * while getbrand is defined in the subclass
 */
public abstract class Car {
    String model;
    int year;
    int miles;
    void addMiles(int i){
        miles+=i;
    }

    /**
     * @return returns a string for the brand of the car.
     */
    abstract String getBrand();

    /**
     * @return returns the model of the car
     */
    String getModel(){
        return model;
    }

    /**
     * @return returns the year of the car, as a string
     */
    String getYear(){
        return ""+year;
    }

    /**
     * @return returns the number of miles, as a string
     */
    String getMiles(){
        return ""+miles;
    }

    /**
     * @return overrides the default tostring to return the brand name.
     */
    public String toString(){
        return getBrand();
    }
}
