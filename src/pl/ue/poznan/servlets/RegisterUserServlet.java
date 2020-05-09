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

import pl.ue.poznan.model.User;
import pl.ue.poznan.service.UserServiceImpl;

@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String result = "";
		
		String birthdateStr = request.getParameter("birthdate");
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date birthDate;
		try {
			birthDate = sdf.parse(birthdateStr);
			java.sql.Date birthDateSQL = new java.sql.Date(birthDate.getTime());
			String username = request.getParameter("username");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String city = request.getParameter("city");
			String roleidvalid = request.getParameter("roleid");
			Integer roleid = null;
			if (!(roleidvalid == null)) {
				roleid = Integer.parseInt(roleidvalid);
			}
			String street = request.getParameter("street");
			String postcode = request.getParameter("postcode");
			String phonenumber = request.getParameter("phonenumber");
			String password = request.getParameter("password");
			String passwordconf = request.getParameter("passwordconf");
			String email = request.getParameter("email");
			
			
		
			User user = new User(username, firstname, lastname, city, birthDateSQL, roleid, 
					street, postcode, phonenumber, password, passwordconf, email);
			UserServiceImpl usi = new UserServiceImpl();
			String error = usi.validateRegistration(user);
			
			if (error == "") {
				result = usi.addUser(user);
				request.setAttribute("successMessage", "You have successfully registered your account");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}else {
				request.setAttribute("message", error);
				request.getRequestDispatcher("RegisterUser.jsp").forward(request, response);
				result = error;
			}
			response.getWriter();
			System.out.println(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}


}
