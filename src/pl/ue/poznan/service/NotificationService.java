package pl.ue.poznan.service;

import pl.ue.poznan.model.Notification;

public interface NotificationService {
	String sendNotification(Notification notification);
}
