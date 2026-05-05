package com.takecare.travel.hotelservice;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
  private final HotelService service;

  public HotelController(HotelService service) {
    this.service = service;
  }

  @GetMapping
  public List<Hotel> all() {
    return service.findAll();
  }

  @GetMapping("/search")
  public List<Hotel> search(
      @RequestParam(required = false) String city,
      @RequestParam(required = false) String location) {
    return service.findByCity(location != null ? location : city);
  }
}
