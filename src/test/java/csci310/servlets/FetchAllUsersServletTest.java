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

public class FetchAllUsersServletTest {
    private FetchAllUsersServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Database db;

    @Before
    public void setUp() throws Exception {
        servlet = new FetchAllUsersServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        db = new Database();
    }

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoPost() throws IOException, ServletException {
		Database.connect("groupdate");
        db.addUser("testUser2", "", "", "");

		String username = "testUser2";
		
        // Response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        String responseOutput = stringWriter.toString();
        assertTrue(responseOutput.contains(username));
        // check that a list is returned
     	assertTrue(responseOutput.startsWith("["));
     	assertTrue(responseOutput.endsWith("]"));
   
     	db.removeUser("testUser2");
        Database.disconnect();
	}

}
