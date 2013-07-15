package de.tum.labcourse.finalproject;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FlightSegmentServlet
 */
@WebServlet(name = "FlightSegmenServlet", description = "Existing flights + new flight", urlPatterns = { "/FlightSegmenServlet" })
public class FlightSegmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightSegmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlightSegmentDAO dao = new FlightSegmentDAO();
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
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
	    DateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		FlightSegmentDAO flights = new FlightSegmentDAO();
		FlightSegmentBean flight = new FlightSegmentBean();
		flight.setFlight_number(request.getParameter("flightNumber"));
		try {
			flight.setDate(dateFormat.parse(request.getParameter("flightDate")));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		flight.setGate_nr(request.getParameter("gateNr"));

		try {
			flight.setBoarding_time(timeFormat.parse(request.getParameter("boardingTime")));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		flight.setMaerketing_carrier_flight_numbers(request.getParameter("marketingFlightsNumber").split(", "));
		try {
			flight.setArriving_time(new Timestamp (timestampFormat.parse(request.getParameter("arrivingTime")).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			flight.setDeparture_time(new Timestamp (timestampFormat.parse(request.getParameter("departureTime")).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
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
