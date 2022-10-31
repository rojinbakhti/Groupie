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

public class GetGroupdatesServletTest {
    private GetGroupdatesServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        servlet = new GetGroupdatesServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        // Variables
        String username = "dasollee";
        String role = "invitee";
        // Parameters
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("role")).thenReturn(role);
        // Response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        System.out.println(stringWriter.toString());
        assertEquals("[{\"groupdate_id\":20,\"title\":\"Test1\",\"status\":\"inviter_unfinalized\",\"inviter\":\"aravadani\",\"date\":\"0\"},{\"groupdate_id\":21,\"title\":\"Test2\",\"status\":\"inviter_unfinalized\",\"inviter\":\"rojinbhakti\",\"date\":\"0\"}]", stringWriter.toString());
    }
}