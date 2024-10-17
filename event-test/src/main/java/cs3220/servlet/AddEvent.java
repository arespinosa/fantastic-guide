package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddEvent
 */
@WebServlet("/AddEvent")
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEvent() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<html><head><title>Add Event</title></head>");
		out.print("<body>");
		
		out.print("<h3>New Event</h3>");
		
		// Form for adding a new event
	    out.println("<form action='AddEvent' method='post'>");  // Points to AddEvent for POST processing
	    out.println("Name: <input type='text' name='name' required><br>");
	    out.println("Date: <input name='date' type='date' required><br>");  // HTML5 date input
	    out.println("Creator: <input type='text' name='creator' required><br>");
	    out.println("<button type='submit'>Add</button>");
	    out.println("</form>");
        
		out.print("</body>");
		out.print("</html>");
	}

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from form submission
        String name = request.getParameter("name");
        String DateS = request.getParameter("date"); // Date in yyyy-MM-dd format
        String creator = request.getParameter("creator");

        // Create an empty Event object
        Event event = null;
        	
        // try catch block in order to parse dateInput 
        try {
            // Parse the date input as a LocalDate
            LocalDate date = LocalDate.parse(DateS);

            // Create a new Event with parsed LocalDate and other inputs
            event = new Event(name, date, creator);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return;
        }

        // Retrieve the list of events from the application scope
        List <Event> events = (List<Event>) getServletContext()
        		.getAttribute( "events" );

        // Add the newly created event to the list
        events.add(event);

        // Redirect to ListEvents after successful addition
        response.sendRedirect("ListEvents");
    }
}
