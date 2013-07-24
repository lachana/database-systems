<%@ 
	page
	import="java.util.ArrayList,airTraffic.model.bean.FlightSegmentBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flights Manager</title>
</head>
<body>

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
			</tr>
		</thead>
		<% 
			ArrayList<FlightSegmentBean> allFlights = null;
			allFlights = (ArrayList <FlightSegmentBean>) request.getAttribute("allFlights");
			for(FlightSegmentBean f : allFlights) {
			 %>
		<tr>
				<td><% out.print(f.getFlight_number()); %></td>
				<td><%= f.getDate() %></td>
				<td><% out.print(f.getGate_nr()); %></td>
				<td><% out.print(f.getBoarding_time()); %></td>
				<td><% out.print(f.getDeparture_time()); %></td>
				<td><% out.print(f.getArriving_time()); %></td>
				<td><% out.print(f.getDeparture_airport()); %></td>
				<td><% out.print(f.getArrival_airport()); %></td>
				<td><% out.print(f.getOperated_by()); %></td>
				<td><% out.print(f.getFlightBy()); %></td>

		</tr>
		<% } %>
	</table>

	<h2>Add flight</h2>
	<form method="post" action="flights">
		<table>
			<thead>
				<tr>
				<td>Flight Number</td>
				<td>Date</td>
				<td>Gate</td>
				<td>Boarding Time</td>
				<td>Departure</td>
				<td>Arrival</td>
				<td>From</td>
				<td>To</td>
				<td>Operated By</td>
				<td>Flight By</td>
					<td>
						<!-- Don't touch! -->
					</td>
				</tr>
			</thead>
			<tr>
				<td><input name="flightNumber" type="text" /></td>
				<td><input name="flightDate" type="text" /></td>
				<td><input name="gateNr" type="text" /></td>
				<td><input name="boardingTime" type="text" /></td>
				<td><input name="departureTime" type="text" /></td>
				<td><input name="arrivingTime" type="text" /></td>
				<td><input name="departureAirport" type="text" /></td>
				<td><input name="arrivalAirport" type="text" /></td>
				<td><input name="operatedBy" type="text" /></td>
				<td><input name="flightBy" type="text" /></td>
				<td><input type="submit" name="AddNewFlight"
					value="Add new flight" /></td>
			</tr>
		</table>
	</form>

</body>
</html>