<%@ page import="java.util.*, pl.ue.poznan.model.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Send message</title>
</head>
<body>
	<form action="NotificationControllerServlet" method="get">
		<input type="hidden" name="command" value="SEND_MESSAGE">
		<input type="hidden" name="recipient" value="${recipient}">
		<input type="hidden" name="sender" value="${sender}">
		<table>
			<tr>
				<td>
					<h3 style="color: red;">${message}</h3>
				</td>
			</tr>
			<tr>
				<td>Message (max. 300 char):</td>
				<td><input type="text" name="msg"></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Send message"></td>
			</tr>
		</table>
	</form>	
</body>
</html>