<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add MarketingCarrier</title>
</head>
<body>
<a href="/FinalProject2610/Mainpage.jsp">home</a> <a href="/FinalProject2610/flights">all FlightSegments</a><br>

<%if(request.getAttribute("error") != null){ %>
<h2>Please enter valid data</h2>
<%} %>
<h2>Add MarketingCarrier</h2>
<form action="/FinalProject2610/marketingCarrier" method="post">
<table>
	<tr>
	<td>MarketingCarrier</td>
	<td><input type="text" name="marketingCarrier"></td>
	</tr>
	<tr>
	<td>MarketingCarrier FlightNumber</td>
	<td><input type="text" name="marketingNumber">
	</td> 
	<tr></tr>
	<tr>
	<td><input type="submit" value="Enter"></td>
	</tr>
	
</table>
</form>
</body>
</html>