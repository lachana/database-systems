package airTraffic;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import airTraffic.model.dao.FlightSegmentDAO;

/**
 * Servlet implementation class MarketingCarrierServlet
 */
@WebServlet("/MarketingCarrierServlet")
public class MarketingCarrierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketingCarrierServlet() {
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
			HttpSession session = request.getSession(true);
			session.setAttribute("flightNumber", flightNumber);
			session.setAttribute("date", date);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MarketingCarrier.jsp");
			dispatcher.forward(request, response);
		} catch(Throwable e){
			request.setAttribute("error", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("FinalProject2610/flights");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String marketingCarrier = request.getParameter("marketingCarrier");
			String marketingNumber = request.getParameter("marketingNumber");
			HttpSession session = request.getSession();
			Date date = (Date)session.getAttribute("date");
			String flightNumber = (String)session.getAttribute("flightNumber");
			FlightSegmentDAO fDao = new FlightSegmentDAO();
			fDao.addMarketingCarrier(flightNumber, date, marketingCarrier, marketingNumber);
			response.sendRedirect("/FinalProject2610/flights");
		} catch(Throwable e){
			request.setAttribute("error", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MarketingCarrier.jsp");
			dispatcher.forward(request, response);
		}
	}

}
