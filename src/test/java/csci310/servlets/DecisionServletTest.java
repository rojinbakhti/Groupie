package csci310.servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import csci310.Database;

public class DecisionServletTest {
	private DecisionServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Database db;
    StringWriter stringWriter;
    PrintWriter writer;
    String responseOutput;

    @Before
    public void setUp() throws Exception {
        servlet = new DecisionServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        db = new Database();
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testDoPost() throws ServletException, IOException {
        Database.connect("groupdate");
        db.addUser("testUser2", "", "", "");
		String accept = "accept";
		String decline = "decline";
        String invitee_username = "invitee_user";
		// accept
        when(request.getParameter("username")).thenReturn(invitee_username);
        when(request.getParameter("decision")).thenReturn(accept);
        when(request.getParameter("groupdate_id")).thenReturn("35");

        when(response.getWriter()).thenReturn(writer);
        when(response.getStatus()).thenReturn(200);
        servlet.doPost(request, response);
        writer.flush();
        responseOutput = stringWriter.toString();
        assertTrue(response.getStatus()==200);
        assertTrue(responseOutput.contains("{\"success\":\"true\"}"));
     	
        // decline
        when(request.getParameter("username")).thenReturn(invitee_username);
        when(request.getParameter("groupdate_id")).thenReturn("35");
        when(request.getParameter("decision")).thenReturn(decline);
        when(response.getWriter()).thenReturn(writer);
        when(response.getStatus()).thenReturn(200);
        servlet.doPost(request, response);
        writer.flush();
        responseOutput = stringWriter.toString();
        assertTrue(response.getStatus()==200);
        assertTrue(responseOutput.contains("{\"success\":\"true\"}"));
        db.removeUser("testUser2");
        Database.disconnect();
	}

}
