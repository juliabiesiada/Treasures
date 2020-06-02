package pl.ue.poznan.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<Notification> getNotifications(Integer type) throws SQLException {
		List<Notification> notifications = new ArrayList<Notification>();
		NotificationDAOImpl ndi = new NotificationDAOImpl();
		notifications = ndi.getNotifications(type);
		return notifications;
	}

	@Override
	public List<Notification> getUserNotifications(String username, Integer type) throws SQLException {
		List<Notification> notifications = new ArrayList<Notification>();
		List<Notification> usersNot = new ArrayList<Notification>();
		NotificationDAOImpl ndi = new NotificationDAOImpl();
		notifications = ndi.getNotifications(type);
		String tempUname;
		
		for (int i = 0; i < notifications.size(); i++) {
			tempUname = notifications.get(i).getRecipientUname();
			if (tempUname.equals(username)) {
				usersNot.add(notifications.get(i));
			}
		}
		return usersNot;
	}

	@Override
	public void markAsRead(Integer nid) {
		NotificationDAOImpl ndi = new NotificationDAOImpl();
		ndi.markAsRead(nid);
		
	}

	@Override
	public Notification getNotificationById(Integer nid) {
		NotificationDAOImpl ndi = new NotificationDAOImpl();
		Notification notification = ndi.getNotificationById(nid);
		return notification;
	}

	@Override
	public void removeNotification(Integer nid) {
		NotificationDAOImpl ndi = new NotificationDAOImpl();
		ndi.removeNotification(nid);
		
	}

}
