package csci310.servlets;

import csci310.Database;
import csci310.FinalizedPGD;
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

public class GetFinalizedPGDServletTest {
    private GetFinalizedPGDServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        servlet = new GetFinalizedPGDServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

}

    @Test
    public void testdoPost() throws IOException, ServletException {
        String groupdate_id = "8";
        Database db = new Database();
//        Database.connect("groupdate.db");
//        Database.disconnect();

        when(request.getParameter("groupdate_id")).thenReturn(groupdate_id);

        // Response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        String responseOutput = stringWriter.toString();
        System.out.println(responseOutput);
        assertTrue(responseOutput.contains("accepted_count"));

    }
}