<%@ page import="java.util.*, pl.ue.poznan.model.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<%
	User thisUser = (User) request.getAttribute("user");
%>
<body style="color: grey;">

	<h1>Hi ${username}! Welcome!</h1>
	<table>
		<tr>
			<td><a href="LogOut.jsp">Logout</a></td>
			<td>
				<form action="UserControllerServlet" method="get">
					<input type="hidden" name="username" value="<%=thisUser.getUsername()%>"> 
					<input type="hidden" name="command" value="PROFILE">
					<input type="submit" value="My profile">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>