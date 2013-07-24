package airTraffic;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airTraffic.model.bean.FlightSegmentBean;
import airTraffic.model.dao.FlightSegmentDAOAirline;

/**
 * Servlet implementation class FlightSegmentServlet
 */
@WebServlet(name = "FlightSegmenServlet", description = "Existing flights + new flight", urlPatterns = { "/FlightSegmenServlet" })
public class FlightSegmentServletAirline extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlightSegmentDAOAirline dao = new FlightSegmentDAOAirline();
		//Get all flights
		List<FlightSegmentBean> allFlights;
		try {
			allFlights = dao.getAvailableFlights();
		} catch (SQLException e) {
			// Throw 500 ERROR if something goes wrong
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		//Add them as attributes to the request to make available in the JSP view
		request.setAttribute("allFlights", allFlights);
		//Set view
		RequestDispatcher view = request.getRequestDispatcher("flights.jsp");
		view.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		FlightSegmentDAOAirline flights = new FlightSegmentDAOAirline();
		FlightSegmentBean flight = new FlightSegmentBean();
		try {
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("flightDate"));
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			flight.setDate(sqlDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

		
		flight.setFlight_number(request.getParameter("flightNumber"));

		flight.setGate_nr(request.getParameter("gateNr"));

		try {
			flight.setBoarding_time(new Time (timeFormat.parse(request.getParameter("boardingTime")).getTime()));
		} catch (ParseException e) {
			// Throw 500 ERROR if something goes wrong
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						return;
		}

		try {
			flight.setDeparture_time(new Time (timeFormat.parse(request.getParameter("departureTime")).getTime()));
		} catch (ParseException e) {
			// Throw 500 ERROR if something goes wrong
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						return;
		}

		flight.setArrival_airport(request.getParameter("arrivalAirport"));
		flight.setDeparture_airport(request.getParameter("departureAirport"));
		flight.setOperated_by(request.getParameter("operatedBy"));
		flight.setFlightBy(Integer.parseInt(request.getParameter("flightBy")));
		
		try {
			flights.addNewFlight(flight);
		} catch (SQLException e) {
			// Throw 500 ERROR if something goes wrong
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
        response.sendRedirect("/AirTrafficProject/flights");
	}
}
