package pl.ue.poznan.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.ue.poznan.model.User;

import pl.ue.poznan.model.Extra;
import pl.ue.poznan.model.Notification;
import pl.ue.poznan.service.NotificationServiceImpl;
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
		
		notification = new Notification(1, recipientUname, senderUname, sqlDate, msg, 1);
		
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

}
