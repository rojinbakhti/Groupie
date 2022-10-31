package csci310.servlets;

import csci310.Database;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.DelegatingServletInputStream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
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
public class addUserServletTest {
    private addUserServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Database db;
    StringWriter stringWriter;
    PrintWriter writer;
    String responseOutput;

    @Before
    public void setUp() throws Exception {
        servlet = new addUserServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        db = new Database();
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);

    }


    @Test
    public void testdoPost()throws ServletException, IOException {

        String json = "{\n" +
                "\n" +
                "\t\"username\": \"testUser2\",\n" +
                "\t\"user\": \"testUsername\",\n" +
                "\t\"events\": [{\n" +
                "\t\t\t\"name\": \"BTS PERMISSION TO DANCE ON STAGE - LIVE PLAY\",\n" +
                "\t\t\t\"url\": \"https://www.ticketmaster.com/bts-permission-to-dance-on-stage-inglewood-california-12-02-2021/event/0A005B5200C1666D\",\n" +
                "\t\t\t\"localDate\": \"2021-11-29\",\n" +
                "\t\t\t\"localTime\": \"10:30:00\",\n" +
                "\t\t\t\"venueName\": \"YouTube Theater\",\n" +
                "\t\t\t\"venueCity\": \"Inglewood\",\n" +
                "\t\t\t\"venueState\": \"CA\",\n" +
                "\t\t\t\"venueCountry\": \"US\",\n" +
                "\t\t\t\"venueAddress\": \"1011 S. Stadium Dr\",\n" +
                "\t\t\t\"info\": \"This is a Live Broadcast of the BTS concert at SoFi Stadium Please Note: In accordance with California's and the venue's current guidelines, fans agree to the terms below prior to ticket purchases & at event entry: By purchasing tickets for this event\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"BTS PERMISSION TO DANCE ON STAGE - LIVE PLAY\",\n" +
                "\t\t\t\"url\": \"https://www.ticketmaster.com/bts-permission-to-dance-on-stage-inglewood-california-12-02-2021/event/0A005B5200C1666D\",\n" +
                "\t\t\t\"localDate\": \"2021-11-29\",\n" +
                "\t\t\t\"localTime\": \"11:30:00\",\n" +
                "\t\t\t\"venueName\": \"YouTube Theater\",\n" +
                "\t\t\t\"venueCity\": \"Inglewood\",\n" +
                "\t\t\t\"venueState\": \"CA\",\n" +
                "\t\t\t\"venueCountry\": \"US\",\n" +
                "\t\t\t\"venueAddress\": \"1011 S. Stadium Dr\",\n" +
                "\t\t\t\"info\": \"This is a Live Broadcast of the BTS concert at SoFi Stadium Please Note: In accordance with California's and the venue's current guidelines, fans agree to the terms below prior to ticket purchases & at event entry: By purchasing tickets for this event\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "\n" +
                "}";
        Database.connect("groupdate");
        db.addUser("testUser2", "", "", "");
        // https://jsontostring.com/

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
        //assertTrue(response.getStatus()==200);
        String ans = responseOutput.toString();
        assertTrue(ans.contains("true"));

        String json2 = "{\n" +
                "\n" +
                "\t\"username\": \"testUser2\",\n" +
                "\t\"user\": \"newSignUpUser\",\n" +
                "\t\"events\": [{\n" +
                "\t\t\t\"name\": \"BTS PERMISSION TO DANCE ON STAGE - LIVE PLAY\",\n" +
                "\t\t\t\"url\": \"https://www.ticketmaster.com/bts-permission-to-dance-on-stage-inglewood-california-12-02-2021/event/0A005B5200C1666D\",\n" +
                "\t\t\t\"localDate\": \"2021-11-29\",\n" +
                "\t\t\t\"localTime\": \"10:30:00\",\n" +
                "\t\t\t\"venueName\": \"YouTube Theater\",\n" +
                "\t\t\t\"venueCity\": \"Inglewood\",\n" +
                "\t\t\t\"venueState\": \"CA\",\n" +
                "\t\t\t\"venueCountry\": \"US\",\n" +
                "\t\t\t\"venueAddress\": \"1011 S. Stadium Dr\",\n" +
                "\t\t\t\"info\": \"This is a Live Broadcast of the BTS concert at SoFi Stadium Please Note: In accordance with California's and the venue's current guidelines, fans agree to the terms below prior to ticket purchases & at event entry: By purchasing tickets for this event\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"BTS PERMISSION TO DANCE ON STAGE - LIVE PLAY\",\n" +
                "\t\t\t\"url\": \"https://www.ticketmaster.com/bts-permission-to-dance-on-stage-inglewood-california-12-02-2021/event/0A005B5200C1666D\",\n" +
                "\t\t\t\"localDate\": \"2021-11-29\",\n" +
                "\t\t\t\"localTime\": \"11:30:00\",\n" +
                "\t\t\t\"venueName\": \"YouTube Theater\",\n" +
                "\t\t\t\"venueCity\": \"Inglewood\",\n" +
                "\t\t\t\"venueState\": \"CA\",\n" +
                "\t\t\t\"venueCountry\": \"US\",\n" +
                "\t\t\t\"venueAddress\": \"1011 S. Stadium Dr\",\n" +
                "\t\t\t\"info\": \"This is a Live Broadcast of the BTS concert at SoFi Stadium Please Note: In accordance with California's and the venue's current guidelines, fans agree to the terms below prior to ticket purchases & at event entry: By purchasing tickets for this event\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "\n" +
                "}";
        when(request.getInputStream()).thenReturn(
                new DelegatingServletInputStream(
                        new ByteArrayInputStream(json2.getBytes(StandardCharsets.UTF_8))));

        when(request.getReader()).thenReturn(
                new BufferedReader(new StringReader(json2)));

        when(response.getWriter()).thenReturn(writer);
        when(response.getStatus()).thenReturn(200);
        servlet.doPost(request, response);
        writer.flush();
        responseOutput = stringWriter.toString();
        //assertTrue(response.getStatus()==200);
        ans = responseOutput.toString();
        assertTrue(ans.contains("false"));
        //Database.disconnect();


        String json3 = "{\n" +
                "\n" +
                "\t\"username\": \"testUser2\",\n" +
                "\t\"user\": \"newSignUpUser\",\n" +
                "\t\"events\": [{\n" +
                "\t\t\t\"name\": \"BTS PERMISSION TO DANCE ON STAGE - LIVE PLAY\",\n" +
                "\t\t\t\"url\": \"https://www.ticketmaster.com/bts-permission-to-dance-on-stage-inglewood-california-12-02-2021/event/0A005B5200C1666D\",\n" +
                "\t\t\t\"localDate\": \"2021-11-29\",\n" +
                "\t\t\t\"localTime\": \"20:30:00\",\n" +
                "\t\t\t\"venueName\": \"YouTube Theater\",\n" +
                "\t\t\t\"venueCity\": \"Inglewood\",\n" +
                "\t\t\t\"venueState\": \"CA\",\n" +
                "\t\t\t\"venueCountry\": \"US\",\n" +
                "\t\t\t\"venueAddress\": \"1011 S. Stadium Dr\",\n" +
                "\t\t\t\"info\": \"This is a Live Broadcast of the BTS concert at SoFi Stadium Please Note: In accordance with California's and the venue's current guidelines, fans agree to the terms below prior to ticket purchases & at event entry: By purchasing tickets for this event\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"BTS PERMISSION TO DANCE ON STAGE - LIVE PLAY\",\n" +
                "\t\t\t\"url\": \"https://www.ticketmaster.com/bts-permission-to-dance-on-stage-inglewood-california-12-02-2021/event/0A005B5200C1666D\",\n" +
                "\t\t\t\"localDate\": \"2021-11-29\",\n" +
                "\t\t\t\"localTime\": \"11:30:00\",\n" +
                "\t\t\t\"venueName\": \"YouTube Theater\",\n" +
                "\t\t\t\"venueCity\": \"Inglewood\",\n" +
                "\t\t\t\"venueState\": \"CA\",\n" +
                "\t\t\t\"venueCountry\": \"US\",\n" +
                "\t\t\t\"venueAddress\": \"1011 S. Stadium Dr\",\n" +
                "\t\t\t\"info\": \"This is a Live Broadcast of the BTS concert at SoFi Stadium Please Note: In accordance with California's and the venue's current guidelines, fans agree to the terms below prior to ticket purchases & at event entry: By purchasing tickets for this event\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "\n" +
                "}";
        when(request.getInputStream()).thenReturn(
                new DelegatingServletInputStream(
                        new ByteArrayInputStream(json3.getBytes(StandardCharsets.UTF_8))));

        when(request.getReader()).thenReturn(
                new BufferedReader(new StringReader(json3)));

        when(response.getWriter()).thenReturn(writer);
        when(response.getStatus()).thenReturn(200);
        servlet.doPost(request, response);
        writer.flush();
        responseOutput = stringWriter.toString();
        //assertTrue(response.getStatus()==200);
        ans = responseOutput.toString();
        assertTrue(ans.contains("false"));

        json3 = "{\n" +
                "\n" +
                "\t\"username\": \"testUser2222\",\n" +
                "\t\"user\": \"newSignUpUser\",\n" +
                "\t\"events\": [{\n" +
                "\t\t\t\"name\": \"BTS PERMISSION TO DANCE ON STAGE - LIVE PLAY\",\n" +
                "\t\t\t\"url\": \"https://www.ticketmaster.com/bts-permission-to-dance-on-stage-inglewood-california-12-02-2021/event/0A005B5200C1666D\",\n" +
                "\t\t\t\"localDate\": \"2021-11-29\",\n" +
                "\t\t\t\"localTime\": \"20:30:00\",\n" +
                "\t\t\t\"venueName\": \"YouTube Theater\",\n" +
                "\t\t\t\"venueCity\": \"Inglewood\",\n" +
                "\t\t\t\"venueState\": \"CA\",\n" +
                "\t\t\t\"venueCountry\": \"US\",\n" +
                "\t\t\t\"venueAddress\": \"1011 S. Stadium Dr\",\n" +
                "\t\t\t\"info\": \"This is a Live Broadcast of the BTS concert at SoFi Stadium Please Note: In accordance with California's and the venue's current guidelines, fans agree to the terms below prior to ticket purchases & at event entry: By purchasing tickets for this event\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"BTS PERMISSION TO DANCE ON STAGE - LIVE PLAY\",\n" +
                "\t\t\t\"url\": \"https://www.ticketmaster.com/bts-permission-to-dance-on-stage-inglewood-california-12-02-2021/event/0A005B5200C1666D\",\n" +
                "\t\t\t\"localDate\": \"2021-11-29\",\n" +
                "\t\t\t\"localTime\": \"11:30:00\",\n" +
                "\t\t\t\"venueName\": \"YouTube Theater\",\n" +
                "\t\t\t\"venueCity\": \"Inglewood\",\n" +
                "\t\t\t\"venueState\": \"CA\",\n" +
                "\t\t\t\"venueCountry\": \"US\",\n" +
                "\t\t\t\"venueAddress\": \"1011 S. Stadium Dr\",\n" +
                "\t\t\t\"info\": \"This is a Live Broadcast of the BTS concert at SoFi Stadium Please Note: In accordance with California's and the venue's current guidelines, fans agree to the terms below prior to ticket purchases & at event entry: By purchasing tickets for this event\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "\n" +
                "}";
        when(request.getInputStream()).thenReturn(
                new DelegatingServletInputStream(
                        new ByteArrayInputStream(json3.getBytes(StandardCharsets.UTF_8))));

        when(request.getReader()).thenReturn(
                new BufferedReader(new StringReader(json3)));

        when(response.getWriter()).thenReturn(writer);
        when(response.getStatus()).thenReturn(200);
        servlet.doPost(request, response);
        writer.flush();
        responseOutput = stringWriter.toString();
        //assertTrue(response.getStatus()==200);
        ans = responseOutput.toString();
        assertTrue(ans.contains("false"));

        json3 = "{\n" +
                "\n" +
                "\t\"username\": \"busyUser\",\n" +
                "\t\"user\": \"newSignUpUser\",\n" +
                "\t\"events\": [{\n" +
                "\t\t\t\"name\": \"BTS PERMISSION TO DANCE ON STAGE - LIVE PLAY\",\n" +
                "\t\t\t\"url\": \"https://www.ticketmaster.com/bts-permission-to-dance-on-stage-inglewood-california-12-02-2021/event/0A005B5200C1666D\",\n" +
                "\t\t\t\"localDate\": \"2021-01-01\",\n" +
                "\t\t\t\"localTime\": \"18:30:00\",\n" +
                "\t\t\t\"venueName\": \"YouTube Theater\",\n" +
                "\t\t\t\"venueCity\": \"Inglewood\",\n" +
                "\t\t\t\"venueState\": \"CA\",\n" +
                "\t\t\t\"venueCountry\": \"US\",\n" +
                "\t\t\t\"venueAddress\": \"1011 S. Stadium Dr\",\n" +
                "\t\t\t\"info\": \"This is a Live Broadcast of the BTS concert at SoFi Stadium Please Note: In accordance with California's and the venue's current guidelines, fans agree to the terms below prior to ticket purchases & at event entry: By purchasing tickets for this event\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"BTS PERMISSION TO DANCE ON STAGE - LIVE PLAY\",\n" +
                "\t\t\t\"url\": \"https://www.ticketmaster.com/bts-permission-to-dance-on-stage-inglewood-california-12-02-2021/event/0A005B5200C1666D\",\n" +
                "\t\t\t\"localDate\": \"2021-11-29\",\n" +
                "\t\t\t\"localTime\": \"11:30:00\",\n" +
                "\t\t\t\"venueName\": \"YouTube Theater\",\n" +
                "\t\t\t\"venueCity\": \"Inglewood\",\n" +
                "\t\t\t\"venueState\": \"CA\",\n" +
                "\t\t\t\"venueCountry\": \"US\",\n" +
                "\t\t\t\"venueAddress\": \"1011 S. Stadium Dr\",\n" +
                "\t\t\t\"info\": \"This is a Live Broadcast of the BTS concert at SoFi Stadium Please Note: In accordance with California's and the venue's current guidelines, fans agree to the terms below prior to ticket purchases & at event entry: By purchasing tickets for this event\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "\n" +
                "}";
        when(request.getInputStream()).thenReturn(
                new DelegatingServletInputStream(
                        new ByteArrayInputStream(json3.getBytes(StandardCharsets.UTF_8))));

        when(request.getReader()).thenReturn(
                new BufferedReader(new StringReader(json3)));

        when(response.getWriter()).thenReturn(writer);
        when(response.getStatus()).thenReturn(200);
        servlet.doPost(request, response);
        writer.flush();
        responseOutput = stringWriter.toString();
        //assertTrue(response.getStatus()==200);
        ans = responseOutput.toString();
        assertTrue(ans.contains("false"));
        Database.disconnect();

    }
}