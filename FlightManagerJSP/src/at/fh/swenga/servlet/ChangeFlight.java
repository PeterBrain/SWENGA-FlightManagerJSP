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
		
		HttpSession session = request.getSession(true);
 
		FlightService flightService = (FlightService) session.getAttribute("flightService");
 
		String flightIdString = request.getParameter("flightId");
		String aircraft = request.getParameter("aircraft");
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		String departureString = request.getParameter("departure");
		String arrivalString = request.getParameter("arrival");
		String numberOfPassengersString = request.getParameter("numberOfPassengers");
		String airline = request.getParameter("airline");
		String isCancelledString = request.getParameter("isCancelled");
 
		String errorMessage = "";
		boolean errorOccurred = false;
 
		// convert flightId
		int flightId = 0;
		try {
			flightId = Integer.parseInt(flightIdString);
		} catch (Exception e) {
			errorMessage += "FlightID invalid<br>";
			errorOccurred = true;
		}
		
		// convert numberOfPassengers
		int numberOfPassengers = 0;
		try {
			numberOfPassengers = Integer.parseInt(numberOfPassengersString);
		} catch (Exception e) {
			errorMessage += "Number of passengers invalid<br>";
			errorOccurred = true;
		}
 
		// convert departure
		Date departure = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			departure = sdf.parse(departureString);
		} catch (Exception e) {
			errorMessage += "Departure invalid<br>";
			errorOccurred = true;
		}
		
		// convert arrival
		Date arrival = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			arrival = sdf.parse(arrivalString);
		} catch (Exception e) {
			errorMessage += "Arrival invalid<br>";
			errorOccurred = true;
		}
		
		// convert isCancelled
		boolean isCancelled = false;
		try {
			isCancelled = Boolean.parseBoolean(isCancelledString);
		} catch (Exception e) {
			errorMessage += "Cancelled invalid<br>";
			errorOccurred = true;
		}
 
		// data conversion successful
		if (!errorOccurred) {
			FlightModel flight = flightService.getFlightByFlightId(flightId);
 
			if (flight == null) {
				errorMessage += "Flight doesn't exist!<br>";
				errorOccurred = true;
			} else {
				if (departure.compareTo(arrival) <= 0) {
					flight.setAircraft(aircraft);
					flight.setOrigin(origin);
					flight.setDestination(destination);
					flight.setDeparture(departure);
					flight.setArrival(arrival);
					flight.setNumberOfPassengers(numberOfPassengers);
					flight.setAirline(airline);
					flight.setCancelled(isCancelled);
				} else {
					errorMessage += "Arrival before departure? - You wish, that's not possible!<br>";
					errorOccurred = true;
				}
			}
		}
 
		// create a message for the JSP
		if (!errorOccurred) {
			request.setAttribute("message", "Flight " + flightId + " changed.");
		} else {
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
