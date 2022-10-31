package csci310.servlets;

import static org.junit.Assert.*;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketmasterServletTest {
	private TicketmasterServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    
	@Before
	public void setUp() throws Exception {
		servlet = new TicketmasterServlet();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() throws IOException, ServletException {
		String json = "{\"classificationName\":\"music\",\"keyword\":\"\",\"startDateTime\":\"2021-10-02T15:00:00Z\",\"endDateTime\":\"\",\"city\":\"Los Angeles\",\"stateCode\":\"CA\"}";
		
		when(request.getInputStream()).thenReturn(
			    new DelegatingServletInputStream(
			        new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))));

		when(request.getReader()).thenReturn(
			    new BufferedReader(new StringReader(json)));
		
		when(request.getContentType()).thenReturn("application/json");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		servlet.doPost(request, response);
		
		writer.flush();
		String responseOutput = stringWriter.toString();

		// check that a list is returned
		assertTrue(responseOutput.startsWith("["));
		assertTrue(responseOutput.endsWith("]"));
	}

}
