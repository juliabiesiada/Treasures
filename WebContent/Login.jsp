<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<form action="UserControllerServlet" method="get">
	<input type="hidden" name="command" value="LOGIN">	
	<table>
	<tr>
	<td>
	<h3 style="color:red;">${message} </h3>
	<h3 style="color:green;">${successMessage} </h3>
		</td>
	</tr>
		<tr>
			<td>Username</td>
			<td><input type="text" name="username">
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password">
		</tr>
		<tr>
			<td><input type="submit" value="Login" name="conf"></td>
		</tr>
		</table>
		</form>
</body>
</html>