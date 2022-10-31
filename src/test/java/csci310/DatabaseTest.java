package csci310;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DatabaseTest {

    Database db;

    @Before
    public void setUp() throws Exception {
        db = new Database();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConnect() {
        assertTrue(Database.connect("groupdate"));
        Database.disconnect();
        // catch case
        String currDBURLFormat = Database.dbURLFormat;
        Database.dbURLFormat = "";
        assertFalse(Database.connect("groupdate"));
        Database.dbURLFormat = currDBURLFormat;
    }

    @Test
    public void testDisconnect() {
        // main case
        Database.connect("groupdate");
        assertTrue(Database.disconnect());
        Database.conn = null;
        assertFalse(Database.disconnect());
    }

    @Test
    public void testGetUser() {
        // main case
        Database.connect("groupdate");
        assertTrue(db.addUser("testUser", "", "", ""));
        ArrayList<String> rs = db.getUser("testUser");
        assertFalse(rs.isEmpty());
        assertTrue(db.removeUser("testUser"));
        Database.disconnect();
        // catch case
        rs = db.getUser("testUser");
        assertTrue(rs.isEmpty());
    }


    @Test
    public void testAddUser() {
        Database.connect("groupdate");
        assertTrue(db.addUser("testUser", "", "", ""));
        assertTrue(db.removeUser("testUser"));
        Database.disconnect();
        assertFalse(db.addUser("testUser", "", "", ""));
    }

    @Test
    public void testRemoveUser() {
        Database.connect("groupdate");
        assertTrue(db.addUser("testUser", "", "", ""));
        assertTrue(db.removeUser("testUser"));
        Database.disconnect();
        assertFalse(db.removeUser("testUser"));
    }

    @Test
    public void testGetAvailability() throws InterruptedException {
        Database db = new Database();
        Database.connect("groupdate");
        db.addUser("testingUser","123456","saltpasswprd","testAvailability");
        assertEquals("testAvailability",db.getAvailability("testingUser"));
        Database.disconnect();
        assertEquals("false",db.getAvailability("rojinbhakti"));
        Thread.sleep(2000);
    }

    @Test
    public void testChangeAvailability() throws InterruptedException {
        Database db = new Database();
        Database.connect("groupdate");
        db.addUser("testingUser","123456","saltpasswprd","testAvailability");
        Database.disconnect();
        Database.connect("groupdate");
        db.changeAvailability("testingUser","newTestAvailability");
        assertEquals("newTestAvailability",db.getAvailability("testingUser"));
        db.removeUser("testingUser");
        Database.disconnect();
        assertEquals(false,db.changeAvailability("testingUser","testAvailability"));
        Thread.sleep(2000);

    }
    @Test
    public void testUserExist(){
        Database db = new Database();
        Database.connect("groupdate");
        assertTrue(db.userExist("aravadani"));
        Database.disconnect();
        assertFalse(db.userExist("aravadani"));
    }

    @Test
    public void testFetchAllUsers() {
        // main case
        Database.connect("groupdate");
        assertTrue(db.addUser("choco2", "", "", ""));
        assertTrue(db.addUser("chorong2", "", "", ""));
        ArrayList<User> users = db.fetchAllUsers();
        users.get(0).getId();
        users.get(0).getUsername();
        assertEquals(users.get(0).getUsername(),"aravadani");

        users.get(0).getAvailabilities();
        assertTrue(users.size() >=2);
        assertFalse(users.isEmpty());
        assertTrue(db.removeUser("choco2"));
        assertTrue(db.removeUser("chorong2"));
        Database.disconnect();
        db.fetchAllUsers();
    }

    @Test
    public void testGetBlockList() {
        // main case
        Database.connect("groupdate");
        String result = db.getBlockList("rojin");
        assertEquals(result,"user1,user2,user3");
        Database.disconnect();
        db.getBlockList("rojin");

    }

    @Test
    public void testChangeBlockList() {
        // main case
        Database.connect("groupdate");
        db.changeBlocklist("rojin","user1,user2,user4");
        assertEquals(db.getBlockList("rojin"),"user1,user2,user4");
        db.changeBlocklist("rojin","user1,user2,user3");
        Database.disconnect();
        db.changeBlocklist("rojin","user1,user2,user3");

    }
    @Test
    public void testGetLatestGroupID() {
        Database.connect("groupdate");
        assertNotEquals(db.getLatestGroupID(),-1);
        Database.disconnect();
        assertEquals(db.getLatestGroupID(),-1);

    }

    @Test
    public void testAddPGD() {
        Database.connect("groupdate");
        int lastRow = db.getLatestGroupID();
        lastRow++;
        assertNotEquals(db.addPGD(lastRow,"inviter_user","invitee_user", Status.inviter_unfinalized,"title","todaysdate"),false);
        Database.disconnect();
        assertEquals(db.addPGD(lastRow,"inviter_user","invitee_user", Status.inviter_unfinalized,"title","todaysdate"),false);
    }

    @Test
    public void testCreateBlockList() {
        // main case
        Database.connect("groupdate");
        db.createBlocklist("dasol", "rojin");
        assertEquals(db.getBlockList("dasol"),"rojin");
        Database.disconnect();
        db.createBlocklist("dasol", "rojin");
    }

    @Test
    public void testAddEvent() {
        Database.connect("groupdate");
        assertNotEquals(db.addEvent("eventInfo",-1),-1);
        Database.disconnect();
        assertEquals(db.addEvent("eventInfo",-1),-1);

    }
    @Test
    public void testAddUserResponse() {
        Database.connect("groupdate");
        assertNotEquals(db.addUserResponse(341,"responseuser","yes",3),false);
        Database.disconnect();
        assertEquals(db.addUserResponse(341,"responseuser","yes",3),false);
    }
    @Test
    public void testUpdateUserResponse() {
        Database.connect("groupdate");
        assertNotEquals(db.updateUserResponse(341,"responseuser","true",4),false);
        Database.disconnect();
        assertEquals(db.updateUserResponse(341,"responseuser","false",4),false);
    }

    @Test
    public void testGetGroupdates() {
        Database.connect("groupdate");
        assertFalse(db.getGroupdates("aravadani", "inviter").isEmpty());
        Database.disconnect();
        db.getGroupdates("aravadani", "inviter");
    }

    @Test
    public void testUpdatePGDStatus_invitee(){
        Database.connect("groupdate");
        assertNotEquals(db.updatePGDStatus_invitee(4,Status.invitee_responded,"userTest1"),false);
        Database.disconnect();
        assertEquals(db.updatePGDStatus_invitee(4,Status.invitee_responded,"userTest1"),false);
    }

    @Test
    public void testUpdatePGDStatus_inviter(){
        Database.connect("groupdate");
        assertNotEquals(db.updatePGDStatus_inviter(4,Status.invitee_accepted,"proposeServletUser"),false);
        Database.disconnect();
        assertEquals(db.updatePGDStatus_inviter(4,Status.invitee_responded,"userTest1"),false);
    }

    @Test
    public void testgetResponses(){
        Database.connect("groupdate");
        db.addUserResponse(87,"testgetResponses","yes",0);
        assertFalse(db.getResponses(87).isEmpty() );
        Database.disconnect();
        assertTrue(db.getResponses(87).isEmpty() );
    }

    @Test
    public void testGetGroupdate(){
        Database.connect("groupdate");
        db.getGroupdate(99999);
        assertEquals(3, db.getGroupdate(3).groupdate_id);
        Database.disconnect();
        db.getGroupdate(3);
    }

    @Test
    public void testGetGroupdateEvents(){
        Database.connect("groupdate");
        assertFalse(db.getGroupdateEvents(3).isEmpty());
        Database.disconnect();
        db.getGroupdateEvents(3);
    }


    @Test
    public void testInsertFinalizedPGD(){
        Database.connect("groupdate");
        assertTrue(db.insertFinalizedPGD(6,5));
        Database.disconnect();
        db.insertFinalizedPGD(5,5);
    }

    @Test
    public void testGetFinalizedPGD(){
        Database.connect("groupdate");
        db.insertFinalizedPGD(6,5);
        FinalizedPGD f = db.getFinalizedPGD(6);
        db.getFinalizedPGD(666);
        assertEquals(f.event_id , 5);
        Database.disconnect();
        db.getFinalizedPGD(5);
    }


}

