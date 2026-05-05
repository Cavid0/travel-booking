package com.takecare.travel.carrentalservice;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarRentalController {
  private final CarRentalService service;

  public CarRentalController(CarRentalService service) {
    this.service = service;
  }

  @GetMapping
  public List<CarRental> all() {
    return service.findAll();
  }

  @GetMapping("/search")
  public List<CarRental> search(
      @RequestParam(required = false) String city,
      @RequestParam(required = false) String location) {
    return service.findByCity(location != null ? location : city);
  }
}
