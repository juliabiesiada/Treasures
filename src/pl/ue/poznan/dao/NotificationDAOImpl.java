package pl.ue.poznan.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pl.ue.poznan.model.Notification;

public class NotificationDAOImpl implements NotificationDAO {
	
	private DataBaseConnect dataBaseConnect = null;
	private Connection conn = null;
	
	private static final String TABLE_NOTIFICATIONS = "notifications";
	private static final String ALL_COLUMNS = "(NID, \"TYPES_TID\", \"RECIPIENT_USERNAME\",\"SENDER_USERNAME\", \"DATE_SENT\", \"MSG_CONTENT\", \"STATUS\")"; 
	private static final String SEND_NOTIFICATION = "INSERT INTO " + TABLE_NOTIFICATIONS + " " +
			ALL_COLUMNS + " VALUES (NOTIFICATIONS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			

	public NotificationDAOImpl() {
		dataBaseConnect = new DataBaseConnect(conn);
	}

	@Override
	public String sendNotification(Notification notification) {
		
		String result = "";
		
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(SEND_NOTIFICATION);
			ps.setInt(1, 1);
			ps.setString(2, notification.getRecipientUname());
			ps.setString(3, notification.getSenderUname());
			ps.setDate(4, notification.getDateAdded());
			ps.setString(5, notification.getMsgContent());
			ps.setInt(6, notification.getStatus());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Error while sending a message";
		}
		
		return result;
		
	}

}
