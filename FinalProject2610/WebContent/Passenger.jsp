<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Passenger</title>
</head>
<body>
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
		<label>Add New Passenger</label>
		<label>PassportId</label> <input type="text" name="passportId"><br>
		<label>First Name</label> <input type="text" name="firstName"><br>
		<label>Last Name</label> <input type="text" name="lastName"><br>
		<label>Address</label> <input type="text" name="address"><br>
		<label>Date of Birth</label> <input type="text" name="dateOfBirth"><br>
		<input type="submit" value="Enter">
	</fieldset>
</form>
</body>
</html>