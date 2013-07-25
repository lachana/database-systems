<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Passenger</title>
</head>
<body>
<a href="/FinalProject2610/MainPage.jsp">Home</a><br>
<%if(request.getAttribute("error") != null){ %>
<h2>Please enter all data correctly</h2>
<%} %>
<form name="existingPassenger" action="/FinalProject2610/passenger" method="post">
	<fieldset>
	<legend>Existing Passenger</legend>
	PassportId: <input type="text" name="existingPassportId"><br>
	<input type="submit" value="Enter">
	</fieldset>
</form>

<form name="newPassenger" action="/FinalProject2610/passenger" method="post">
	<fieldset>
		<legend>New Passenger</legend>
		<table>
		<tr><td><label>PassportId</label></td><td> <input type="text" name="passportId"></td></tr>
		<tr><td><label>First Name</label></td><td> <input type="text" name="firstName"></td></tr>
		<tr><td><label>Last Name</label></td><td> <input type="text" name="lastName"></td></tr>
		<tr><td><label>Address</label></td><td> <input type="text" name="address"></td></tr>
		<tr><td><label>Date of Birth</label></td><td> <input type="text" name="dateOfBirth">(dd-MM-yyyy)</td></tr>
		<tr><td></td><td><input type="submit" value="Enter"></td></tr>
		</table>
	</fieldset>
</form>
</body>
</html>