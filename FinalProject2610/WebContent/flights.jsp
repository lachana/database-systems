<%@ 
	page
	import="java.util.ArrayList,airTraffic.model.bean.FlightSegmentBean, java.util.LinkedList, java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flights Manager</title>
</head>
<body>
<a href="/FinalProject2610/MainPage.jsp">home</a><br>
	<h2>All available flights</h2>
	<table border = "1">
		<thead>
			<tr>
				<td width=10%>Flight Number</td>
				<td width=20%>Date</td>
				<td width=10%>Gate</td>
				<td width=10%>Boarding Time</td>
				<td width=10%>Departure</td>
				<td width=10%>Arrival</td>
				<td width=10%>From</td>
				<td width=10%>To</td>
				<td width=15%>Operated By</td>
				<td width=15%>Flight By</td>
				<td width=15%>MarketingCarrierNumbers</td>
			</tr>
		</thead>
		<% 
			ArrayList<FlightSegmentBean> allFlights = null;
			allFlights = (ArrayList <FlightSegmentBean>) request.getAttribute("allFlights");
			for(FlightSegmentBean f : allFlights) {
			 %>
		<tr>
				<td><a href="/FinalProject2610/personnel?flightNumber=<%=f.getFlight_number()%>&date=<%=f.getDate()%>&airline=<%=f.getOperated_by()%>"><%=f.getFlight_number()%></a></td>
				<td><%= f.getDate() %></td>
				<td><% out.print(f.getGate_nr()); %></td>
				<td><% out.print(f.getBoarding_time()); %></td>
				<td><% out.print(f.getDeparture_time()); %></td>
				<td><% out.print(f.getArriving_time()); %></td>
				<td><% out.print(f.getDeparture_airport()); %></td>
				<td><% out.print(f.getArrival_airport()); %></td>
				<td><% out.print(f.getOperated_by()); %></td>
				<td><% out.print(f.getFlightBy()); %></td>
				<td><%
				LinkedList<String> marketingCarriers = f.getMaerketing_carrier_flight_numbers();
				for(Iterator<String> iter = marketingCarriers.iterator(); iter.hasNext();){
				String carrier = iter.next();%>
				<%= carrier %>, 
				<%} %> <a href="/FinalProject2610/marketingCarrier?flightNumber=<%=f.getFlight_number()%>&date=<%=f.getDate()%>">add MarketingCarrier</a>
				</td>
		</tr>
		<% } %>
	</table>

<%if(request.getAttribute("error") != null){%>
<h2>Please enter valid data</h2>
<% }%>

	<h2>Add flight</h2>
	<form method="post" action="flights">
		<table>
			
				<tr>
				<td>Flight Number</td>
				<td><input name="flightNumber" type="text" /></td>
				</tr>
				<tr>
				<td>Date</td>
				<td><input name="flightDate" type="text" />(yyyy-MM-dd)</td>
				</tr>
				<tr>
				<td>Gate</td>
				<td><input name="gateNr" type="text" /></td>
				</tr>
				<tr>
				<td>Boarding Time</td>
				<td><input name="boardingTime" type="text" />(hh:mm:ss)</td>
				</tr>
				<tr>
				<td>Departure</td>
				<td><input name="departureTime" type="text" />(hh:mm:ss)</td>
				</tr>
				<tr>
				<td>Arrival</td>
				<td><input name="arrivingTime" type="text" />(hh:mm:ss)</td>
				</tr>
				<tr>
				<td>From</td>
				<td><input name="departureAirport" type="text" /></td>
				</tr>
				<tr>
				<td>To</td>
				<td><input name="arrivalAirport" type="text" /></td>
				</tr>
				<tr>
				<td>Operated By</td>
				<td><input name="operatedBy" type="text" /></td>
				</tr>
				<tr>
				<td>Flight By</td>
				<td><input name="flightBy" type="text" /></td>
				</tr>
				<tr>
				<td><!-- Don't touch! --></td>
				<td><input type="submit" name="AddNewFlight"
					value="Add new flight" /></td>
				</tr>
			
		</table>
	</form>

</body>
</html>