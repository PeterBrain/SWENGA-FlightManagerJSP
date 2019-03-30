package at.fh.swenga.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.fh.swenga.model.FlightModel;
import at.fh.swenga.model.FlightService;

/**
 * Servlet implementation class ChangeFlight
 */
@WebServlet("/changeFlight")
public class ChangeFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// get the http session object for the user
		HttpSession session = request.getSession(true);
 
		// get the flightService out of the session
		FlightService flightService = (FlightService) session.getAttribute("flightService");
 
		// get all form data out of the request
		String flightIdString = request.getParameter("flightId");
		String aircraft = request.getParameter("aircraft");
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		String departureString = request.getParameter("departure");
		String arrivalString = request.getParameter("arrival");
		String numberOfPassengersString = request.getParameter("numberOfPassengers");
		String airline = request.getParameter("airline");
		String isCancelledString = request.getParameter("isCancelled");
 
		// provide a String to collect all error messages
		String errorMessage = "";
		boolean errorOccurred = false;
 
		// ---- Convert flightId ----
		// if flightId is not a number -> add an error text to the error message
		int flightId = 0;
		try {
			flightId = Integer.parseInt(flightIdString);
		} catch (Exception e) {
			errorMessage += "FlightID invalid<br>";
			errorOccurred = true;
		}
		
		// ---- Convert numberOfPassengers ----
		// if flightId is not a number -> add an error text to the error message
		int numberOfPassengers = 0;
		try {
			numberOfPassengers = Integer.parseInt(numberOfPassengersString);
		} catch (Exception e) {
			errorMessage += "Number of passengers invalid<br>";
			errorOccurred = true;
		}
		
		// ---- Convert isCancelled ----
		// if flightId is not a number -> add an error text to the error message
		boolean isCancelled = false;
		try {
			isCancelled = Boolean.parseBoolean(isCancelledString);
		} catch (Exception e) {
			errorMessage += "Cancelled invalid<br>";
			errorOccurred = true;
		}
 
		// ---- Convert departure -----
		// For date<->String conversion, Java provides the SimpleDateFormat class
		// if it is not a date-> add an error text to the error message
		Date departure = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			departure = sdf.parse(departureString);
		} catch (Exception e) {
			errorMessage += "Departure invalid<br>";
			errorOccurred = true;
		}
		
		// ---- Convert arrival -----
		// For date<->String conversion, Java provides the SimpleDateFormat class
		// if it is not a date-> add an error text to the error message
		Date arrival = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			arrival = sdf.parse(arrivalString);
		} catch (Exception e) {
			errorMessage += "Arrival invalid<br>";
			errorOccurred = true;
		}
 
		// Data Conversion ok? -> Change Flight
		if (!errorOccurred) { // same as: if (errorOccurred == false)
			FlightModel flight = flightService.getFlightByFlightId(flightId);
 
			if (flight == null) {
				errorMessage += "Flight doesn't exist!<br>";
				errorOccurred = true;
			} else { 
				// overwrite data in the existing flight object
				flight.setAircraft(aircraft);
				flight.setOrigin(origin);
				flight.setDestination(destination);
				flight.setDeparture(departure);
				flight.setArrival(arrival);
				flight.setNumberOfPassengers(numberOfPassengers);
				flight.setAirline(airline);
				flight.setCancelled(isCancelled);
			}
		}
 
		// --- Create a message for the JSP
 
		if (!errorOccurred) { // same as: if (errorOccurred == false)
			// No error? Then put a message on the page that an flight has changed
			request.setAttribute("message", "Flight " + flightId + " changed.");
		} else {
			// Errors happened? -> put all collected error messages in the request for the JSP
			request.setAttribute("errorMessage", errorMessage);
		}
 
		RequestDispatcher rd = request.getRequestDispatcher("./listFlights");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
