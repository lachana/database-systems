package airTraffic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airTraffic.model.dao.TenantDAO;

public class TenantServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String redirect = "";
		String OId = request.getParameter("key");
		if (OId != null){
		int Id = Integer.parseInt(OId);
		TenantDAO dao = new TenantDAO();
		dao.ViewTenantActive(Id);
		redirect = "/TenantInfo.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/TenantInfo.jsp");
		dispatcher.forward(request, response);
				
    }
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    
		RequestDispatcher dispatcher = request.getRequestDispatcher("/TenantInfo.jsp");
		dispatcher.forward(request, response);
    }

}
