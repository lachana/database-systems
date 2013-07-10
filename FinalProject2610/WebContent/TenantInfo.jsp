<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="airTraffic.model.bean.ShoppingService" %>
<%@page import="airTraffic.model.dao.ShoppingDAO" %>
<%@page import="airTraffic.model.bean.Tenant" %>
<%@page import="airTraffic.model.dao.TenantDAO" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="tenant" scope="request" class="airTraffic.model.bean.Tenant" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tenant information</title>
</head>
<body>
<a href = "ShoppingArea.jsp">Back to the list of shopping areas</a>
    	<% String Id = request.getParameter("key");
    		int IntId = Integer.parseInt(Id);
    		TenantDAO dao = new TenantDAO();
    		tenant = dao.ViewTenantActive(IntId);
    		
    	%>
    	    	
     <% if (request.getAttribute("error") != null) { %>
        	<h1>Order not found!</h1>
        <% } else { %>
        	<h1>Tenant info</h1>
        	<p>Area Id: <%= IntId %></p>        	
        	<p>Tenant id: <%= tenant.gettId() %></p> 
        	<p>Last name: <%= tenant.getLastName() %></p>
        	<p>First name: <%= tenant.getFirstName() %></p>
        	<p>Address: <%= tenant.getAddress() %></p>
        	<p>Email: <%= tenant.getEmail()%></p>
        	      	
        <% } %> 
</body>
</html>