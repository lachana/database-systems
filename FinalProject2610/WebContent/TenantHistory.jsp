<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="airTraffic.model.bean.ShoppingService" %>
<%@page import="airTraffic.model.dao.ShoppingDAO" %>
<%@page import="airTraffic.model.bean.Tenant" %>
<%@page import="airTraffic.model.dao.TenantDAO" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="ten" scope="request" class="airTraffic.model.bean.Tenant" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tenant history</title>
</head>
<body>
<a href = "ShoppingArea.jsp">Back to the list of shopping areas</a>
<p> or </p>
<a href = "MainPage.jsp">Back to the main page</a>
    	
    	<% String Id = request.getParameter("key");
    		int IntId = Integer.parseInt(Id);
    		
    		TenantDAO dao = new TenantDAO();
    		List<Tenant> tenants = dao.ViewTenantAll(IntId);
    		
    	%>
    	    	
     <% if (request.getAttribute("error") != null) { %>
        	<h1>Tenant not found!</h1>
        <% } else { %>
        <h1>All tenants of shopping area</h1>
        
        <TABLE border="1" style="background-color: #ffffcc;">
<tr>
        <td width="10%">
          <div align="left">Tenant ID</div>
        </td>
        <td width="20%">
          <div align="left">Last name</div>
        </td>
        <td width="20%">
          <div align="left">First name</div>
        </td>
        <td width="20%">
          <div align="left">Address</div>
        </td>
        <td width="20%">
          <div align="left">Email</div>
        </td>
        <td width="20%">
          <div align="left">Is active</div>
        </td>
       </tr>

<% for (Tenant tenant : tenants) {%>
<TR>
<TD><%=tenant.gettId()%></TD>
<TD><%=tenant.getLastName()%></TD>
<TD><%=tenant.getFirstName()%></TD>
<td><%=tenant.getAddress() %></td>
<td><%=tenant.getEmail() %> </td>
<td><%=tenant.isActive() %>></td>
</TR>
<% } }%>
</TABLE>
</body>
</html>