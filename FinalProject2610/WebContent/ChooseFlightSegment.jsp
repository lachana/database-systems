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
<title>ChooseFlightSegment</title>
</head>
<body>
<a href="/FinalProject2610/MainPage.jsp">Home</a><br>
<%PassengerBean passenger = (PassengerBean)session.getAttribute("passenger");
LinkedList<FlightSegmentBean> flightSegments = (LinkedList<FlightSegmentBean>) session.getAttribute("foundFlights");
LinkedList<String> noFirstClassSeats = (LinkedList<String>)session.getAttribute("noFirstClassSeats");
LinkedList<String> noBusinessClassSeats = (LinkedList<String>)session.getAttribute("noBusinessClassSeats");
LinkedList<String> noEconomyClassSeats = (LinkedList<String>)session.getAttribute("noEconomyClassSeats");%>
You are logged in as: <%=passenger.getFirstName() %> <%=passenger.getLastName() %><br><br>

<%if(flightSegments.isEmpty()){ %>
<h2>no FlightSegments found</h2>
<%} %>

<table>
	<caption>Choose Flight</caption>
	<tr>
		<th>Date</th>
		<th>FlightNumber</th>
		<th>Departure</th>
		<th>Destination</th>
		<th>DepartureTime</th>
		<th>BoardingTime</th>
		<th>TimeOfArrival</th>
		<th>FirstClassSeat</th>
		<th>BusinessClassSeat</th>
		<th>EconomyClassSeat</th>
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
		<td><%if(noFirstClassSeats.contains(flight.getFlight_number())){ %>
		no seat left <%} else{ %> 
		<a href="/FinalProject2610/flightSegment?number=<%=flight.getFlight_number()%>&class=firstClass">book firstClass</a><%} %>
		</td>
		<td><%if(noBusinessClassSeats.contains(flight.getFlight_number())){ %>
		no seat left <%} else{ %> 
		<a href="/FinalProject2610/flightSegment?number=<%=flight.getFlight_number()%>&class=businessClass">book businessClass</a><%} %>
		</td>
		<td><%if(noEconomyClassSeats.contains(flight.getFlight_number())){ %>
		no seat left <%} else{ %> 
		<a href="/FinalProject2610/flightSegment?number=<%=flight.getFlight_number()%>&class=economyClass">book economyClass</a><%} %>
		</td>
	</tr>
	<%for(Iterator<String> iterMC = flight.getMaerketing_carrier_flight_numbers().iterator(); iterMC.hasNext();){
		String mcflight = iterMC.next();%>
	<tr>
		<td></td>
		<td><%= flight.getDate() %></td>
		<td><%= mcflight %></td>
		<td><%= flight.getDeparture_airport() %></td>
		<td><%= flight.getArrival_airport() %></td>
		<td><%= flight.getDeparture_time() %></td>
		<td><%= flight.getBoarding_time() %></td>
		<td><%= flight.getArriving_time() %></td>
		<td><%if(noFirstClassSeats.contains(flight.getFlight_number())){ %>
		no seat left <%} else{ %> 
		<a href="/FinalProject2610/flightSegment?number=<%=flight.getFlight_number()%>&class=firstClass">book firstClass</a><%} %>
		</td>
		<td><%if(noBusinessClassSeats.contains(flight.getFlight_number())){ %>
		no seat left <%} else{ %> 
		<a href="/FinalProject2610/flightSegment?number=<%=flight.getFlight_number()%>&class=businessClass">book businessClass</a><%} %>
		</td>
		<td><%if(noEconomyClassSeats.contains(flight.getFlight_number())){ %>
		no seat left <%} else{ %> 
		<a href="/FinalProject2610/flightSegment?number=<%=flight.getFlight_number()%>&class=economyClass">book economyClass</a><%} %>
		</td>
	</tr>
	<%} %>
	<%} %>
</table>
</body>
</html>