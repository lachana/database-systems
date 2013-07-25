<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="airTraffic.model.bean.CrewmemberBean, java.util.LinkedList, java.util.Iterator"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Personnel</title>
</head>
<body>
<a href="/FinalProject2610/MainPage.jsp">home</a> <a href="/FinalProject2610/flights">all FlightSegments</a><br>

<%
LinkedList<CrewmemberBean> pilots = (LinkedList<CrewmemberBean>)session.getAttribute("pilots");
LinkedList<CrewmemberBean> coPilots = (LinkedList<CrewmemberBean>)session.getAttribute("coPilots");
LinkedList<CrewmemberBean> attendants = (LinkedList<CrewmemberBean>)session.getAttribute("attendants");
%>

<form action="/FinalProject2610/personnel" method="post">
Pilot<select name="pilot">
	<%for(Iterator<CrewmemberBean> iter = pilots.iterator(); iter.hasNext();){
		CrewmemberBean pilot = iter.next();%>
		<option value="<%=pilot.getPersID() %>"><%=pilot.getFirstName()%> <%=pilot.getLastName()%></option>
	<%} %>
</select>
CoPilot<select name="coPilot">
	<%for(Iterator<CrewmemberBean> iter = coPilots.iterator(); iter.hasNext();){
		CrewmemberBean coPilot = iter.next();%>
		<option value="<%=coPilot.getPersID() %>"><%=coPilot.getFirstName()%> <%=coPilot.getLastName()%></option>
	<%} %>
</select>
FlightAttendant<select name="attendant">
	<%for(Iterator<CrewmemberBean> iter = attendants.iterator(); iter.hasNext();){
		CrewmemberBean attendant = iter.next();%>
		<option value="<%=attendant.getPersID()%>"><%=attendant.getFirstName()%> <%=attendant.getLastName()%></option>
	<%} %>
</select>
<input type="submit" value="Enter">
</form>

</body>
</html>