<%@ page import="java.sql.*, java.io.*, java.util.*, pl.ue.poznan.model.*" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My requests</title>
</head>
<body>
	<h3>${message}</h3>
	<c:forEach items = "${requests}" var = "request">
		<p>User ${request.senderUname}</p>
		<p>requested to borrow your item ${request.requestedOffer }</p>
		<p>for ${request.date_req}</p>
		<p><form action=NotificationControllerServlet method="get">
				<input type="hidden" name="logged_user" value=${request.recipientUname }>
				<input type="hidden" name="notif_id" value=${request.nid }>
				<input type="hidden" name="oid" value=${request.offers_oid }>
				<input type="hidden" name="command" value="ACCEPT_REQUEST">
				<input type="submit" value="Accept">
			</form>
		</p>
		<p><form action=NotificationControllerServlet method="get">
				<input type="hidden" name="logged_user" value=${request.recipientUname }>
				<input type="hidden" name="sender" value=${request.senderUname }>
				<input type="hidden" name="notif_id" value=${request.nid }>
				<input type="hidden" name="title" value=${request.requestedOffer }">
				<input type="hidden" name="command" value="DENY_REQUEST">
				<input type="submit" value="Deny">
			</form>
		</p>
	</c:forEach>
</body>
</html>