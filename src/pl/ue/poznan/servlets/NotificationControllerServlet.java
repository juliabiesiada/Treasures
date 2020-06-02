package pl.ue.poznan.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.ue.poznan.model.User;

import pl.ue.poznan.model.Extra;
import pl.ue.poznan.model.Notification;
import pl.ue.poznan.model.Offer;
import pl.ue.poznan.model.OfferPresentationObject;
import pl.ue.poznan.service.CategoryServiceImpl;
import pl.ue.poznan.service.NotificationServiceImpl;
import pl.ue.poznan.service.OfferServiceImpl;
import pl.ue.poznan.service.UserServiceImpl;

@WebServlet("/NotificationControllerServlet")
public class NotificationControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	NotificationServiceImpl nsi;
	UserServiceImpl usi;
	
    public NotificationControllerServlet() {
        nsi = new NotificationServiceImpl();
        usi = new UserServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");

			if (!theCommand.isEmpty()) {
				switch (theCommand) {
			
				case "SEND_MESSAGE_FORM":
					sendMessageForm(request, response);
					break;
					
				case "SEND_MESSAGE":
					sendMessage(request, response);
					break;
				case "GET_MY_MESSAGES":
					getMyMessages(request, response);
					break;
				case "VIEW_MESSAGE":
					viewMessage(request, response);
					break;
				case "REPLY_TO_MSG":
					replyToMsg(request, response);
					break;
				case "SEND_REQUEST":
					requestItem(request, response);
					break;
				case "GET_MY_REQUESTS":
					getMyRequests(request, response);
					break;
				case "DENY_REQUEST":
					denyRequest(request, response);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void sendMessageForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String senderUname = request.getParameter("logged_user");
		String recipientUname = request.getParameter("username");
		
		request.setAttribute("sender", senderUname);
		request.setAttribute("recipient", recipientUname);
		
		request.getRequestDispatcher("SendMessage.jsp").forward(request, response);
	}

	private void sendMessage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Extra extra = new Extra();
		String result;
		String senderUname = request.getParameter("sender");
		String recipientUname = request.getParameter("recipient");
		String msg = request.getParameter("msg"); 
		Notification notification;
		Date date = new Date();
		
		//data to pass to profile if msg is sent
		User user = usi.getUserByUsername(recipientUname);
		String username = user.getUsername();
		String fname = user.getFirstname();
		String lname = user.getLastname();
		String city = user.getCity();
		Integer rid = user.getRoleid();
		String role;

		if (rid == 1) {
			role = "Lender";
		} else {
			role = "Borrower";
		}
		
		java.sql.Date sqlDate = extra.convertDate(date);
		
		if (msg.isEmpty()) {
			request.setAttribute("sender", senderUname);
			request.setAttribute("recipient", recipientUname);
			request.setAttribute("message", "Message cannot be empty!");
			request.getRequestDispatcher("SendMessage.jsp").forward(request, response);
		}
		
		notification = new Notification(1, 1, recipientUname, senderUname, sqlDate, msg, 1, null, null);
		
		result = nsi.sendNotification(notification);
		
		if (result.equals("")) {
			request.setAttribute("user", user);
			request.setAttribute("logged_user", senderUname);
			request.setAttribute("username", username);
			request.setAttribute("fname", fname);
			request.setAttribute("lname", lname);
			request.setAttribute("city", city);
			request.setAttribute("role", role);
			request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
		}else {
			request.setAttribute("sender", senderUname);
			request.setAttribute("recipient", recipientUname);
			request.setAttribute("message", result);
			request.getRequestDispatcher("SendMessage.jsp").forward(request, response);
		}
		
	}

	private void getMyMessages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loggedUser = request.getParameter("logged_user");
		List<Notification> messages = new ArrayList<Notification>();
		messages = nsi.getUserNotifications(loggedUser, 1);
		String message = "";
		Integer newMsg = 0;
		Integer code;
		
		if (messages.size() == 0) {
			message = "Your inbox is empty";
		}
		//counting unread messages
		for (int i = 0; i < messages.size(); i++) {
			code = messages.get(i).getStatus();
			if (code != 2) {
				newMsg = newMsg + 1;
			}
		}
		
		switch (newMsg) {
			case 0:
				message = "You have no new messages";
				break;
			case 1:
				message = "You have one new message";
				break;
			default:
				message = "You have " + newMsg + " new messages";
		}
		
		request.setAttribute("message", message);
		request.setAttribute("messages", messages);
		request.setAttribute("logged_user", loggedUser);
		request.getRequestDispatcher("MyMessages.jsp").forward(request, response);
		
	}
	
	private void viewMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loggedUser = request.getParameter("logged_user");
		Integer nid = Integer.parseInt(request.getParameter("notif_id"));
		
		nsi.markAsRead(nid);
		Notification message = nsi.getNotificationById(nid);
		
		request.setAttribute("logged_user", loggedUser);
		request.setAttribute("sender", message.getSenderUname());
		request.setAttribute("dateSent", message.getDateAdded());
		request.setAttribute("msgContent", message.getMsgContent());
		request.getRequestDispatcher("MessageDetails.jsp").forward(request, response);
	}
	

	private void replyToMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String senderUname = request.getParameter("sender");
		String recipientUname = request.getParameter("recipient");
		
		request.setAttribute("logged_user", recipientUname);
		request.setAttribute("sender", recipientUname);
		request.setAttribute("recipient", senderUname);
		
		request.getRequestDispatcher("SendMessage.jsp").forward(request, response);
		
	}
	

	private void requestItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Extra extra = new Extra();
		
		String loggedUser = request.getParameter("logged_user");
		String seller = request.getParameter("username");
		String msg = "User " + loggedUser + " requested to borrow your item";
		Integer oid = Integer.parseInt(request.getParameter("oid"));
		String dateStr = request.getParameter("date_req");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date uDate;
		Date uToday = new Date();
		java.sql.Date sDate = null;
		java.sql.Date sToday = extra.convertDate(uToday);
		try {
			uDate = sdf.parse(dateStr);
			sDate = extra.convertDate(uDate);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		Notification notification = new Notification (1, 2, seller, loggedUser, sToday, msg, 1, oid, sDate);
			
		String message = nsi.sendNotification(notification);
		if (message.equals("")) {
			message = "You succesfully requested this item";
		}
		
		//offer data to pass back to the page
		OfferServiceImpl osi = new OfferServiceImpl();
		Offer offer = osi.getOfferById(oid);
		CategoryServiceImpl csi = new CategoryServiceImpl();
		
		OfferPresentationObject opo = new OfferPresentationObject(oid,
				offer.getTitle(), offer.getDescription(), offer.getDateAdded(),
				offer.getPrice(), offer.getUsers_username(), 
				csi.getCategoryById(offer.getCategories_cid()), offer.getBase64Image());
		
		request.setAttribute("message", message);
		request.setAttribute("offer", opo);
		request.setAttribute("logged_user", loggedUser);
		request.getRequestDispatcher("OfferDetails.jsp").forward(request, response);
	}

	private void getMyRequests(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String message = "";
		String loggedUser = request.getParameter("logged_user");
		List<Notification> requests = new ArrayList<Notification>();
		
		requests = nsi.getUserNotifications(loggedUser, 2);
		
		//getting titles of requested offers
		Notification tempReq;
		List<String> titles = new ArrayList<String>();
		Integer tempOid;
		Offer tempOffer;
		OfferServiceImpl osi = new OfferServiceImpl();
		for (int i = 0; i < requests.size(); i++) {
			tempReq = requests.get(i);
			tempOid = tempReq.getOffers_oid();
			tempOffer = osi.getOfferById(tempOid);
			tempReq.setRequestedOffer(tempOffer.getTitle());
		}
		if (requests.size() == 0) {
			message = "You have no requests";
		}
		// displaying msg about new requests
		Integer code;
		Integer newReq = 0;
		for (int i = 0; i < requests.size(); i++) {
			code = requests.get(i).getStatus();
			if (code != 2) {
				newReq = newReq + 1;
				//set as read after the page is viewed
				requests.get(i).setStatus(2);
			}
		}
		
		switch (newReq) {
			case 0:
				message = "You have no new requests";
				break;
			case 1:
				message = "You have one new request";
				break;
			default:
				message = "You have " + newReq + " new requests";
		}
		
		request.setAttribute("message", message);
		request.setAttribute("titles", titles);
		request.setAttribute("requests", requests);
		request.setAttribute("logged_user", loggedUser);
		request.getRequestDispatcher("MyRequests.jsp").forward(request, response);
	}

	private void denyRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loggedUser = request.getParameter("logged_user");
		String sender = request.getParameter("sender");
		Integer nid = Integer.parseInt(request.getParameter("notif_id"));
		String title = request.getParameter("title");
		
		Date uToday = new Date();
		Extra extra = new Extra();
		java.sql.Date sToday = extra.convertDate(uToday);
		String content = "User " + loggedUser + " denied your request to borrow " + title;
		
		nsi.removeNotification(nid);
		//informing the user about rejection
		Notification rejectMsg = new Notification(1, 1, sender, loggedUser, sToday, content, 1, null, null);
		nsi.sendNotification(rejectMsg);
		
		getMyRequests(request, response);
	}
}
