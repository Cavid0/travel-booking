package com.takecare.travel.hotelservice;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
  @GetMapping("/")
  public Map<String, String> home() {
    return Map.of(
        "service", "hotel-service",
        "status", "UP",
        "endpoints", "/api/hotels, /api/hotels/search?city=Baku");
  }
}