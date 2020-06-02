package pl.ue.poznan.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.ue.poznan.model.Notification;

public class NotificationDAOImpl implements NotificationDAO {
	
	private DataBaseConnect dataBaseConnect = null;
	private Connection conn = null;
	
	private static final String TABLE_NOTIFICATIONS = "notifications";
	private static final String ALL_COLUMNS = "(NID, \"TYPES_TID\", \"RECIPIENT_USERNAME\",\"SENDER_USERNAME\", \"DATE_SENT\", \"MSG_CONTENT\", \"STATUS\", \"OFFERS_OID\", \"DATE_REQUESTED\")"; 
	private static final String SEND_NOTIFICATION = "INSERT INTO " + TABLE_NOTIFICATIONS + " " +
			ALL_COLUMNS + " VALUES (NOTIFICATIONS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_NOTIFICATIONS = "SELECT * FROM " + TABLE_NOTIFICATIONS +
			" WHERE TYPES_TID = ?";
	private static final String MARK_AS_READ = "UPDATE " + TABLE_NOTIFICATIONS +
			" SET STATUS = 2 WHERE NID = ?";
	private static final String GET_BY_ID = "SELECT * FROM " + TABLE_NOTIFICATIONS +
			" WHERE NID = ?";
	private static final String DELETE = "DELETE FROM " + TABLE_NOTIFICATIONS + 
			" WHERE NID = ?";
			

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
			ps.setInt(1, notification.getTypes_tid());
			ps.setString(2, notification.getRecipientUname());
			ps.setString(3, notification.getSenderUname());
			ps.setDate(4, notification.getDateAdded());
			ps.setString(5, notification.getMsgContent());
			ps.setInt(6, notification.getStatus());
			if (notification.getOffers_oid() == null) {
				ps.setNull(7, java.sql.Types.INTEGER);
			}else {
				ps.setInt(7, notification.getOffers_oid());
			}
			if (notification.getDate_req() == null) {
				ps.setNull(8, java.sql.Types.DATE);
			}else {
				ps.setDate(8, notification.getDate_req());
			}
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Error while sending a message";
		}
		
		return result;
		
	}

	@Override
	public List<Notification> getNotifications(Integer type) throws SQLException {
		List<Notification> notifications = new ArrayList<Notification>();
		Notification tempNot;
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}
		
		PreparedStatement ps = conn.prepareStatement(GET_NOTIFICATIONS);
		ps.setInt(1, type);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			tempNot = new Notification (rs.getInt(1), rs.getInt(2), rs.getString(3),
					rs.getString(4), rs.getDate(5), rs.getString(6), rs.getInt(7),
					rs.getInt(8), rs.getDate(9));
			notifications.add(tempNot);
		}
		return notifications;
	}

	@Override
	public void markAsRead(Integer nid) {
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(MARK_AS_READ);
			ps.setInt(1, nid);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Could not mark as read");
			e.printStackTrace();
		}
		
	}

	@Override
	public Notification getNotificationById(Integer nid) {
		
		Notification notification = null;
		
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(GET_BY_ID);
			ps.setInt(1, nid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				notification = new Notification (rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), rs.getDate(5), rs.getString(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			System.out.println("Could not load notification");
			e.printStackTrace();
		}
		
		return notification;
	}

	@Override
	public void removeNotification(Integer nid) {
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement(DELETE);
			ps.setInt(1, nid);
			ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Deleting failed");
			e.printStackTrace();
		}
		
	}

}
