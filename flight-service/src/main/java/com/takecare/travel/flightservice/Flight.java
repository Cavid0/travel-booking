package com.takecare.travel.flightservice;
public class Flight { private final String flightNumber; private final String origin; private final String destination; private final double price;
    public Flight(String flightNumber,String origin,String destination,double price){this.flightNumber=flightNumber;this.origin=origin;this.destination=destination;this.price=price;}
    public String getFlightNumber(){return flightNumber;} public String getOrigin(){return origin;} public String getDestination(){return destination;} public double getPrice(){return price;} }
