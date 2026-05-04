package com.takecare.travel.hotelservice;
public class Hotel { private final String name; private final String city; private final String roomType; private final double pricePerNight;
    public Hotel(String name,String city,String roomType,double pricePerNight){this.name=name;this.city=city;this.roomType=roomType;this.pricePerNight=pricePerNight;}
    public String getName(){return name;} public String getCity(){return city;} public String getRoomType(){return roomType;} public double getPricePerNight(){return pricePerNight;} }
