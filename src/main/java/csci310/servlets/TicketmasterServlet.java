package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import csci310.EventDetails;
import csci310.TicketmasterAPI;
import csci310.SearchParams;

public class TicketmasterServlet extends HttpServlet{
	
	@Override
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		TicketmasterAPI ticketmasterAPI = new TicketmasterAPI();
		Map<String, String> hm = new HashMap<String, String>();
		SearchParams params = new Gson().fromJson(request.getReader(), SearchParams.class);

	    hm.put("classificationName", params.getClassificationName());
	    hm.put("keyword", params.getKeyword());
	    hm.put("startDateTime", params.getStartDateTime());
	    hm.put("endDateTime", params.getEndDateTime());
	    hm.put("city", params.getCity());
	    hm.put("stateCode", params.getStateCode());
	    ArrayList<EventDetails > events = ticketmasterAPI.searchEvents(hm);

	    params.setClassificationName("foo");
		params.setKeyword("bar");
		params.setStartDateTime("alice");
		params.setEndDateTime("bob");
		params.setCity("LA");
		params.setStateCode("CA");
	    
	    PrintWriter out = response.getWriter();
	    
	    String json = new Gson().toJson(events);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		out.print(json);
	}
}
