<%@ page import="java.sql.*, java.io.*, java.util.*, pl.ue.poznan.model.*" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My messages</title>
</head>
<body>
	<h3>${message}</h3>
	<c:forEach items = "${messages}" var = "message">
		<p>${message.senderUname}</p>
		<p>${message.dateAdded}</p>
		<p><form action=NotificationControllerServlet method="get">
				<input type="hidden" name="logged_user" value=${message.recipientUname }>
				<input type="hidden" name="notif_id" value=${message.nid }>
				<input type="hidden" name="command" value="VIEW_MESSAGE">
				<input type="submit" value="See message">
			</form>
		</p>
	</c:forEach>
</body>
</html>