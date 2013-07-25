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

import airTraffic.model.bean.CrewmemberBean;
import airTraffic.model.dao.PersonnelDAO;

/**
 * Servlet implementation class PersonnelServlet
 */
@WebServlet("/PersonnelServlet")
public class PersonnelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonnelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String flightNumber = request.getParameter("flightNumber");
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			String airline = request.getParameter("airline");
			HttpSession session = request.getSession(true);
			session.setAttribute("flightNumber", flightNumber);
			session.setAttribute("date", date);
			PersonnelDAO pDao = new PersonnelDAO();
			LinkedList<CrewmemberBean> pilots = pDao.getCrewmembersByCrewPositionAndFreeDateAndAirline("Pilot", date, airline);
			LinkedList<CrewmemberBean> coPilots = pDao.getCrewmembersByCrewPositionAndFreeDateAndAirline("CoPilot", date, airline);
			LinkedList<CrewmemberBean> attendants = pDao.getCrewmembersByCrewPositionAndFreeDateAndAirline("FlightAttendant", date, airline);
			session.setAttribute("pilots", pilots);
			session.setAttribute("coPilots", coPilots);
			session.setAttribute("attendants", attendants);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Personnel.jsp");
			dispatcher.forward(request, response);
		} catch(Throwable e){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/flights");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			String flightNumber = (String)session.getAttribute("flightNumber");
			Date date = (Date)session.getAttribute("date");
			int pilotId = Integer.parseInt(request.getParameter("pilot"));
			int coPilotId = Integer.parseInt(request.getParameter("coPilot"));
			int attendantId = Integer.parseInt(request.getParameter("attendant"));
			PersonnelDAO pDao = new PersonnelDAO();
			pDao.addServe(flightNumber, date, pilotId);
			pDao.addServe(flightNumber, date, coPilotId);
			pDao.addServe(flightNumber, date, attendantId);
			response.sendRedirect("/FinalProject2610/flights");
		} catch(Throwable e){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Personnel.jsp");
			dispatcher.forward(request, response);
		}
	}

}
