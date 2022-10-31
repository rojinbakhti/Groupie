package csci310.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import csci310.Availability;
import csci310.Database;
import csci310.Status;

public class FinalizeServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get parameters from POST
        String decision = request.getParameter("decision");
        String groupdate_id = request.getParameter("groupdate_id");
        String event_id = request.getParameter("event_id");
        String inviter_username = request.getParameter("username");
        // Set up db
        String dbURL = "groupdate";
        Database db = new Database();
        Database.connect(dbURL);
        System.out.println(decision);
        System.out.println(groupdate_id);
        PrintWriter out = response.getWriter();
        if (decision.equals("finalize")) {
            System.out.println(db.updatePGDStatus_inviter(Integer.parseInt(groupdate_id), Status.inviter_finalized,inviter_username)+"updated pgd status to finalized");
            db.insertFinalizedPGD(Integer.parseInt(groupdate_id),Integer.parseInt(event_id));
            out.print("event_finalized");

        }

        response.setStatus(200);
        Database.disconnect();
    }
}
