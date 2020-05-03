package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import dao.DataBaseConnect;
import myjava.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {
		
		private DataBaseConnect dataBaseConnect = null;
		private Connection conn = null;
		
		public static final String TABLE_EMPLOYEES = "employees";
		public static final String ALL_COLUMNS = "(\"EMP_ID\",\"FIRST_NAME\",\"LAST_NAME\",\"HIRE_DATE\",\"SALARY\",\"ALLOWANCE\",\"POS_ID\",\"JG_ID\",\"MANAGER_ID\",\"DEP_ID\")";
		public static String QUERY = null;

		
		public EmployeeDAOImpl() {
			dataBaseConnect = new DataBaseConnect(conn);
		}

		public List<Employee> getEmployees() {

			List<Employee> employees = new ArrayList<Employee>();
			QUERY = "SELECT * FROM " + TABLE_EMPLOYEES;
			
			try {
				
				try {
					dataBaseConnect.openConnection();
					conn = dataBaseConnect.getConnection();
				} catch (ClassNotFoundException e) {
					System.out.println("Can't open connection");
					e.printStackTrace();
					return null;
				}
				
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(QUERY);
				
				while(resultSet.next()) {
					employees.add(new Employee(resultSet.getInt(1), resultSet.getString(2), 
							resultSet.getString(3), resultSet.getDate(4), resultSet.getFloat(5),
							resultSet.getFloat(6), resultSet.getInt(7), resultSet.getInt(8),
							resultSet.getInt(9), resultSet.getInt(10)));
				}
				
				stmt.close();
				resultSet.close();
				
			} catch(SQLException ex) {
				ex.printStackTrace();
			} finally {
				dataBaseConnect.closeConnection();
			}
			
			return employees;
		}

		public boolean updateEmployee(Employee employee) {
			// TODO Auto-generated method stub
			return false;
		}

		public int getEmployeeID(Employee employee) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Employee getEmployee(int id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String addEmployee(Employee employee) {
			try {
				dataBaseConnect.openConnection();
				conn = dataBaseConnect.getConnection();
			} catch (ClassNotFoundException e) {
				System.out.println("Can't open connection");
				e.printStackTrace();
			}
			QUERY = "INSERT INTO " + TABLE_EMPLOYEES + " " + ALL_COLUMNS + "VALUES (?,?,?,?,?,?,?,?,?,?)";
			String result = "Data entered succesfully";
			try {
				PreparedStatement ps = conn.prepareStatement(QUERY);
				ps.setInt(1, employee.getId());
				ps.setString(2, employee.getFname());
				ps.setString(3, employee.getLname());
				ps.setDate(4, employee.getHireDate());
				ps.setFloat(5, employee.getSalary());
				ps.setFloat(6, employee.getAllowance());
				ps.setInt(7, employee.getPosID());
				ps.setInt(8, employee.getJgID());
				ps.setInt(9, employee.getManagerID());
				ps.setInt(10, employee.getDepID());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				result = "Data entered incorrectly";
			}
			return result;
		}


	}

	

