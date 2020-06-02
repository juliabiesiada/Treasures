<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message details</title>
</head>
<body>
<p>${sender}</p>
<p>${dateSent}</p>
<p>${msgContent}</p>
<p><form action=NotificationControllerServlet method="get">
		<input type="hidden" name="sender" value="${sender}">
		<input type="hidden" name="recipient" value="${logged_user}">
		<input type="hidden" name="notif_id" value="${nid}">
		<input type="hidden" name="command" value="REPLY_TO_MSG">
		<input type="submit" value="Reply">
  </form>
</p>
</body>
</html>