package com.takecare.travel.hotelservice;
import java.util.*; import java.util.stream.Collectors; import org.springframework.stereotype.Service;
@Service public class HotelService {
  private final List<Hotel> items=List.of(new Hotel("Baku Palace","Baku","Deluxe",120.00), new Hotel("Istanbul Comfort","Istanbul","Standard",95.50), new Hotel("London Central","London","Suite",210.00));
  public List<Hotel> findAll(){return items;}
  public List<Hotel> findByCity(String city){String n=city.toLowerCase(Locale.ROOT); return items.stream().filter(i -> i.getCity().toLowerCase(Locale.ROOT).equals(n)).collect(Collectors.toList());}
}
