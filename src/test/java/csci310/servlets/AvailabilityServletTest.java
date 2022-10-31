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
import static org.mockito.Mockito.*;

public class AvailabilityServletTest {
    private AvailabilityServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        servlet = new AvailabilityServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        // Variables
        String username = "rojinbhakti";
        String endTime = "12:30";
        String startTime = "12:00";
        String event = "available";
        String addSlot = "true";

        // SLOT ADDDED TEST
        // Parameters
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("startTime")).thenReturn(startTime);
        when(request.getParameter("endTime")).thenReturn(endTime);
        when(request.getParameter("event")).thenReturn(event);
        when(request.getParameter("addSlot")).thenReturn(addSlot);
        // Response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("slotAdded"));

        // SLOT ADDED NULL TEST
        // Parameters
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("startTime")).thenReturn(startTime);
        when(request.getParameter("endTime")).thenReturn(endTime);
        when(request.getParameter("event")).thenReturn(event);
        when(request.getParameter("addSlot")).thenReturn(null);
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("noAction"));
        // SLOTADDED String but not true test
        // Parameters
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("startTime")).thenReturn(startTime);
        when(request.getParameter("endTime")).thenReturn(endTime);
        when(request.getParameter("event")).thenReturn(event);
        when(request.getParameter("addSlot")).thenReturn("null");
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("noAction"));

        // Parameters
        HttpServletRequest request2 = mock(HttpServletRequest.class);
        when(request2.getParameter("addSlot")).thenReturn(null);
        when(request2.getParameter("username")).thenReturn(username);
        when(request2.getParameter("startTime")).thenReturn(startTime);
        when(request2.getParameter("endTime")).thenReturn(endTime);
        when(request2.getParameter("event")).thenReturn(event);
        when(request2.getParameter("removeSlot")).thenReturn(null);
        servlet.doPost(request2, response);

        when(request2.getParameter("addSlot")).thenReturn(null);
        when(request2.getParameter("username")).thenReturn(username);
        when(request2.getParameter("startTime")).thenReturn(startTime);
        when(request2.getParameter("endTime")).thenReturn(endTime);
        when(request2.getParameter("event")).thenReturn(event);
        when(request2.getParameter("removeSlot")).thenReturn("null");
        servlet.doPost(request2, response);

        when(request2.getParameter("addSlot")).thenReturn(null);
        when(request2.getParameter("username")).thenReturn(username);
        when(request2.getParameter("startTime")).thenReturn(startTime);
        when(request2.getParameter("endTime")).thenReturn(endTime);
        when(request2.getParameter("event")).thenReturn(event);
        when(request2.getParameter("removeSlot")).thenReturn("true");
        // Response
        StringWriter stringWriter2 = new StringWriter();
        PrintWriter writer2 = new PrintWriter(stringWriter2);
        when(response.getWriter()).thenReturn(writer2);
        // Do POST
        servlet.doPost(request2, response);
        // Output
        writer2.flush();
        System.out.println("availabilityServlet:"+stringWriter2.toString());
        assertTrue(stringWriter2.toString().contains("slotRemoved"));

        // SLOT ADDED for a user with no availability
        // Parameters
        username = "dasollee";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("startTime")).thenReturn(startTime);
        when(request.getParameter("endTime")).thenReturn(endTime);
        when(request.getParameter("event")).thenReturn(event);
        when(request.getParameter("addSlot")).thenReturn("true");
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("slotAdded"));


        username = "busyUser";
        when(request.getParameter("show")).thenReturn("true");
        when(request.getParameter("username")).thenReturn("busyUser");
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();


        username = "busyUser";
        when(request.getParameter("show")).thenReturn("faaalse");
        when(request.getParameter("username")).thenReturn("busyUser");
        // Response
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();

        //assertTrue(stringWriter.toString().contains("slotAdded"));
    }
}