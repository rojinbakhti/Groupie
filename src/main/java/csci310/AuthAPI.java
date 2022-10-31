package csci310;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

public class AuthAPI {

    final int SALT_INDEX = 3;
    final int PASSWORD_INDEX = 2;
    public String passwordAlgorithm = "MD5";
    public String saltAlgorithm = "SHA1PRNG";
    public String dbURL = "groupdate";

    /**
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    byte[] getSalt() throws NoSuchAlgorithmException
    {
        // Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance(saltAlgorithm);
        // Create array for salt
        byte[] salt = new byte[16];
        // Get a random salt
        sr.nextBytes(salt);
        // Return salt
        return salt;
    }

    /**
     *
     * @param passwordToHash
     * @param salt
     * @return
     */
    String getSecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance(passwordAlgorithm);
            // Add password bytes to digest
            md.update(salt);
            // Get hashed password
            byte [] hashedPassword = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            generatedPassword =  Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        // Return
        return generatedPassword;
    }

    /**
     * Accepts username and unhashed password to verify credentials
     * @param username: username to match
     * @param password: unhashed password to salt and hash
     * @return int value equal 0 for success
     */
    public int verifyCredentials(String username, String password) {
        // Connect to database
        Database db = new Database();
        Database.connect(dbURL);
        // Lookup username row
        ArrayList<String> row = db.getUser(username);
        if(row.isEmpty()){
            Database.disconnect();
            return 1;
        }
        // Hash password
        byte[] salt = Base64.getDecoder().decode(row.get(SALT_INDEX).getBytes());
        String hashedPassword = getSecurePassword(password, salt);
//        System.out.println(Base64.getEncoder().encodeToString(salt));
//        System.out.println(hashedPassword);
        // Verify credentials
        if(!hashedPassword.equals(row.get(PASSWORD_INDEX))){
            Database.disconnect();
            return 2;
        }
        // Return
        Database.disconnect();
        return 0;
    }

    /**
     * Create new user in database, if username doesn't exist
     * @param username: new unique account
     * @param password: unhashed password
     * @return int value equal 0 for success
     */
    public int createUser(String username, String password) {
        // Connect to database
        Database db = new Database();
        Database.connect(dbURL);
        // Verify username is not taken
        //ArrayList<String> row = db.getUser(username);
        if(db.userExist(username)){
            Database.disconnect();
//            System.out.println(row);
            return 1;
        }
        // Hash password
        byte[] salt;
        try {
            salt = getSalt();
        }catch(NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            Database.disconnect();
            return 2;
        }
        String hashedPassword = getSecurePassword(password, salt);
        // Encode salt
        String encoded = Base64.getEncoder().encodeToString(salt);
//        System.out.println("createUser " + hashedPassword + " " + encoded);
        // Insert into database
        db.addUser(username, hashedPassword, encoded, "");
        // Return
        Database.disconnect();
        return 0;
    }
}
