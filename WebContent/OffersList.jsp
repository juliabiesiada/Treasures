<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My offers</title>
</head>
<body>
	<c:forEach items = "${offers}" var = "offer">
		<p>${offer.title}</p>
		<p>${offer.dateAdded}</p>
		<p>${offer.price}</p>
		<p>${offer.category_name}</p>
		<p><form action=OfferControllerServlet method="get">
				<input type="hidden" name="offer_id" value=${offer.oid }>
				<input type="hidden" name="command" value="OFFER_DETAILS">
				<input type="submit" value="See details">
			</form>
		</p>
	</c:forEach>
</body>
</html>