package csci310.servlets;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import csci310.Database;


public class SettingsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get parameters from POST
        String username=request.getParameter("username");
        String user = request.getParameter("user");
        String action=request.getParameter("action");
        // Set up db
        String dbURL = "groupdate";
        Database db = new Database();
        Database.connect(dbURL);
        // Create response
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        //response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        //Get user blocklist from database
        String blocklist_str = db.getBlockList(username);
        String blocked_by_str = db.getBlockList(username);
        List<String> blocklist = new ArrayList<String>();
        //get user block list, if not null
        System.out.println("blocked_by_str: " + blocked_by_str);
        if (blocklist_str != null) {
            if (action.equals("add")){

                // add user to block list
                blocklist = new ArrayList(Arrays.asList(blocklist_str.split(",")));
                blocklist.add(user);

                db.changeBlocklist(username, String. join(",", blocklist));
                out.print(String. join(",", blocklist));

            }
            else if (action.equals("remove")){
                if (blocklist_str.isEmpty()){
                    out.print("empty");
                    
                }else{
                    //
                    // remove user from block list

                    blocklist = new ArrayList(Arrays.asList(blocklist_str.split(",")));
                    blocklist.remove(user);
                    db.changeBlocklist(username, String. join(",", blocklist));
                    out.print(String. join(",", blocklist));
                }
            }
            else if(action.equals("show")){
                if (blocklist_str.isEmpty()){
                    out.print("empty");
                } else{
                    // return block list
                    out.print(blocklist_str);
                }
                

            }

        } else{
            if (action.equals("add")){
                db.createBlocklist(username, user);
                String blocked_by_str1 = db.getBlockList(username);
                System.out.println("blocked_by_str1"+blocked_by_str1);
                out.print(blocked_by_str1);
            }
            else if (action.equals("remove")){
                out.print("empty");
            }
            else if(action.equals("show")){

                // Block list empty
                out.print("empty");
            }

        }
        Database.disconnect();
    }


}









