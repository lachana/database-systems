<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="airTraffic.model.bean.ShoppingService" %>
<%@page import="airTraffic.model.dao.ShoppingDAO" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="area" scope="request" class="airTraffic.model.bean.ShoppingService" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Shopping areas</title>
</head>
<body>
 <%
    	  ShoppingDAO dao = new ShoppingDAO(); 
          List<ShoppingService> areas = dao.ViewAreas();
  %>
<h2>List of all shopping areas</h2>
<% if (request.getAttribute("error") != null) { %>
        	<h1>There is no info</h1>
        <% } else { %>
<TABLE border="1" style="background-color: #ffffcc;">
<tr>
        <td width="10%">
          <div align="left">Area ID</div>
        </td>
        <td width="10%">
          <div align="left">Name</div>
        </td>
        <td width="20%">
          <div align="left">Location</div>
        </td>
        <td width="20%">
          <div align="left">Assignment</div>
        </td>
        <td width="20%">
          <div align="left">Size</div>
        </td>
        <td width="20%">
          <div align="left">Offered by</div>
        </td>
        <td width="20%">
          <div align="left"></div>
        </td>
    </tr>

<% for (ShoppingService shop : areas) {%>
<TR>
<TD><%=shop.getsId()%></TD>
<TD><%=shop.getName()%></TD>
<TD><%=shop.getLocation()%></TD>
<td><%=shop.getAssignment() %></td>
<td><%=shop.getSize() %> </td>
<td><%=shop.getOfferedBy() %></td>
<TD><a href="TenantInfo.jsp?key=<%=area.getsId()%>">View tenant info</a></TD>
</TR>
<% } }
%>

</TABLE>
    
</body>
</html>