package csci310;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    public static Connection conn;
    public static String dbURLFormat = "jdbc:sqlite:db/%s.db";

    /**
     * Connect to database
     */
    public static boolean connect(String db) {
        conn = null;
        try {
            // db parameters
            String url = String.format(dbURLFormat, db);
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            // output
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Disconnect database
     */
    public static boolean disconnect() {
        try {
            if (conn != null) {
                conn.close();
            } else {
                throw new SQLException();
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Get user matching username from database
     *
     * @param username: Username to match in query
     * @return String ArrayList of first returned row
     */
    public ArrayList<String> getUser(String username) {
        ArrayList<String> results = new ArrayList<String>();
        try {
            String query = String.format("SELECT id,username,password,salt,availabilities " +
                    "FROM users " +
                    "WHERE username = '%s'", username);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                results.add(rs.getString("id"));
                results.add(rs.getString("username"));
                results.add(rs.getString("password"));
                results.add(rs.getString("salt"));
                results.add(rs.getString("availabilities"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    /**
     * Get all users
     *
     * @return User ArrayList all the Users in the table
     */
    public ArrayList<User> fetchAllUsers() {
        ArrayList<User> results = new ArrayList<User>();
        try {
            String query = "SELECT * FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String id = rs.getString("id");
                String username = rs.getString("username");
                String password = rs.getString("availabilities");
                User user = new User(id, username, password);
                results.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    /**
     * Add user to database. Does not check if user already in database
     *
     * @param username:     username to be added
     * @param password:     salted and hashed password
     * @param salt:         salt for password
     * @param availability: User unavailability in stringified JSON
     * @return boolean representing whether operation was successful or not
     */
    public Boolean addUser(String username, String password, String salt, String availability) {
        try {
            String sql = String.format(
                    "INSERT INTO users(username,password,salt,availabilities) VALUES('%s','%s','%s','%s')",
                    username, password, salt, availability);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Removes user that matches username
     * FOR TESTING PURPOSES
     *
     * @param username: Match string
     * @return boolean to indicate success or failure
     */
    public Boolean removeUser(String username) {
        ArrayList<String> results = new ArrayList<String>();
        try {
            String query = String.format("DELETE " +
                    "FROM users " +
                    "WHERE username = '%s'", username);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Modifies availability for the user that matches username
     *
     * @param username: Match string
     * @return boolean to indicate success or failure
     */
    public Boolean changeAvailability(String username, String availability) {
        ArrayList<String> results = new ArrayList<String>();
        try {
            String query = String.format("UPDATE users " +
                    "SET availabilities= '%s' " +
                    "WHERE username = '%s'", availability, username);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Gets availability for the user that matches username
     *
     * @param username: Match string
     * @return boolean to indicate success or failure
     */
    public String getAvailability(String username) {
        try {
            String query = "SELECT * FROM users WHERE username ='" + username + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString("availabilities");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "false";
        }
    }


    /**
     * Gets blocklist for the user that matches username
     *
     * @param username: Match string
     * @return boolean to indicate success or failure
     */
    public String getBlockList(String username) {
        try {
            String query = "SELECT * FROM user_blocklist WHERE username ='" + username + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString("blocklist");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Modifies blocklist for the user that matches username
     *
     * @param username: Match string
     * @return boolean to indicate success or failure
     */
    public Boolean changeBlocklist(String username, String blocklist) {
        try {
            String query = String.format("UPDATE user_blocklist " +
                    "SET blocklist= '%s' " +
                    "WHERE username = '%s'", blocklist, username);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Create blocklist for the user if there isn't one
     *
     * @param username: Match string
     * @return boolean to indicate success or failure
     */
    public Boolean createBlocklist(String username, String blocklist) {
        try {
            System.out.println("username" + username);
            System.out.println("blocklist" + blocklist);
            String query = String.format("INSERT INTO user_blocklist VALUES('%s','None','%s')", username, blocklist);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * @param username
     * @return
     */
    public Boolean userExist(String username) {
        try {
            String query = String.format("Select *" +
                    "FROM users " +
                    "WHERE username = '%s'", username);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

//    public Boolean addGroupdate(String title);

    // method to get group_id of the latest row
    public int getLatestGroupID() {
        try {
            String query = String.format("SELECT * FROM groupdates ORDER BY groupdate_id DESC LIMIT 1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs.getInt("groupdate_id");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    // method to enter PGD row into the database

    public Boolean addPGD(int groupdate_id, String inviter_username, String invitee_username, Status status, String title, String date) {
        try {
            String sql = String.format(
                    "INSERT INTO groupdates(groupdate_id,inviter_username,invitee_username,status,title,date) VALUES('%s','%s','%s','%s','%s','%s')",
                    groupdate_id, inviter_username, invitee_username, status, title, date);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    // method to enter events into the database and return the event_id
    public int addEvent(String info, int groupdate_id) {
        try {
            String sql = String.format(
                    "INSERT INTO events(groupdate_id,info) VALUES('%d','%s')",
                    groupdate_id, info);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        int last_event_id;
        try {
            String query = String.format("SELECT * FROM events ORDER BY event_id DESC LIMIT 1");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            last_event_id = rs.getInt("event_id");
            return last_event_id;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    // method to enter user_response into the database
    public Boolean addUserResponse(int event_id, String username, String response, int excitement) {
        response = "none";
        try {
            String sql = String.format(
                    "INSERT INTO user_response(event_id,username,response,excitement) VALUES('%d','%s','%s','%d')",
                    event_id, username, response, excitement);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    // method to update user_response in the database
    public Boolean updateUserResponse(int event_id, String username, String response, int excitement) {
        if (response.equals("true")){
            response = "yes";
        }
        if(response.equals("false")){
            response = "no";
        }
        try {
            String query = String.format("UPDATE user_response " +
                    "SET response= '%s', " + "excitement='%d'" +
                    "WHERE event_id = '%d' AND username='%s'", response, excitement, event_id, username);
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    //
    public Boolean updatePGDStatus_invitee(int groupdate_id, Status status, String invitee_username) {

        try {
            String query = String.format("UPDATE groupdates " +
                    "SET status= '%d' " +
                    "WHERE groupdate_id = '%d' AND invitee_username='%s'", status.ordinal(), groupdate_id, invitee_username);
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public Boolean updatePGDStatus_inviter(int groupdate_id, Status status, String inviter_username) {

        try {
            String query = String.format("UPDATE groupdates " +
                    "SET status= '%d' " +
                    "WHERE groupdate_id = '%d' AND inviter_username='%s'", status.ordinal(), groupdate_id, inviter_username);
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Groupdate> getGroupdates(String username, String role) {
        // List
        ArrayList<Groupdate> results = new ArrayList<Groupdate>();
        // Try SQL Query
        try {
            // Look for groupdates where role_username = username
            String query = String.format(
                    "SELECT groupdate_id, title, status, date, inviter_username, invitee_username\n" +
                            "FROM groupdates\n" +
                            "WHERE %s_username = '%s'" +
                            "GROUP BY groupdate_id", role, username);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            ResultSet rs = preparedStatement.executeQuery();
            // Add results to ArrayList
            while (rs.next()) {
                // Get all groupdate params
                int groupdate_id = rs.getInt("groupdate_id");
                String title = rs.getString("title");
                Status status = Status.values()[(rs.getInt("status"))];  // STATUS is INTEGER in DB for ENUM
                String date = rs.getString("date");
                String inviter = rs.getString("inviter_username");
                // Create groupdate object
                Groupdate g = new Groupdate(groupdate_id, title, status, date, inviter);
                // Add object to ArrayList
                results.add(g);
            }
        } catch (SQLException e) { // Handle Exception
            System.out.println(e.getMessage());
        }
        // Return results
        return results;
    }

    public ArrayList<Response> getResponses(int event_id) {
        // List
        ArrayList<Response> results = new ArrayList<Response>();
        // Try SQL Query
        try {
            // Look for groupdates where role_username = username
            String query = String.format(
                    "SELECT * FROM user_response" +
                            " WHERE event_id = %d", event_id);
            System.out.println(query);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
//
            // Add results to ArrayList
            while (rs.next()) {
                // Get all groupdate params
                int excitement = rs.getInt("excitement");
                String response = rs.getString("response");
                String user = rs.getString("username");
                Response user_response = new Response(user, response, excitement);
                // Create groupdate object
                Response response_obj = new Response(user, response, excitement);
                // Add object to ArrayList
                results.add(response_obj);
            }
        } catch (SQLException e) { // Handle Exception
            System.out.println(e.getMessage());
            return results;
        }
        // Return results
        return results;
    }


    public Groupdate getGroupdate(int groupdate_id) {
        // Object
        Groupdate gd = null;
        // Try SQL Query
        try {
            // Look for groupdates where role_username = username
            String query = String.format(
                    "SELECT groupdate_id, title, status, date, inviter_username\n" +
                            "FROM groupdates\n" +
                            "WHERE groupdate_id = %d\n" +
                            "GROUP BY groupdate_id", groupdate_id);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // Add results to ArrayList
            if (rs.next()) {
                // Get all groupdate params
                String title = rs.getString("title");
                Status status = Status.values()[(rs.getInt("status"))];  // STATUS is INTEGER in DB for ENUM
                String date = rs.getString("date");
                String inviter = rs.getString("inviter_username");
                // Create groupdate object
                gd = new Groupdate(groupdate_id, title, status, date, inviter);
            }
        } catch (SQLException e) { // Handle Exception
            System.out.println(e.getMessage());
        }
        // Return results
        return gd;
    }

    public ArrayList<GroupdateEvent> getGroupdateEvents(int groupdate_id) {
        // List
        ArrayList<GroupdateEvent> results = new ArrayList<GroupdateEvent>();
        // Try SQL Query
        try {
            // Look for groupdates where role_username = username
            String query = String.format(
                    "SELECT groupdate_id, event_id, info\n" +
                            "FROM events\n" +
                            "WHERE groupdate_id = %d\n" +
                            "ORDER BY event_id DESC", groupdate_id);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // Add results to ArrayList
            while (rs.next()) {
                // Get all groupdateEvent params
                int event_id = rs.getInt("event_id");
                String info = rs.getString("info");
                // Create groupdateEvent object
                GroupdateEvent ge = new GroupdateEvent(event_id, info);
                // Add object to ArrayList
                results.add(ge);
            }
        } catch (SQLException e) { // Handle Exception
            System.out.println(e.getMessage());

        }
        return results;
    }

    public Boolean insertFinalizedPGD(int groupdate_id, int event_id) {
        try {
            String sql = String.format(
                    "INSERT INTO finalized_PGD(groupdate_id,event_id,accepted_count,rejected_count) VALUES('%d','%d','%d','%d')",
                    groupdate_id, event_id, 0, 0);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }


    public FinalizedPGD getFinalizedPGD(int groupdate_id) {
        FinalizedPGD f = null;
        try {
            String query = String.format(
                    "SELECT * " +
                            "FROM finalized_PGD " +
                            "WHERE groupdate_id =%d",groupdate_id);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next()){
                // Get all groupdateEvent params
                f = new FinalizedPGD(rs.getInt("groupdate_id"),
                        rs.getInt("event_id"),
                        rs.getInt("accepted_count"),
                        rs.getInt("rejected_count"));
                // Create groupdateEvent object
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return f;
    }

}