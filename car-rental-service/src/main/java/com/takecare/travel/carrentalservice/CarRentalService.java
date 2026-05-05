package com.takecare.travel.carrentalservice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CarRentalService {
  private final List<CarRental> items = List.of(
      new CarRental("Toyota Corolla", "Baku", 35.00),
      new CarRental("BMW 3 Series", "Istanbul", 80.00),
      new CarRental("Ford Focus", "London", 45.00),
      new CarRental("Tesla Model 3", "LosAngeles", 99.00));

  public List<CarRental> findAll() {
    return items;
  }

  public List<CarRental> findByCity(String city) {
    String n = normalize(city);
    return items.stream()
        .filter(i -> n.isEmpty() || normalize(i.getCity()).equals(n))
        .collect(Collectors.toList());
  }

  private String normalize(String value) {
    return value == null ? "" : value.trim().toLowerCase(Locale.ROOT).replace(" ", "");
  }
}
