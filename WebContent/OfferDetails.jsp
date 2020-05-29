<%@ page import="java.util.*, pl.ue.poznan.model.*" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Offer</title>
</head>
	<% 
		OfferPresentationObject thisOffer = (OfferPresentationObject) request.getAttribute("offer");
	%>
<body>
	<table>
		<tr>
			<td><%=thisOffer.getTitle()%>
		</tr>
		<tr>
			<td><%=thisOffer.getCategory_name()%>
		</tr>
		<tr>
			<td><%=thisOffer.getDateAdded()%>
		</tr>
		<tr>
			<td><%=thisOffer.getPrice()%>
		</tr>
	</table>
</body>
</html>