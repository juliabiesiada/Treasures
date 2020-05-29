<%@ page import="java.util.*, pl.ue.poznan.model.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add offer</title>
</head>
<body>
	<%
		User thisUser = (User) request.getAttribute("user");
	%>
	
	<h3 style="color:red;">${error} </h3>

	<form action="OfferControllerServlet" method="get">
		<input type="hidden" name="command" value="ADD_OFFER">
		<input type="hidden" name="username" value=<%=thisUser.getUsername()%>>
		<table>
			<tr>
				<td>Title (max 50 char):</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>Description (max 250 char):</td>
				<td><input type="text" name="desc"></td>
			</tr>
			<tr>
				<td>Price/day:</td>
				<td><input type="number" name="price"></td>
			</tr>
			<tr>
				<td><select id="category" name="category">
						<option value="1">Wedding</option>
						<option value="2">Sports</option>
						<option value="3">Cocktail dresses, suits</option>
						<option value="4">Shoes</option>
						<option value="5">Accessories</option>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add">
			</tr>
		</table>

	</form>
</body>
</html>