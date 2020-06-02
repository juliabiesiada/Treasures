package pl.ue.poznan.service;

import java.sql.SQLException;
import java.util.List;

import pl.ue.poznan.model.Notification;

public interface NotificationService {
	String sendNotification(Notification notification);
	List<Notification> getNotifications(Integer type) throws SQLException;
	List<Notification> getUserNotifications(String username, Integer type) throws SQLException;
	void markAsRead(Integer nid);
	Notification getNotificationById(Integer nid);
	void removeNotification(Integer nid);
}
