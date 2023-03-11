package edu.sdccd.cisc191.template;

public class Toyota extends Car{
    public Toyota(String model, int year, int miles){
        this.model = model;
        this.year = year;
        this.miles = miles;
    }
    public String getBrand(){
        return "Toyota";
    }
}
