package com.takecare.travel.flightservice;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
  private final FlightService service;

  public FlightController(FlightService service) {
    this.service = service;
  }

  @GetMapping
  public List<Flight> all() {
    return service.findAll();
  }

  @GetMapping("/search")
  public List<Flight> search(
      @RequestParam(required = false) String origin,
      @RequestParam(required = false) String destination) {
    return service.search(origin, destination);
  }
}
