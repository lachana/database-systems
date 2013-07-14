package airTraffic;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import airTraffic.model.bean.FlightSegmentBean;
import airTraffic.model.bean.JourneyBean;
import airTraffic.model.bean.PassengerBean;
import airTraffic.model.dao.FlightSegmentDAO;
import airTraffic.model.dao.JourneyDAO;

/**
 * Servlet implementation class JourneyServlet
 */
@WebServlet("/JourneyServlet")
public class JourneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JourneyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int jId = Integer.parseInt(request.getParameter("jId"));
			HttpSession session = request.getSession(false);
			LinkedList<JourneyBean> journeys = (LinkedList<JourneyBean>)session.getAttribute("journeys");
			//set selected journey in session
			for(Iterator<JourneyBean> iter = journeys.iterator(); iter.hasNext();){
				JourneyBean journey = iter.next();
				if(journey.getjId() == jId){
					session.setAttribute("journey", journey);
					break;
				}
			}
			//get flightSegments associated with journey
			FlightSegmentDAO fDao = new FlightSegmentDAO();
			LinkedList<FlightSegmentBean> flightSegments = new LinkedList<FlightSegmentBean>();
			flightSegments = fDao.getFlightSegmentsByJId(jId);
			session.setAttribute("flightSegments", flightSegments);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/FlightSegment.jsp");
			dispatcher.forward(request, response);
		} catch(Throwable e){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Journey.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//get session and passenger
			HttpSession session = request.getSession(false);
			PassengerBean passenger = (PassengerBean)session.getAttribute("passenger");
			//create journey
			JourneyBean journey = new JourneyBean();
			journey.setTravelAgency(request.getParameter("travelAgency"));
			journey.setPaymentType(request.getParameter("paymentType"));
			if(request.getParameter("travelCancelInsurance") != null){
				journey.setTravelCancelInsurance(true);
			} else{
				journey.setTravelCancelInsurance(false);
			}
			journey.setBooked_by(passenger.getPassportId());
			//insert journey into db
			JourneyDAO jDao = new JourneyDAO();
			jDao.insertJourney(journey);
			//get jId
			journey.setjId(jDao.getLastInsertId());
			//set session attributes
			session.setAttribute("journey", journey);
			LinkedList<FlightSegmentBean> flightSegments = new LinkedList<FlightSegmentBean>();
			session.setAttribute("flightSegments", flightSegments);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/FlightSegment.jsp");
			dispatcher.forward(request, response);
		} catch(Throwable e){
			request.setAttribute("error", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Journey.jsp");
			dispatcher.forward(request, response);
		}
	}

}
