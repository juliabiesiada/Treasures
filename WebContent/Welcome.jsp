<%@ page import="java.util.*, pl.ue.poznan.model.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	<!--CHANGE!!!!!!!!!!!!!!!!!!!!-->
	<h1>Hi ${logged_user}! Welcome!</h1>
	<table>
		<tr>
			<td><a href="LogOut.jsp">Logout</a></td>
			<td>
				<form action="UserControllerServlet" method="get">
					<!--CHANGE!!!!!!!!!!!!!!!!!!!!-->
					<input type="hidden" name="logged_user" value="<%=thisUser.getUsername()%>"> 
					<input type="hidden" name="command" value="MY_PROFILE">
					<input type="submit" value="My profile">
				</form>
			</td>
		</tr>
		<tr>
			<td>${message}</td>
		</tr>
		<tr>
			<c:forEach items = "${offers}" var = "offer">
				<td>
					<p><img src="data:image/jpg;base64,${offer.base64Image}" width="240" height="300"/></p>
					<p>${offer.title}</p>
					<p>${offer.price}</p>
					<p>${offer.category_name}</p>
					<p><form action=OfferControllerServlet method="get">
							<input type="hidden" name="offer_id" value=${offer.oid }>
							<input type="hidden" name="logged_user" value="<%=thisUser.getUsername()%>">
							<input type="hidden" name="command" value="OFFER_DETAILS">
							<input type="submit" value="See details">
						</form>
					</p>
				</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>