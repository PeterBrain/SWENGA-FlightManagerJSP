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
 * Servlet implementation class SaveNewFlight
 */
@WebServlet("/saveNewFlight")
public class SaveNewFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNewFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String flightIdString = request.getParameter("flightId");
		String aircraft = request.getParameter("aircraft");
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		String departureString = request.getParameter("departure");
		String arrivalString = request.getParameter("arrival");
		String numberOfPassengersString = request.getParameter("numberOfPassengers");
		/*String duration = request.getParameter("duration");*/
		String airline = request.getParameter("airline");
		String isCancelledString = request.getParameter("isCancelled");
		
		String errorMessage = "";
		boolean errorOccurred = false;
		
		//---- Convert flightId ----
		int flightId = 0;
		try {
			flightId = Integer.parseInt(flightIdString);
		} catch (Exception e) {
			errorMessage += "FlightID invalid<br>";
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
		
		//---- Convert numberOfPassengers ----
		int numberOfPassengers = 0;
		try {
			numberOfPassengers = Integer.parseInt(numberOfPassengersString);
		} catch (Exception e) {
			errorMessage += "Number of passengers invalid<br>";
			errorOccurred = true;
		}
		
		//---- Convert isCancelled ----
		boolean isCancelled = false;
		try {
			isCancelled = Boolean.parseBoolean(isCancelledString);
		} catch (Exception e) {
			errorMessage += "Cancelled invalid<br>";
			errorOccurred = true;
		}
		
		if (!errorOccurred) { // same as: if (errorOccurred == false)
			 
			// get the http session object for the user
			HttpSession session = request.getSession(true);
 
			// FlightService in the session? if not, create a new FlightService and put it into the session
			FlightService flightService = (FlightService) session.getAttribute("flightService");
			if (flightService == null) {
				flightService = new FlightService();
				session.setAttribute("flightService", flightService);
			}
 
			FlightModel flight = flightService.getFlightByFlightId(flightId);
 
			if (flight != null) {
				errorMessage += "Flight already exists!<br>";
				errorOccurred = true;
			} else {
				FlightModel em = new FlightModel(flightId, aircraft, origin, destination, departure, arrival, numberOfPassengers, airline, isCancelled);
				flightService.addFlight(em);
			}
		}
		
		if (!errorOccurred) { // same as: if (errorOccurred == false)
			request.setAttribute("message", "New flight " + flightId + " added.");
		}
		else {
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
