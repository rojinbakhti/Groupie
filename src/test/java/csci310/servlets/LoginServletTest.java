package csci310.servlets;

import csci310.Database;
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
import static org.mockito.Mockito.verify;

public class LoginServletTest {
    private LoginServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Database db;

    @Before
    public void setUp() throws Exception {
        servlet = new LoginServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        db = new Database();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDoPostHttpServletRequestHttpServletResponse() throws IOException, ServletException {
        // Variables
        String username = "testUser";
        String password = "password";
        // Call failed
        servlet.doPost(request, response);
        verify(response).setStatus(401);
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login Failed");
        // Add user
        Database.connect("groupdate");
        db.addUser("testUser", "X03MO1qnZdYdgyfeuILPmQ==", "", "");
        Database.disconnect();
        // Parameters
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        // Response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains(username));
        verify(response).setStatus(200);
        // Remove user
        Database.connect("groupdate");
        db.removeUser("testUser");
        Database.disconnect();
    }
}