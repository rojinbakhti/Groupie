package csci310;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AvailabilityTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAdd() {
        Availability availability = new Availability();
        availability.add("01-11-2021 15:00:00","01-11-2021 15:45:00","JLoConcert");
        availability.add("01-15-2021 15:00:00","01-17-2021 15:45:00","JBConcert");
        availability.add("02-17-2021 15:00:00","01-17-2021 15:45:00","Available");
        assertEquals("[{\"startTime\":\"01-11-2021 15:00:00\",\"endTime\":\"01-11-2021 15:45:00\",\"event\":\"JLoConcert\"},{\"startTime\":\"01-15-2021 15:00:00\",\"endTime\":\"01-17-2021 15:45:00\",\"event\":\"JBConcert\"},{\"startTime\":\"02-17-2021 15:00:00\",\"endTime\":\"01-17-2021 15:45:00\",\"event\":\"Available\"}]",availability.saveAvailability());
    }
    @Test
    public void testRemove() {
        Availability availability = new Availability();
        availability.add("01-11-2021 15:00:00","01-11-2021 15:45:00","JLoConcert");
        availability.add("01-15-2021 15:00:00","01-17-2021 15:45:00","JBConcert");
        availability.add("02-17-2021 15:00:00","01-17-2021 15:45:00","Available");
        availability.remove("02-17-2021 15:00:00","01-17-2021 15:45:00","Available"); //T T T
        availability.remove("03-17-2021 15:00:00","01-17-2021 15:45:00","Available");
//        availability.remove("02-17-2021 15:00:00","01-17-2021 15:45:00","notAvailable"); //T T F
//        availability.remove("03-17-2021 15:00:00","01-17-2021 15:45:00","Available");//F T T
//        availability.remove("02-17-2021 15:00:00","03-17-2021 15:45:00","Available");//T F T
//        availability.remove("05-17-2021 15:00:00","05-17-2021 15:45:00","Available");//F F T
//        availability.remove("02-17-2021 15:00:00","05-17-2021 15:45:00","notAvailable");// T F F
//        availability.remove("06-17-2021 15:00:00","01-17-2021 15:45:00","notAvailable");// T F T
//        availability.remove("06-18-2021 15:00:00","01-18-2021 15:45:00","Available");// F T F
        assertEquals("[{\"startTime\":\"01-11-2021 15:00:00\",\"endTime\":\"01-11-2021 15:45:00\",\"event\":\"JLoConcert\"},{\"startTime\":\"01-15-2021 15:00:00\",\"endTime\":\"01-17-2021 15:45:00\",\"event\":\"JBConcert\"}]",availability.saveAvailability());
    }

    @Test
    public void testSaveAvailability() {
        Availability availability = new Availability();
        availability.add("01-11-2021 15:00:00","01-11-2021 15:45:00","JLoConcert");
        availability.add("01-15-2021 15:00:00","01-17-2021 15:45:00","JBConcert");
        availability.add("02-17-2021 15:00:00","01-17-2021 15:45:00","Available");
        assertEquals( "[{\"startTime\":\"01-11-2021 15:00:00\",\"endTime\":\"01-11-2021 15:45:00\",\"event\":\"JLoConcert\"},{\"startTime\":\"01-15-2021 15:00:00\",\"endTime\":\"01-17-2021 15:45:00\",\"event\":\"JBConcert\"},{\"startTime\":\"02-17-2021 15:00:00\",\"endTime\":\"01-17-2021 15:45:00\",\"event\":\"Available\"}]",availability.saveAvailability());
    }
    @Test
    public void testReadAvailability() {
        Availability availability = new Availability();
        availability.add("01-11-2021 15:00:00", "01-11-2021 15:45:00", "JLoConcert");
        availability.add("01-15-2021 15:00:00", "01-17-2021 15:45:00", "JBConcert");
        availability.add("02-17-2021 15:00:00", "01-17-2021 15:45:00", "Available");
        Availability availability2 = new Availability();
        String result = availability.saveAvailability();
        availability2.readAvailability(availability.saveAvailability());
        Assert.assertEquals(result,availability2.saveAvailability());
    }
}