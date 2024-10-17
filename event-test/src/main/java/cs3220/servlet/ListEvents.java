package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListEvents
 */
@WebServlet(urlPatterns = "/ListEvents", loadOnStartup = 1)
public class ListEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListEvents() {
        super();
    }

    // Initialize servlet with sample event data
    public void init() throws ServletException {
    	
    	// Create an empty list to hold Event objects
    	List<Event> events = new ArrayList<>();
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd, yyyy");
    	LocalDate date1 = LocalDate.parse("02 14, 2023", formatter);
    	LocalDate date2 = LocalDate.parse("06 15, 2023", formatter);
    	
    	events.add(new Event("Room 19 Valentine Party", date1, "Amy Frank"));
	    events.add(new Event("Room 19 Kindergarten Graduation", date2, "Amy Frank"));
    	
    

    	
    	
    	// Store the events list in application scope
    	getServletContext().setAttribute("events", events);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		@SuppressWarnings("unchecked")
		List<Event> events = (List<Event>) getServletContext().getAttribute("events");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.println("<html><head><title>List Events</title></head><body>");
		out.println("<h3>Events</h3>");
		out.println("<a href='AddEvent'>New Event <br></a>");
		
		// Add some simple table styling
		out.println("<style>");
		out.println("table { border-collapse: collapse; margin-top: 20px }");
		out.println("th, td { border: 1px solid black; padding: 8px;}");
		out.println("</style>");
		
		// Table header
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Event</th>");
		out.println("<th>Date</th>");
		out.println("<th>Created By</th>");
		out.println("</tr>");
		
		// Iterate over the events and display each in the table
        for (Event entry : events) {
            out.println("<tr>");
            out.println("<td>" + entry.getEName() + "</td>");
            out.println("<td>" + entry.getDate() + "</td>"); // getDate() returns formatted date
            out.println("<td>" + entry.getCreator() + "</td>");
            out.println("</tr>");
        }
        
        // Close the table and the HTML document
        out.println("</table>");
        out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
