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

public class GetEventsServletTest {
    private GetEventsServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        servlet = new GetEventsServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        // Variables
        String groupdate_id = "3";
        // Parameters
        when(request.getParameter("groupdate_id")).thenReturn(groupdate_id);
        // Response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"groupdate_id\":3,\"title\":\"newPGD\",\"status\":\"inviter_unfinalized\",\"inviter\":\"rojin\",\"date\":\"today\",\"events\":[{\"event_id\":3,\"info\":\"info\"}]}", stringWriter.toString());
    }
}