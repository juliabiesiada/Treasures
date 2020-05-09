package pl.ue.poznan.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.ue.poznan.dao.UserDAO;
import pl.ue.poznan.dao.UserDAOImpl;
import pl.ue.poznan.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO ud = new UserDAOImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = ud.getUser(username, password);
		u = ud.getUser(username, password);
		if(u!=null && u.getUsername()!=null) {
			request.setAttribute("message", u.getUsername());
			request.getRequestDispatcher("Welcome.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "Data not found");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		
		
//		UserDAOImpl u = new UserDAOImpl();
//		String result = u.getUser(user2);
//		response.getWriter();
//		System.out.println(result);
		

	}

}
