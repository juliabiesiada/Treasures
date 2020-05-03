package servlets;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import dao.EmployeeDAOImpl;
import myjava.Employee;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hireDateStr = request.getParameter("hiredate");
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date hireDate;
		try {
			hireDate = sdf.parse(hireDateStr);
			java.sql.Date hireDateSQL = new java.sql.Date(hireDate.getTime());
			Integer id = Integer.parseInt(request.getParameter("id"));
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			Float salary = Float.parseFloat(request.getParameter("salary"));
			Float allowance = Float.parseFloat(request.getParameter("allowance"));
			Integer posID = Integer.parseInt(request.getParameter("posID"));
			Integer jgID = Integer.parseInt(request.getParameter("jgID"));
			Integer managerID = Integer.parseInt(request.getParameter("manID"));
			
			Integer depID = Integer.parseInt(request.getParameter("depID"));
			
			Employee employee = new Employee(id, fname, lname, hireDateSQL, salary, allowance, posID, jgID, managerID, depID);
			EmployeeDAOImpl edi = new EmployeeDAOImpl();
			String result = edi.addEmployee(employee);
			
			response.getWriter();
			System.out.println(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}

}
