package airTraffic;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import airTraffic.model.bean.JourneyBean;
import airTraffic.model.bean.PassengerBean;
import airTraffic.model.dao.JourneyDAO;
import airTraffic.model.dao.PassengerDAO;

/**
 * Servlet implementation class PassengerServlet
 */
@WebServlet("/PassengerServlet")
public class PassengerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassengerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Passenger.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(true);
			PassengerDAO pDao = new PassengerDAO();
			PassengerBean passenger = new PassengerBean();
			LinkedList<JourneyBean> journeys = new LinkedList<JourneyBean>();
			//get existing passenger
			if(request.getParameter("existingPassportId") != null){
				String passportId = request.getParameter("existingPassportId");
				passenger = pDao.getPassengerByPassportId(passportId);
				JourneyDAO jDao = new JourneyDAO();
				journeys = jDao.getJourneysByPassenger(passportId);
			} else { //insert new passenger
				passenger.setPassportId(request.getParameter("passportId"));
				passenger.setFirstName(request.getParameter("firstName"));
				passenger.setLastName(request.getParameter("lastName"));
				passenger.setAddress(request.getParameter("address"));
				java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("dateOfBirth"));
				java.sql.Date date = new java.sql.Date(utilDate.getTime());
				passenger.setDateOfBirth(date);
				pDao.insertPassenger(passenger);
			}
			//set session attributes
			session.setAttribute("passenger", passenger);
			session.setAttribute("journeys", journeys);
			//dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Journey.jsp");
			dispatcher.forward(request, response);
		} catch(Throwable e){
			request.setAttribute("error", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Passenger.jsp");
			dispatcher.forward(request, response);
		}
	}

}
