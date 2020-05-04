<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="RegisterUserServlet" method="post">
	
	<table>
		<tr>
			<td>ID:</td>
			<td><input type="number" name="id">
		</tr>
		<tr>
			<td>Username:</td>
			<td><input type="text" name="username">
		</tr>
		<tr>
			<td>First name:</td>
			<td><input type="text" name="firstname">
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input type="text" name="lastname">
		</tr>
		<tr>
			<td>City:</td>
			<td><input type="text" name="city">
		</tr>
		<tr>
			<td>Birth date:</td>
			<td><input type="date" name="birthdate">
		</tr>
		<tr>
			<td>Role id:</td>
			<td><input type="number" name="roleid">
		</tr>
		<tr>
			<td>Street:</td>
			<td><input type="text" name="street">
		</tr>
		<tr>
			<td>Post code:</td>
			<td><input type="text" name="postcode">
		</tr>
		<tr>
			<td>Phone number:</td>
			<td><input type="text" name="phonenumber">
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="text" name="password">
		</tr>
		<tr>
			<td><input type="submit" value="Register" name="conf"></td>
		</tr>
	</table>
	
	</form>
</body>
</html>