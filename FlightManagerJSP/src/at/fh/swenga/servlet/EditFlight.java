package at.fh.swenga.servlet;

import java.io.IOException;

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
 * Servlet implementation class EditFlight
 */
@WebServlet("/editFlight")
public class EditFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFlight() {
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
		 
		int flightId = Integer.parseInt(flightIdString);
 
		// get the http session object for the user
		HttpSession session = request.getSession(true);
 
		// get the flightService out of the session
		FlightService flightService = (FlightService) session.getAttribute("flightService");
 
		if (flightService != null) {
			FlightModel flightModel = flightService.getFlightByFlightId(flightId);
			if (flightModel != null) {
				request.setAttribute("flight", flightModel);
				RequestDispatcher rd = request.getRequestDispatcher("./editFlight.jsp");
				rd.forward(request, response);
				return;
			}
		}
 
		request.setAttribute("errorMessage", "No flight with flightId " + flightId);
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
