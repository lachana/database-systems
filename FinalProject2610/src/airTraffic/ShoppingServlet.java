package airTraffic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airTraffic.model.bean.ShoppingServiceBean;
import airTraffic.model.dao.ShoppingDAO;

public class ShoppingServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ShoppingArea.jsp");

		String redirect="";
		String action = request.getParameter("action");
		ShoppingDAO dao = new ShoppingDAO();
		if (action.equalsIgnoreCase("listAreas")){
			redirect = "/ShoppingArea.jsp";
			request.setAttribute("users", dao.ViewAreas());
		} else {
			redirect = "/AddArea.jsp";
		}
		dispatcher.forward(request, response);
	}

	private int parseInt(String parameter) {
		// TODO Auto-generated method stub
		return 0;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		String redirect="";
		String sId = request.getParameter("sid");        
		String action = request.getParameter("action");
		ShoppingDAO dao = new ShoppingDAO();
		if(!((sId)== null) && action.equalsIgnoreCase("insert"))
		{
			int id = Integer.parseInt(sId);
			ShoppingServiceBean area = new ShoppingServiceBean();

			area.setsId(id);
			area.setName(request.getParameter("name"));
			area.setLocation(request.getParameter("location"));
			area.setAssignment(request.getParameter("assignment"));
			area.setSize(parseInt (request.getParameter("size")));
			area.setAreaNumber(parseInt (request.getParameter("number")));
			area.setOfferedBy(request.getParameter("offeredby"));
			dao.addArea(area);
			redirect = "/ShoppingArea.jsp";
			request.setAttribute("areas", dao.ViewAreas());    
			System.out.println("Record Added Successfully");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/ShoppingArea.jsp");
		dispatcher.forward(request, response);
	}
}