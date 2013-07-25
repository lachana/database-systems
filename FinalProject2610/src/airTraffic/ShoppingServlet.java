package airTraffic;

import java.io.IOException;
import java.util.List;

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
		
		ShoppingDAO dao = new ShoppingDAO();
		//Get all areas
		List<ShoppingServiceBean> allAreas;
		allAreas = dao.ViewAreas();
		//Add them as attributes to the request to make available in the JSP view
		request.setAttribute("areas", allAreas);
		//Set view
		RequestDispatcher view = request.getRequestDispatcher("ShoppingArea.jsp");
		view.forward(request, response); 
		
		
		
	
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ShoppingDAO dao = new ShoppingDAO();
		ShoppingServiceBean area = new ShoppingServiceBean();
		
		
		area.setName(request.getParameter("name"));
		area.setLocation(request.getParameter("location"));
		area.setAssignment(request.getParameter("assignment"));
		area.setSize(Integer.parseInt (request.getParameter("size")));
		area.setAreaNumber(Integer.parseInt (request.getParameter("number")));
		area.setOfferedBy(request.getParameter("offeredby"));
			
		
		dao.addArea(area);
        response.sendRedirect("ShoppingArea.jsp");
		
		

	
	}
}