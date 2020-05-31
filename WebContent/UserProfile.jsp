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
			<form action=NotificationControllerServlet method="get">
				<input type="hidden" name="username" value=<%=thisUser.getUsername()%>>
				<input type="hidden" name="logged_user" value="${logged_user}">
				<input type="hidden" name="command" value="SEND_MESSAGE_FORM">
				<input type="submit" value="Send message">
			</form><p>
		</div>
		<div>
			<form action=UserControllerServlet method="get">
				<input type="hidden" name="username" value=<%=thisUser.getUsername()%>>
				<input type="hidden" name="command" value="EDIT_PROFILE">
				<input type="submit" value="Edit profile">
			</form><p>
			<h3>About me:</h3>
			City: ${city}
		</div><p>
		<form action=OfferControllerServlet method="get">
			<input type="hidden" name="username" value=<%=thisUser.getUsername()%>>
			<input type="hidden" name="command" value="ADD_OFFER_FORM">
			<input type="submit" value="Add offer">
		</form>
		<form action=OfferControllerServlet method="get">
			<input type="hidden" name="username" value=<%=thisUser.getUsername()%>>
			<input type="hidden" name="command" value="GET_USERS_OFFERS">
			<input type="submit" value="My offers">
		</form>
		
	</body>
</html>