<%@ 
	page
	import="java.util.ArrayList,airTraffic.model.bean.FlightSegmentBeanAirline"%>
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
	<table>
		<thead>
			<tr>
				<td>Flight Number</td>
				<td>Date</td>
				<td>Gate Number</td>
				<td>Boarding Time</td>
				<td>Marketing Carrier Flights Numbers</td>
				<td>Arrival</td>
				<td>Departure</td>
				<td>To</td>
				<td>From</td>
				<td>Operated By</td>
				<td>Flight By</td>
			</tr>
		</thead>
		<%
			ArrayList<FlightSegmentBeanAirline> allFlights = null;
				allFlights = (ArrayList) request.getAttribute("allFlights");
				for(FlightSegmentBeanAirline f : allFlights) {
		%>
		<tr>
				<td><% out.print(f.getFlight_number()); %></td>
				<td><% out.print(f.getDate()); %></td>
				<td><% out.print(f.getGate_nr()); %></td>
				<td><% out.print(f.getBoarding_time()); %></td>
				<td><% out.print(f.getMaerketing_carrier_flight_numbers()); %></td>
				<td><% out.print(f.getArriving_time()); %></td>
				<td><% out.print(f.getDeparture_time()); %></td>
				<td><% out.print(f.getArrival_airport()); %></td>
				<td><% out.print(f.getDeparture_airport()); %></td>
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
				<td>Gate Number</td>
				<td>Boarding Time</td>
				<td>Marketing Carrier Flights Numbers</td>
				<td>Arrival</td>
				<td>Departure</td>
				<td>To</td>
				<td>From</td>
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
				<td><input name="marketingFlightsNumber" type="text" /></td>
				<td><input name="arrivingTime" type="text" /></td>
				<td><input name="departureTime" type="text" /></td>
				<td><input name="arrivalAirport" type="text" /></td>
				<td><input name="departureAirport" type="text" /></td>
				<td><input name="operatedBy" type="text" /></td>
				<td><input name="flightBy" type="text" /></td>
				<td><input type="submit" name="AddNewFlight"
					value="Add new flight" /></td>
			</tr>
		</table>
	</form>

</body>
</html>