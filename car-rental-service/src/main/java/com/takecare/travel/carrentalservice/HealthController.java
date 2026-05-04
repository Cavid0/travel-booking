package com.takecare.travel.carrentalservice;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
  @GetMapping("/")
  public Map<String, String> home() {
    return Map.of(
        "service", "car-rental-service",
        "status", "UP",
        "endpoints", "/api/cars, /api/cars/search?city=London");
  }
}