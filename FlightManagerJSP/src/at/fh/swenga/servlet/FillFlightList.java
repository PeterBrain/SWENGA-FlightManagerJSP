package at.fh.swenga.servlet;

import java.io.IOException;
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
 * Servlet implementation class FillFlightList
 */
@WebServlet("/fillFlightList")
public class FillFlightList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillFlightList() {
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
		if (flightService == null) {
			flightService = new FlightService();
			session.setAttribute("flightService", flightService);
		}
		
		Date now = new Date();
		
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 737", "Deutschland", "Spanien", now, now, 100, "Lufthansa", false));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 767", "Deutschland", "Spanien", now, now, 100, "Swiss", true));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 777", "Deutschland", "Spanien", now, now, 100, "Eurowings", false));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Airbus A380", "Deutschland", "Spanien", now, now, 100, "Emirates", false));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 737", "Deutschland", "Spanien", now, now, 100, "Austrian Airlines", false));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 737", "Deutschland", "Spanien", now, now, 100, "Lauda Motion", true));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 737", "Deutschland", "Spanien", now, now, 100, "Default", false));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 737", "Deutschland", "Spanien", now, now, 100, "Default", false));
		
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
