package com.takecare.travel.flightservice;
import java.util.List; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/flights") public class FlightController {
  private final FlightService service; public FlightController(FlightService service){this.service=service;}
  @GetMapping public List<Flight> all(){return service.findAll();}
  @GetMapping("/search") public List<Flight> search(@RequestParam String origin){return service.findByOrigin(origin);}
}
