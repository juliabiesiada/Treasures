package pl.ue.poznan.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.ue.poznan.dao.UserDAO;
import pl.ue.poznan.dao.UserDAOImpl;
import pl.ue.poznan.model.Extra;
import pl.ue.poznan.model.User;
import pl.ue.poznan.service.UserServiceImpl;

/**
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserServiceImpl usi;

	public UserControllerServlet() {
		usi = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");

			if (!theCommand.isEmpty()) {
				switch (theCommand) {
				case "REGISTER":
					registerUser(request, response);
					break;

				case "LOGIN":
					login(request, response);
					break;

				case "PROFILE":
					getUserProfile(request, response);
					break;
				case "EDIT_PROFILE":
					updateProfile(request, response);
					break;
				case "SAVE_NEW_DATA":
					saveUpdate(request, response);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "";
		Extra extra = new Extra();

		String birthdateStr = request.getParameter("birthdate");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date birthDate;
		try {
			birthDate = sdf.parse(birthdateStr);
			java.sql.Date birthDateSQL = extra.convertDate(birthDate);
			String username = request.getParameter("username").trim();
			String firstname = request.getParameter("firstname").trim();
			String lastname = request.getParameter("lastname").trim();
			String city = request.getParameter("city").trim();
			String roleidvalid = request.getParameter("roleid");
			Integer roleid = null;
			if (!(roleidvalid == null)) {
				roleid = Integer.parseInt(roleidvalid);
			}
			String street = request.getParameter("street").trim();
			String postcode = request.getParameter("postcode").trim();
			String phonenumber = request.getParameter("phonenumber").trim();
			String password = request.getParameter("password").trim();
			String passwordconf = request.getParameter("passwordconf").trim();
			String email = request.getParameter("email").trim();

			User user = new User(username, firstname, lastname, city, birthDateSQL, roleid, street, postcode,
					phonenumber, password, passwordconf, email);
			String error = usi.validateRegistration(user);

			if (error == "") {
				result = usi.addUser(user);
				request.setAttribute("successMessage", "You have successfully registered your account");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			} else {
				request.setAttribute("message", error);
				request.getRequestDispatcher("RegisterUser.jsp").forward(request, response);
				result = error;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		User u = usi.getUser(username, password);
		u = usi.getUser(username, password);
		if (u != null && u.getUsername() != null) {
			request.setAttribute("user", u);
			request.setAttribute("username", u.getUsername());
			request.getRequestDispatcher("Welcome.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "Data not found");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

	private void getUserProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User();
		String username = "";
		String fname;
		String lname;
		String city;
		Integer rid;
		String role;
		username = request.getParameter("username");
		if (username.isEmpty()) {
			request.getRequestDispatcher("Welcome.jsp").forward(request, response);
		}
		user = usi.getUserByUsername(username);
		fname = user.getFirstname();
		lname = user.getLastname();
		city = user.getCity();
		rid = user.getRoleid();

		if (rid == 1) {
			role = "Lender";
		} else {
			role = "Borrower";
		}
		request.setAttribute("user", user);
		request.setAttribute("username", username);
		request.setAttribute("fname", fname);
		request.setAttribute("lname", lname);
		request.setAttribute("city", city);
		request.setAttribute("role", role);
		request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
	}

	private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		User user = usi.getUserByUsername(username);
		if (user!=null) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
		}
		
	}
	
	private void saveUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Extra extra = new Extra();
		String username = request.getParameter("username").trim();
		User user = usi.getUserByUsername(username);
		String fname = request.getParameter("fname").trim();
		String lname = request.getParameter("lname").trim();
		String birthdateStr = request.getParameter("birthdate");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date bdate = sdf.parse(birthdateStr);
		java.sql.Date bdateSQL = extra.convertDate(bdate);
		String city = request.getParameter("city").trim();
		String street = request.getParameter("street").trim();
		String pcode = request.getParameter("postCode").trim();
		String phoneNr = request.getParameter("phoneNr").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		String passwordConf = request.getParameter("passwordConf").trim();
		
		user.setFirstname(fname);
		user.setLastname(lname);
		user.setBirthdate(bdateSQL);
		user.setCity(city);
		user.setStreet(street);
		user.setPostcode(pcode);
		user.setPhonenumber(phoneNr);
		user.setEmail(email);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConf);
		
		String error = usi.validateRegistration(user);
		
		if (error.isEmpty()) {
			usi.updateUser(user);
			request.setAttribute("message", "Your profile has been succesfully updated");
			request.setAttribute("user", user);
			request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
		}else {
			request.setAttribute("message", error);
			request.setAttribute("user", user);
			request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
		}
	}
}
