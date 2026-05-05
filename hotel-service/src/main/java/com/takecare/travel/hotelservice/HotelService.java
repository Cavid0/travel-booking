package com.takecare.travel.hotelservice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
  private final List<Hotel> items = List.of(
      new Hotel("Baku Palace", "Baku", "Deluxe", 120.00),
      new Hotel("Istanbul Comfort", "Istanbul", "Standard", 95.50),
      new Hotel("London Central", "London", "Suite", 210.00),
      new Hotel("LA Sunset Hotel", "LosAngeles", "Queen", 160.00));

  public List<Hotel> findAll() {
    return items;
  }

  public List<Hotel> findByCity(String city) {
    String n = normalize(city);
    return items.stream()
        .filter(i -> n.isEmpty() || normalize(i.getCity()).equals(n))
        .collect(Collectors.toList());
  }

  private String normalize(String value) {
    return value == null ? "" : value.trim().toLowerCase(Locale.ROOT).replace(" ", "");
  }
}
