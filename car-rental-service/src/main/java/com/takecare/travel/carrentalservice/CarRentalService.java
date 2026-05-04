package com.takecare.travel.carrentalservice;
import java.util.*; import java.util.stream.Collectors; import org.springframework.stereotype.Service;
@Service public class CarRentalService {
  private final List<CarRental> items=List.of(new CarRental("Toyota Corolla","Baku",35.00), new CarRental("BMW 3 Series","Istanbul",80.00), new CarRental("Ford Focus","London",45.00));
  public List<CarRental> findAll(){return items;}
  public List<CarRental> findByCity(String city){String n=city.toLowerCase(Locale.ROOT); return items.stream().filter(i -> i.getCity().toLowerCase(Locale.ROOT).equals(n)).collect(Collectors.toList());}
}
