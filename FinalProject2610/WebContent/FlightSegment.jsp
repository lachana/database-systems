<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="airTraffic.model.bean.PassengerBean" %>
<%@ page import="airTraffic.model.bean.FlightSegmentBean" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Iterator" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FlightSegment</title>
</head>
<body>
<a href="/FinalProject2610/MainPage.jsp">Home</a><br>
<%PassengerBean passenger = (PassengerBean)session.getAttribute("passenger");
LinkedList<FlightSegmentBean> flightSegments = (LinkedList<FlightSegmentBean>) session.getAttribute("flightSegments");%>
You are logged in as: <%=passenger.getFirstName() %> <%=passenger.getLastName() %><br><br>

<%if(request.getAttribute("error") != null){ %>
<h2>Please enter valid data</h2>
<%} %>


<%if(request.getAttribute("success") != null){ %>
<h2>Flight successfully booked</h2>
<%} %>
<table>
	<caption>Already booked flights</caption>
	<tr>
		<th>Date</th>
		<th>FlightNumber</th>
		<th>Departure</th>
		<th>Destination</th>
		<th>DepartureTime</th>
		<th>BoardingTime</th>
		<th>TimeOfArrival</th>
	</tr>
	<%for(Iterator<FlightSegmentBean> iter = flightSegments.iterator(); iter.hasNext();){
		FlightSegmentBean flight = iter.next();%>
	<tr>
		<td><%= flight.getDate() %></td>
		<td><%= flight.getFlight_number() %></td>
		<td><%= flight.getDeparture_airport() %></td>
		<td><%= flight.getArrival_airport() %></td>
		<td><%= flight.getDeparture_time() %></td>
		<td><%= flight.getBoarding_time() %></td>
		<td><%= flight.getArriving_time() %></td>
	</tr>
	<%} %>
</table>

<form name="searchFlightSegment" action="/FinalProject2610/flightSegment" method="post">
	<fieldset>
		<legend>Search for FlightSegment</legend>
		<label>Date</label><input type="text" name="date"><br>
		<label>Departure Airport</label><input type="text" name="departure"><br>
		<label>Destination Airport</label><input type="text" name="destination"><br>		
		<input type="submit" value="Search">
	</fieldset>
</form>
</body>
</html>