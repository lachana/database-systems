<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="airTraffic.model.bean.PassengerBean" %>
<%@ page import="airTraffic.model.bean.JourneyBean" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Iterator" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Journey</title>
</head>
<body>
<a href="/FinalProject2610/MainPage.jsp">Home</a><br>
<%PassengerBean passenger = (PassengerBean)session.getAttribute("passenger");
LinkedList<JourneyBean> journeys = (LinkedList<JourneyBean>) session.getAttribute("journeys");%>
You are logged in as: <%=passenger.getFirstName() %> <%=passenger.getLastName() %><br><br>
<%if(request.getAttribute("error") != null){ %>
<h2>Please enter valid data</h2>
<%} %>

<table>
	<caption>Choose a journey</caption>
	<tr>
		<th>JId</th>
		<th>TotalPrice</th>
		<th>TravelAgency</th>
		<th>PaymentType</th>
	</tr>
	<%for(Iterator<JourneyBean> iter = journeys.iterator(); iter.hasNext();){
		JourneyBean journey = iter.next();%>
	<tr>
		<td><a href="/FinalProject2610/journey?jId=<%= journey.getjId() %>"><%= journey.getjId() %></a></td>
		<td><%= journey.getTotalPrice() %></td>
		<td><%= journey.getTravelAgency() %></td>
		<td><%= journey.getPaymentType() %></td>
	</tr>
	<%} %>
</table>

<form name="newJourney" action="/FinalProject2610/journey" method="post">
	<fieldset>
		<legend>Create New Journey</legend>
		<label>Travel Agency</label><input type="text" name="travelAgency">
		PaymentType<select name="paymentType">
			<option value="MasterCard">MasterCard</option>
			<option value="VisaCard">VisaCard</option>
			<option value="Paypal">Paypal</option>
			<option value="BankTransfer">BankTransfer</option>
		</select><br>
		<input type="checkbox" name="travelCancelInsurance" value="true">TravelCancelInsurance<br>
		<input type="submit" value="Enter">
	</fieldset>
</form>
</body>
</html>