<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
<form action="UserControllerServlet" method="get">
	<input type="hidden" name="command" value="REGISTER">
	<table>
		<tr>
		<td>
		<h3 style="color:red;">${message} </h3>
		<h3 style="color:green;">${successMessage} </h3>
			</td>
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
			<td>Role:</td>
			<td><input type="radio" name="roleid" value="1"> Lender
			<td><input type="radio" name="roleid" value="2"> Borrower 
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
			<td>Email:</td>
			<td><input type="text" name="email">
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="password">
		</tr>
		<tr>
			<td>Confirm password:</td>
			<td><input type="password" name="passwordconf">
		</tr>
		<tr>
			<td><input type="submit" value="Register" name="conf"></td>
		</tr>
	</table>
	
	</form>
</body>
</html>