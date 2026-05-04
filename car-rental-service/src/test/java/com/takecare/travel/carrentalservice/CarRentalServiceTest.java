package com.takecare.travel.carrentalservice;
import static org.junit.jupiter.api.Assertions.*; import org.junit.jupiter.api.Test;
class CarRentalServiceTest { private final CarRentalService service=new CarRentalService();
 @Test void returnsAllItems(){assertEquals(3, service.findAll().size());}
 @Test void filtersIgnoringCase(){assertFalse(service.findByCity("london").isEmpty());}
}
