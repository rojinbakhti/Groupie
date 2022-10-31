package csci310.servlets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SettingsServletTest {
    private SettingsServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        servlet = new SettingsServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @After
    public void tearDown() throws Exception {


    }


    @Test
    public void testdoPost() throws IOException, ServletException {

        String username = "rojin";
        String user = "userN";
        String action = "show";


        // SHOW BLOCKLIST
        // Parameters
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("user1,user2,user3"));



        // ADD TO BLOCK LIST
        // Parameters
        action = "add";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("user1,user2,user3,userN"));


        // Remove from BLOCK LIST
        // Parameters
        action = "remove";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("user1,user2,user3"));

        // spam action from BLOCK LIST
        // Parameters
        action = "nothing";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        //assertTrue(stringWriter.toString().contains("user1,user2,user3"));


        // remove when user block list is empty
        // Parameters
        action = "remove";
        username = "rojin1";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        //assertTrue(stringWriter.toString().contains("empty"));


        // show when user block list is empty
        // Parameters
        action = "show";
        username = "rojin1";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("empty"));


        // Add when user block list is empty
        // Parameters
        action = "add";
        username = "rojin1";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("userN"));

        // Getting user1 back to its original state
        // Parameters
        action = "remove";
        username = "rojin1";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
       // assertTrue(stringWriter.toString().contains("userN"));


        // spam action from BLOCK LIST
        // Parameters
        action = "nothing";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        //assertTrue(stringWriter.toString().contains("user1,user2,user3"));
        username = (java.time.LocalDateTime.now()).toString().toString()+"notAlreadyinBlockListUser";
        user = "anewnotinblocklist";
        action = "add";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();

        //assertTrue(stringWriter.toString().contains("user1,user2,user3"));
        username = (java.time.LocalDateTime.now()).toString()+"notAlreadyinBlockListUser1";
        action = "remove";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();

        username = (java.time.LocalDateTime.now()).toString()+"notAlreadyinBlockListUser2";
        action = "show";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();

        username = (java.time.LocalDateTime.now()).toString()+"notAlreadyinBlockListUser3";
        action = "nothing";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(action);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
    }
}