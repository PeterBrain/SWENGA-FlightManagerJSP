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

		HttpSession session = request.getSession(true);
		
		FlightService flightService = (FlightService) session.getAttribute("flightService");
		if (flightService == null) {
			flightService = new FlightService();
			session.setAttribute("flightService", flightService);
		}
		
		Date now = new Date();
		
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 737", "Germany", "Spanien", now, now, 148, "Lufthansa", false));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 767", "Austria", "Belgium", now, now, 72, "Austrian", true));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 777", "France", "Portugal", now, now, 65, "Eurowings", false));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Airbus A380", "Germany", "Thailand", now, now, 225, "Emirates", false));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 767", "Switzerland", "Germany", now, now, 103, "Swiss", false));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Airbus A320", "Spain", "Portugal", now, now, 62, "Iberia", true));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 747", "France", "Sweden", now, now, 98, "Norwegian", false));
		flightService.addFlight(new FlightModel(flightService.getSize() + 1, "Boeing 737", "Sweden", "Spain", now, now, 46, "Iberia", false));
		
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
