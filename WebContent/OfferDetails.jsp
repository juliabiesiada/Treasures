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
	<h3>${message}</h3>
	<table>
		<tr>
			<td><%=thisOffer.getTitle()%>
		</tr>
		<tr>
			<td><%=thisOffer.getDescription()%>
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
		<tr>
			<td><img src="data:image/jpg;base64,<%=thisOffer.getBase64Image()%>" width="240" height="300"/>
		</tr>
		<tr>
			<td>
				<form action=UserControllerServlet method="get">
					<input type="hidden" name="command" value="PROFILE">
					<input type="hidden" name="logged_user" value="${logged_user}">
					<input type="hidden" name="username" value="<%=thisOffer.getUsers_username()%>">
					<input type="submit" name="submit" value="See user's profile">
				</form>
			</td>
			<td>
				<form action=NotificationControllerServlet method="get">
					<input type="date" name="date_req">
					<input type="hidden" name="command" value="SEND_REQUEST">
					<input type="hidden" name="logged_user" value="${logged_user}">
					<input type="hidden" name="oid" value="<%=thisOffer.getOid()%>">
					<input type="hidden" name="opo" value="${offer }">
					<input type="hidden" name="username" value="<%=thisOffer.getUsers_username()%>">
					<input type="submit" name="submit" value="Request item">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>