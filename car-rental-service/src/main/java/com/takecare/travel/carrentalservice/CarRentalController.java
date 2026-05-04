package com.takecare.travel.carrentalservice;
import java.util.List; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/cars") public class CarRentalController {
  private final CarRentalService service; public CarRentalController(CarRentalService service){this.service=service;}
  @GetMapping public List<CarRental> all(){return service.findAll();}
  @GetMapping("/search") public List<CarRental> search(@RequestParam String city){return service.findByCity(city);}
}
