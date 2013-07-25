package airTraffic;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import airTraffic.model.bean.AirplaneBean;
import airTraffic.model.bean.FlightSegmentBean;
import airTraffic.model.bean.JourneyBean;
import airTraffic.model.bean.PassengerBean;
import airTraffic.model.dao.AirplaneDAO;
import airTraffic.model.dao.FlightSegmentDAO;
import airTraffic.model.dao.FlightTicketDAO;

/**
 * Servlet implementation class FlightSegmentServlet
 */
@WebServlet("/FlightSegmentServlet")
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
		try{
			//get session
			HttpSession session = request.getSession();
			LinkedList<FlightSegmentBean> flightSegments = (LinkedList<FlightSegmentBean>)session.getAttribute("foundFlights");
			JourneyBean journey = (JourneyBean)session.getAttribute("journey");
			PassengerBean passenger = (PassengerBean)session.getAttribute("passenger");
			//parse parameters
			FlightSegmentBean flight = new FlightSegmentBean();
			String flightNumber = request.getParameter("number");
			for(Iterator<FlightSegmentBean> iter = flightSegments.iterator(); iter.hasNext();){
				FlightSegmentBean f = iter.next();
				if(f.getFlight_number().equals(flightNumber)){
					flight = f;
					break;
				}
			}
			String flightClass = request.getParameter("class");
			//insert flightTicket
			FlightTicketDAO fDao = new FlightTicketDAO();
			double price = fDao.insertFlightTicket(flight, passenger.getPassportId(), flightClass, journey);
			//update journey
			journey.setTotalPrice(journey.getTotalPrice() + price);
			session.setAttribute("journey", journey);
			//success
			request.setAttribute("success", true);
			//add flightSegment to session
			LinkedList<FlightSegmentBean> bookedFlights = (LinkedList<FlightSegmentBean>)session.getAttribute("flightSegments");
			bookedFlights.add(flight);
			session.setAttribute("flightSegments", bookedFlights);
			//dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher("/FlightSegment.jsp");
			dispatcher.forward(request, response);
		} catch(Throwable e){
			request.setAttribute("error", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/FlightSegment.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			//get inputs
			java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("date"));
			Date date = new Date(utilDate.getTime());
			String departure = request.getParameter("departure");
			String destination = request.getParameter("destination");
			//validate inputs
			LinkedList<FlightSegmentBean> flights = (LinkedList<FlightSegmentBean>)session.getAttribute("flightSegments");
			if(!flights.isEmpty() && (!departure.equals(flights.getLast().getArrival_airport()) || date.getTime() < flights.getLast().getDate().getTime())){
				request.setAttribute("teleport", true);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/FlightSegment.jsp");
				dispatcher.forward(request, response);
			} else {
			//get flightSegments according to inputs
			FlightSegmentDAO fDao = new FlightSegmentDAO();
			LinkedList<FlightSegmentBean> foundFlights = fDao.getFlightSegmentsByDateAndDepAndDest(date, departure, destination);
			//get not available class
			LinkedList<String> noFirstClassSeats = new LinkedList<String>();
			LinkedList<String> noBusinessClassSeats = new LinkedList<String>();
			LinkedList<String> noEconomyClassSeats = new LinkedList<String>();
			AirplaneDAO aDao = new AirplaneDAO();
			FlightTicketDAO ftDao = new FlightTicketDAO();
			for(Iterator<FlightSegmentBean> iter = foundFlights.iterator(); iter.hasNext();){
				FlightSegmentBean flight = iter.next();
				AirplaneBean airplane = aDao.getAirplaneById(flight.getFlightBy());
				if(airplane.getFirstClassSeats() <= ftDao.getNumberOfTicketsSoldForFlightAndClass(flight.getDate(), flight.getFlight_number(), "firstClass")){
					noFirstClassSeats.add(flight.getFlight_number());
				}
				if(airplane.getBusinessClassSeats() <= ftDao.getNumberOfTicketsSoldForFlightAndClass(flight.getDate(), flight.getFlight_number(), "businessClass")){
					noBusinessClassSeats.add(flight.getFlight_number());
				}
				if(airplane.getEconomyClassSeats() <= ftDao.getNumberOfTicketsSoldForFlightAndClass(flight.getDate(), flight.getFlight_number(), "economyClass")){
					noEconomyClassSeats.add(flight.getFlight_number());
				}
			}
			//set session			
			session.setAttribute("foundFlights", foundFlights);
			session.setAttribute("noFirstClassSeats", noFirstClassSeats);
			session.setAttribute("noBusinessClassSeats", noBusinessClassSeats);
			session.setAttribute("noEconomyClassSeats", noEconomyClassSeats);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ChooseFlightSegment.jsp");
			dispatcher.forward(request, response);
			}
		} catch(Throwable e){
			request.setAttribute("error", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/FlightSegment.jsp");
			dispatcher.forward(request, response);
		}
	}

}
