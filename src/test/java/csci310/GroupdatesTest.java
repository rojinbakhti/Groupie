package csci310;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupdatesTest {

    Groupdates gd;

    @Before
    public void setUp() throws Exception {
        gd = new Groupdates();
    }

    @Test
    public void testGetGroupDates() {
        assertNotEquals("[]", gd.getGroupDates("aravadani", "inviter"));
        System.out.println(gd.getGroupDates("dasollee", "invitee"));
    }

    @Test
    public void testGetEvents() {
        assertNotEquals("[]", gd.getEvents(3));
        System.out.println(gd.getEvents(3));
    }
}