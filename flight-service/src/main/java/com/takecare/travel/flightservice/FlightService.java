package com.takecare.travel.flightservice;
import java.util.*; import java.util.stream.Collectors; import org.springframework.stereotype.Service;
@Service public class FlightService {
  private final List<Flight> items=List.of(new Flight("AZAL101","Baku","Istanbul",199.99), new Flight("THY202","Istanbul","London",249.50), new Flight("LH303","Baku","Frankfurt",299.00));
  public List<Flight> findAll(){return items;}
  public List<Flight> findByOrigin(String origin){String n=origin.toLowerCase(Locale.ROOT); return items.stream().filter(i -> i.getOrigin().toLowerCase(Locale.ROOT).equals(n)).collect(Collectors.toList());}
}
