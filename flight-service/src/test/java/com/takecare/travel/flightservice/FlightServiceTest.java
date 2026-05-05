package com.takecare.travel.flightservice;
import static org.junit.jupiter.api.Assertions.*; import org.junit.jupiter.api.Test;
class FlightServiceTest { private final FlightService service=new FlightService();
 @Test void returnsAllItems(){assertEquals(5, service.findAll().size());}
 @Test void filtersIgnoringCase(){assertFalse(service.findByOrigin("baku").isEmpty());}
 @Test void searchesByOriginAndDestination(){assertEquals(1, service.search("NYC", "LAX").size());}
}
