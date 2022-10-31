package csci310.servlets;

import csci310.Database;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.DelegatingServletInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class getUsersResponseServletTest {
    private getUsersResponseServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Database db;
    StringWriter stringWriter;
    PrintWriter writer;
    String responseOutput;

    @Before
    public void setUp() throws Exception {
        servlet = new getUsersResponseServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        db = new Database();

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    @Test
    public void testdoPost() throws IOException, ServletException {
        String event_id = "87";

        when(request.getParameter("event_id")).thenReturn(event_id.toString());

        // Response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        String responseOutput = stringWriter.toString();
        assertTrue(responseOutput.startsWith("["));
        assertTrue(responseOutput.endsWith("]"));
    }
}