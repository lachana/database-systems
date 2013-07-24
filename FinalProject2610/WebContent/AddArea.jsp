<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Add new shopping area</title>
</head>
<body>
<a href = "ShoppingArea.jsp">Back to the list of shopping areas</a>
<p> or </p>
<a href = "MainPage.jsp">Back to the main page</a>
<form method="post" action="shopping">
<h2>Add New Area</h2>
<table>
<tr>
<td>Area ID</td>
<td><input type="text" name="sid" /></td>
</tr>
<tr>
<td>Name</td>
<td><input type="text" name="name" /></td>
</tr>
<tr>
<td>Location</td>
<td><input type="text" name="location" /></td>
</tr>
<tr>
<td>Assignment</td>
<td><input type="text" name="assignment" /></td>
</tr>
<tr>
<td>Size</td>
<td><input type="text" name="size" /></td>
</tr>
<tr>
<td>Area number</td>
<td><input type="text" name="number" /></td>
</tr>
<tr>
<td>Offered by</td>
<td><input type="text" name="offeredby" /></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Add new area" /></td>
</tr>
</table>
</form>

</body>
</html>