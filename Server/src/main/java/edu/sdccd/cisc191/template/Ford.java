package edu.sdccd.cisc191.template;

public class Ford extends Car{

    public Ford(String model, int year, int miles){
        this.model = model;
        this.year = year;
        this.miles = miles;
    }
    public String getBrand(){
        return "Ford";
    }
}
