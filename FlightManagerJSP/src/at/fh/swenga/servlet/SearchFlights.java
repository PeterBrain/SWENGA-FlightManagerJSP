package at.fh.swenga.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class SearchFlights
 */
@WebServlet("/searchFlights")
public class SearchFlights extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFlights() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// get the searchstring from the form
		String searchString = request.getParameter("searchString");
		
		// get the http session object for the user
		HttpSession session = request.getSession(true);
 
		// FlightService in the session? if not, create a new FlightService and put it into the session
		FlightService flightService = (FlightService) session.getAttribute("flightService");		
		if (flightService == null) {
			flightService = new FlightService();
			session.setAttribute("flightService", flightService);
		}
		
		// Create a List of flights, where the aircraft, origin, destination or airline contains search string
		List<FlightModel> filteredFlights = flightService.getFilteredFlights(searchString);
		
		// Put it in the request, so index.jsp can show them
		request.setAttribute("flights", filteredFlights);
		
		RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
