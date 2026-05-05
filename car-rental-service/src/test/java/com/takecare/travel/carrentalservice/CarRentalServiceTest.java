package com.takecare.travel.carrentalservice;
import static org.junit.jupiter.api.Assertions.*; import org.junit.jupiter.api.Test;
class CarRentalServiceTest { private final CarRentalService service=new CarRentalService();
 @Test void returnsAllItems(){assertEquals(4, service.findAll().size());}
 @Test void filtersIgnoringCase(){assertFalse(service.findByCity("london").isEmpty());}
 @Test void filtersByLocationExample(){assertFalse(service.findByCity("Los Angeles").isEmpty());}
}
