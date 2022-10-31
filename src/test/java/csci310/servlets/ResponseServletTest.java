package csci310.servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.DelegatingServletInputStream;

import csci310.Database;

public class ResponseServletTest {
	private ResponseServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Database db;
    StringWriter stringWriter;
    PrintWriter writer;
    String responseOutput;

    @Before
    public void setUp() throws Exception {
        servlet = new ResponseServlet();
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
		// https://jsontostring.com/
		String json = "{\"action\":\"submit\",\"username\":\"dasol\",\"userId\":\"22\",\"events\":[{\"eventId\":\"3\",\"eventResponse\":\"yes\",\"excitement\":\"5\"}]}";
		
		when(request.getInputStream()).thenReturn(
			    new DelegatingServletInputStream(
			        new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))));

		when(request.getReader()).thenReturn(
			    new BufferedReader(new StringReader(json)));

        when(response.getWriter()).thenReturn(writer);
        when(response.getStatus()).thenReturn(200);
        servlet.doPost(request, response);
        writer.flush();
        responseOutput = stringWriter.toString();
        assertTrue(response.getStatus()==200);
        assertTrue(responseOutput.contains("{\"success\":\"true\"}"));
        db.removeUser("testUser2");



        json = "{\"action\":\"draft\",\"username\":\"dasol\",\"userId\":\"22\",\"events\":[{\"eventId\":\"3\",\"eventResponse\":\"yes\",\"excitement\":\"5\"}]}";

        when(request.getInputStream()).thenReturn(
                new DelegatingServletInputStream(
                        new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))));

        when(request.getReader()).thenReturn(
                new BufferedReader(new StringReader(json)));

        when(response.getWriter()).thenReturn(writer);
        when(response.getStatus()).thenReturn(200);
        servlet.doPost(request, response);
        writer.flush();
        responseOutput = stringWriter.toString();
        assertTrue(response.getStatus()==200);
        assertTrue(responseOutput.contains("{\"success\":\"true\"}"));
        db.removeUser("testUser2");


        json = "{\"action\":\"finalize\",\"username\":\"dasol\",\"userId\":\"22\",\"events\":[{\"eventId\":\"3\",\"eventResponse\":\"yes\",\"excitement\":\"5\"}]}";

        when(request.getInputStream()).thenReturn(
                new DelegatingServletInputStream(
                        new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))));

        when(request.getReader()).thenReturn(
                new BufferedReader(new StringReader(json)));

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
