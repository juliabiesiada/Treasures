<%@ page import="java.util.*, pl.ue.poznan.model.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit profile</title>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<%
	//getting the user from a request
	User thisUser = (User) request.getAttribute("user");
	%>

	<form action="UserControllerServlet" method="get">
		<input type="hidden" name="command" value="SAVE_NEW_DATA">
		<input type="hidden" name="username" value=<%=thisUser.getUsername()%>>
		<table>
			<tr>
				<td>
					<h3 style="color: red;">${message}</h3>
				</td>
			</tr>
			<tr>
				<td>First name:</td>
				<td><input type="text" name="fname" value=<%=thisUser.getFirstname()%>>
			</tr>
			<tr>
				<td>Last name:</td>
				<td><input type="text" name="lname" value=<%=thisUser.getLastname()%>>
			</tr>
			<tr>
				<td>Birth date:</td>
				<td><input type="date" name="birthdate" value=<%=thisUser.getBirthdate()%>>
			</tr>
			<tr>
				<td>City:</td>
				<td><input type="text" name="city" value=<%=thisUser.getCity()%>>
			</tr>
			<tr>
				<td>Street:</td>
				<td><input type="text" name="street" value=<%=thisUser.getStreet()%>>
			</tr>
			<tr>
				<td>Post code:</td>
				<td><input type="text" name="postCode" value=<%=thisUser.getPostcode()%>>
			</tr>
			<tr>
				<td>Phone number:</td>
				<td><input type="text" name="phoneNr" value=<%=thisUser.getPhonenumber()%>>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" value=<%=thisUser.getEmail()%>>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" value=<%=thisUser.getPassword()%>>
				<td><input type="password" name="passwordConf" value=<%=thisUser.getPassword()%>>
			</tr>
			<tr>
				<td><input type="submit" value="Save changes">
			</tr>
		</table>
	</form>
</body>
</html>