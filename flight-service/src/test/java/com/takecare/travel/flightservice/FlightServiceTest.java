package com.takecare.travel.flightservice;
import static org.junit.jupiter.api.Assertions.*; import org.junit.jupiter.api.Test;
class FlightServiceTest { private final FlightService service=new FlightService();
 @Test void returnsAllItems(){assertEquals(3, service.findAll().size());}
 @Test void filtersIgnoringCase(){assertFalse(service.findByOrigin("baku").isEmpty());}
}
