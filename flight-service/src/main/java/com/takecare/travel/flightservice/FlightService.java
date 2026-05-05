package com.takecare.travel.flightservice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
  private final List<Flight> items = List.of(
      new Flight("AZAL101", "Baku", "Istanbul", 199.99),
      new Flight("THY202", "Istanbul", "London", 249.50),
      new Flight("LH303", "Baku", "Frankfurt", 299.00),
      new Flight("AA404", "NYC", "LAX", 189.00),
      new Flight("UA505", "NYC", "LosAngeles", 205.00));

  public List<Flight> findAll() {
    return items;
  }

  public List<Flight> findByOrigin(String origin) {
    return search(origin, null);
  }

  public List<Flight> search(String origin, String destination) {
    String o = normalize(origin);
    String d = normalize(destination);
    return items.stream()
        .filter(i -> o.isEmpty() || normalize(i.getOrigin()).equals(o))
        .filter(i -> d.isEmpty() || normalize(i.getDestination()).equals(d))
        .collect(Collectors.toList());
  }

  private String normalize(String value) {
    return value == null ? "" : value.trim().toLowerCase(Locale.ROOT).replace(" ", "");
  }
}
