package csci310;

import io.cucumber.java.bs.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.Assert.*;

public class AuthAPITest {

    AuthAPI a;
    Database db;

    @Before
    public void setUp() throws Exception {
        a = new AuthAPI();
        db = new Database();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetSalt() {
        try{
            String salt = Base64.getEncoder().encodeToString(a.getSalt());
            assertEquals(24, salt.length());
//            System.out.println(salt);
        } catch(NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetSecurePassword() {
        // try case
        byte[] salt = new byte[16];
        try{
            salt = a.getSalt();
            assertEquals(24, Base64.getEncoder().encodeToString(salt).length());
            String password = a.getSecurePassword("password", salt);
            assertEquals(24, password.length());
//            System.out.println(Base64.getEncoder().encodeToString(salt));
//            System.out.println(password);
        } catch(NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }

        // catch case
        String currAlgo = a.passwordAlgorithm;
        a.passwordAlgorithm = "";
        assertNull(a.getSecurePassword("password", salt));
        a.passwordAlgorithm = currAlgo;
    }

    @Test
    public void testVerifyCredentials() {
        // test setup
        Database.connect("groupdate");
        db.addUser("testUser", "X03MO1qnZdYdgyfeuILPmQ==", "", "");
        Database.disconnect();
        // main test
        assertEquals(0, a.verifyCredentials("testUser", "password"));
        // failed username match test
        assertEquals(1, a.verifyCredentials("testUsers", "password"));
        // failed password match test
        assertEquals(2, a.verifyCredentials("testUser", "notpassword"));
        // test tear down
        Database.connect("groupdate");
        db.removeUser("testUser");
        Database.disconnect();
    }

    @Test
    public void testCreateUser() {
        // main case
        assertEquals(0, a.createUser("testUser", "password"));
        assertEquals(0, a.verifyCredentials("testUser", "password"));
        // failed username match test
        assertEquals(1, a.createUser("testUser", "password"));
        // test tear down
        Database.connect("groupdate");
        db.removeUser("testUser");
        Database.disconnect();
        // catch case
        String currAlgo = a.saltAlgorithm;
        a.saltAlgorithm = "";
        assertEquals(2, a.createUser("testUser", "password"));
        a.saltAlgorithm = currAlgo;
    }
}