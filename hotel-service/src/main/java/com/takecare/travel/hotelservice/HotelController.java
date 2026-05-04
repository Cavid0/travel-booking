package com.takecare.travel.hotelservice;
import java.util.List; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/hotels") public class HotelController {
  private final HotelService service; public HotelController(HotelService service){this.service=service;}
  @GetMapping public List<Hotel> all(){return service.findAll();}
  @GetMapping("/search") public List<Hotel> search(@RequestParam String city){return service.findByCity(city);}
}
