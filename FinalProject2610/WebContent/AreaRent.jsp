<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Rent an area in the airport</title>
</head>
<body>
<h1>Rent form</h1>
<h2>To rent an area please fill up the form below</h2>
<form method="POST" action='' name="RentArea"><input
type="hidden" name="action" value="rent" />
 <% String Id = request.getParameter("key");
    if (!((Id) == null)) {
	int IntId = Integer.parseInt(Id); %>
<table>
<tr>
<td>Area ID</td>
<td><input type="text" name="areaId" readonly="readonly"
value="<%=IntId%>"></td>
</tr>
<tr>
<td>Tenant Id</td>
<td><input type="text" name="tenantId"/></td>
</tr>
<tr>
<td>Price</td>
<td><input type="text" name="price" /></td>
</tr>
<tr>
<td>Date</td>
<td><input type="text" name="date" /></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Add" /></td>
</tr>
</table>
<%
} else
out.println("ID Not Found");
%>
</form>


</body>
</html>