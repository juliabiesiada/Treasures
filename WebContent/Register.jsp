<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
	<form action="RegisterServlet" method="post">
	
	<table>
		<tr>
			<td>ID:</td>
			<td><input type="number" name="id">
		</tr>
		<tr>
			<td>First name:</td>
			<td><input type="text" name="fname">
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input type="text" name="lname">
		</tr>
		<tr>
			<td>Hire date:</td>
			<td><input type="date" name="hiredate">
		</tr>
		<tr>
			<td>Salary:</td>
			<td><input type="number" name="salary">
		</tr>
		<tr>
			<td>Allowance:</td>
			<td><input type="number" name="allowance">
		</tr>
		<tr>
			<td>Position:</td>
			<td><input type="number" name="posID">
		</tr>
		<tr>
			<td>Job grade:</td>
			<td><input type="number" name="jgID">
		</tr>
		<tr>
			<td>Manager ID:</td>
			<td><input type="number" name="manID">
		</tr>
		<tr>
			<td>Department ID:</td>
			<td><input type="number" name="depID">
		</tr>
		<tr>
			<td><input type="submit" value="Register" name="conf"></td>
		</tr>
	</table>
	
	</form>

</body>
</html>