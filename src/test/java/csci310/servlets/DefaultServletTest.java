package csci310.servlets;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


public class DefaultServletTest {
	private DefaultServlet servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
	@Before
	public void setUp() throws Exception {
		servlet = new DefaultServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoPost() throws IOException, ServletException {
        servlet.doPost(request, response);
        assertEquals(200, response.getStatus());

	}
	
	@Test
	public void testDoGet() throws IOException, ServletException {
        servlet.doGet(request, response);
        assertEquals(200, response.getStatus());

	}

}
