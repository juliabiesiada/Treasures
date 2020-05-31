package pl.ue.poznan.dao;

import pl.ue.poznan.model.Notification;

public interface NotificationDAO {
	String sendNotification(Notification notification);
}
