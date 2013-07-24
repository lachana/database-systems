<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="airTraffic.model.bean.*" %>
<%@page import="airTraffic.model.dao.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="airport" scope="request" class="airTraffic.model.bean.ShoppingServiceBean" />

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Shopping areas</title>
</head>
<body>
<h2> Welcome to the Air Traffic Management System</h2>
<p> Please select the view you are interested in </p>
<FORM name="mapform" method="POST">
<SELECT name="jump" size="1">
<OPTION value="flights" SELECTED> Airline</option>
<OPTION value="ShoppingArea.jsp">Airport</option>
<OPTION value="passenger">Passenger</option>
</SELECT>
<INPUT type=button onClick= "location = '' + document.mapform.jump.options[ document.mapform.jump.selectedIndex ].value;" value="Go!">
</FORM>

</body>
</html>
