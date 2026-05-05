package com.takecare.travel.hotelservice;
import static org.junit.jupiter.api.Assertions.*; import org.junit.jupiter.api.Test;
class HotelServiceTest { private final HotelService service=new HotelService();
 @Test void returnsAllItems(){assertEquals(4, service.findAll().size());}
 @Test void filtersIgnoringCase(){assertFalse(service.findByCity("baku").isEmpty());}
 @Test void filtersByLocationExample(){assertFalse(service.findByCity("Los Angeles").isEmpty());}
}
