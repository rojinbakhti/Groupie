package csci310.servlets;

import csci310.Database;
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

public class FinalizeServletTest {
    private FinalizeServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Database db;
    StringWriter stringWriter;
    PrintWriter writer;
    String responseOutput;

    @Before
    public void setUp() throws Exception {
        servlet = new FinalizeServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        db = new Database();

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);

    }

    @Test
    public void testdoPost() throws IOException, ServletException {
        String groupdate_id = "5";
        String decision = "finalize";
        String event_id = "6";
        String username = "proposeServletUser";

        when(request.getParameter("groupdate_id")).thenReturn(groupdate_id.toString());
        when(request.getParameter("event_id")).thenReturn(event_id);
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("decision")).thenReturn(decision);


        // Response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        String responseOutput = stringWriter.toString();
        assertEquals(responseOutput, "event_finalized");

        decision = "unfinalize";
        when(request.getParameter("groupdate_id")).thenReturn(groupdate_id.toString());
        when(request.getParameter("event_id")).thenReturn(event_id);
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("decision")).thenReturn(decision);


        // Response
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        responseOutput = stringWriter.toString();
        //assertEquals(responseOutput, "event_finalized");

    }
}