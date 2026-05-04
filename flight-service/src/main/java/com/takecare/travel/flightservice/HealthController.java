package com.takecare.travel.flightservice;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
  @GetMapping("/")
  public Map<String, String> home() {
    return Map.of(
        "service", "flight-service",
        "status", "UP",
        "endpoints", "/api/flights, /api/flights/search?origin=Baku");
  }
}