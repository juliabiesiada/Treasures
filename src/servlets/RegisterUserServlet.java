package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserService;

import myjava.User;

@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String birthdateStr = request.getParameter("birthdate");
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date birthDate;
		try {
			birthDate = sdf.parse(birthdateStr);
			java.sql.Date birthDateSQL = new java.sql.Date(birthDate.getTime());
			Integer id = Integer.parseInt(request.getParameter("id"));
			String username = request.getParameter("username");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String city = request.getParameter("city");
			Integer roleid = Integer.parseInt(request.getParameter("roleid"));
			String street = request.getParameter("street");
			String postcode = request.getParameter("postcode");
			String phonenumber = request.getParameter("phonenumber");
			String password = request.getParameter("password");
			
			
			
			User user = new User(id, username, firstname, lastname, city, birthDateSQL, roleid, 
					street, postcode, phonenumber, password);
			UserService edi = new UserService();
			String result = edi.addUser(user);
			
			response.getWriter();
			System.out.println(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}


}
