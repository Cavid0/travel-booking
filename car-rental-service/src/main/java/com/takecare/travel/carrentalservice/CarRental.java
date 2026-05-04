package com.takecare.travel.carrentalservice;
public class CarRental { private final String model; private final String city; private final double dailyRate;
    public CarRental(String model,String city,double dailyRate){this.model=model;this.city=city;this.dailyRate=dailyRate;}
    public String getModel(){return model;} public String getCity(){return city;} public double getDailyRate(){return dailyRate;} }
