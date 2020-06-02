package pl.ue.poznan.dao;

import java.sql.SQLException;
import java.util.List;

import pl.ue.poznan.model.Notification;

public interface NotificationDAO {
	String sendNotification(Notification notification);
	List<Notification> getNotifications(Integer type) throws SQLException;
	void markAsRead(Integer nid);
	Notification getNotificationById(Integer nid);
	void removeNotification(Integer nid);
}
