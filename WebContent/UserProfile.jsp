<%@ page import="java.util.*, pl.ue.poznan.model.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
	<% 
		User thisUser = (User) request.getAttribute("user");
	%>
	<body style="color:grey;">
		<div>
			<h3 style="color: green;">${message}</h3>
		</div>
		<div>
			<h1>${fname} ${lname}</h1>
			<b>${role}</b><p>
			<em>${username}</em>
		</div>
		
		<div>
			<form action=UserControllerServlet method="get">
				<input type="hidden" name="username" value=<%=thisUser.getUsername()%>>
				<input type="hidden" name="command" value="EDIT_PROFILE">
				<input type="submit" value="Edit profile">
			</form><p>
			<h3>About me:</h3>
			City: ${city}
		</div>
		
	</body>
</html>