package csci310.servlets;

import org.junit.After;
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

public class ProposeServletTest {
    private ProposeServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        servlet = new ProposeServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testdoPost() throws IOException, ServletException {
        String json = "\n" +
                "{\n" +
                "    \"username\": \"testUsername\",\n" +
                "    \"title\": \"Tech\",\n" +
                "\t\t\"action\": \"draft\",\n" +
                "    \"date\": \"2021-12-01T01:48:39Z\",\n" +
                "    \"events\": [\n" +
                "        {\n" +
                "            \"name\": \"Phoenix Suns vs. Golden State Warriors\",\n" +
                "            \"url\": \"https://www.ticketmaster.com/phoenix-suns-vs-golden-state-warriors-phoenix-arizona-11-30-2021/event/19005B134ADD3FEB\",\n" +
                "            \"localDate\": \"2021-11-30\",\n" +
                "            \"localTime\": \"20:00:00\",\n" +
                "            \"venueName\": \"Footprint Center\",\n" +
                "            \"venueCity\": \"Phoenix\",\n" +
                "            \"venueState\": \"AZ\",\n" +
                "            \"venueCountry\": \"US\",\n" +
                "            \"venueAddress\": \"201 East Jefferson Street\",\n" +
                "            \"info\": \"Footprint Center is now a cashless environment. Please plan on using Visa, Mastercard, American Express or Discover during your visit. Or have your cash converted onto a PayPal preloaded Mastercard at the ticket counter at no charge. There is an 6 ticket limit. To purchase more than 6 tickets, please call 602.379.SUNS to find out about group tickets.\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"users\": [\n" +
                "        \"aravadani\"\n" +
                "    ]\n" +
                "}";
        when(request.getInputStream()).thenReturn(
                new DelegatingServletInputStream(
                        new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))));

        when(request.getReader()).thenReturn(
                new BufferedReader(new StringReader(json)));
        // Response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("success"));


        String json2 = "\n" +
                "{\n" +
                "    \"username\": \"testUsername\",\n" +
                "    \"title\": \"Tech\",\n" +
                "\t\t\"action\": \"propose\",\n" +
                "    \"date\": \"2021-12-01T01:48:39Z\",\n" +
                "    \"events\": [\n" +
                "        {\n" +
                "            \"name\": \"Phoenix Suns vs. Golden State Warriors\",\n" +
                "            \"url\": \"https://www.ticketmaster.com/phoenix-suns-vs-golden-state-warriors-phoenix-arizona-11-30-2021/event/19005B134ADD3FEB\",\n" +
                "            \"localDate\": \"2021-11-30\",\n" +
                "            \"localTime\": \"20:00:00\",\n" +
                "            \"venueName\": \"Footprint Center\",\n" +
                "            \"venueCity\": \"Phoenix\",\n" +
                "            \"venueState\": \"AZ\",\n" +
                "            \"venueCountry\": \"US\",\n" +
                "            \"venueAddress\": \"201 East Jefferson Street\",\n" +
                "            \"info\": \"Footprint Center is now a cashless environment. Please plan on using Visa, Mastercard, American Express or Discover during your visit. Or have your cash converted onto a PayPal preloaded Mastercard at the ticket counter at no charge. There is an 6 ticket limit. To purchase more than 6 tickets, please call 602.379.SUNS to find out about group tickets.\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"users\": [\n" +
                "        \"aravadani\"\n" +
                "    ]\n" +
                "}";
        when(request.getInputStream()).thenReturn(
                new DelegatingServletInputStream(
                        new ByteArrayInputStream(json2.getBytes(StandardCharsets.UTF_8))));

        when(request.getReader()).thenReturn(
                new BufferedReader(new StringReader(json2)));
        // Response
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        // Do POST
        servlet.doPost(request, response);
        // Output
        writer.flush();
        assertTrue(stringWriter.toString().contains("success"));

    }
}