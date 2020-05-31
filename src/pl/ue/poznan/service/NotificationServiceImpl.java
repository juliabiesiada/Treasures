package pl.ue.poznan.service;

import pl.ue.poznan.dao.NotificationDAOImpl;
import pl.ue.poznan.model.Notification;

public class NotificationServiceImpl implements NotificationService{

	@Override
	public String sendNotification(Notification notification) {
		String result = "";
		NotificationDAOImpl ndi = new NotificationDAOImpl();
		result = ndi.sendNotification(notification);
		return result;
	}

}
