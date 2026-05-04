package com.takecare.travel.apigateway;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping("/")
  public Map<String, String> home() {
    return Map.of(
        "service", "api-gateway",
        "status", "UP",
        "routes", "/api/flights, /api/hotels, /api/cars");
  }
}