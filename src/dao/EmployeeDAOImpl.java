package dao;

import java.sql.Connection;
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
		public static final String QUERY_ALL_EMPLOYEES = "SELECT * FROM " + TABLE_EMPLOYEES;

		
		public EmployeeDAOImpl() {
			dataBaseConnect = new DataBaseConnect(conn);
		}

		public List<Employee> getEmployees() {

			List<Employee> employees = new ArrayList<Employee>();
			
			try {
				
				try {
					dataBaseConnect.openConnection();
					System.out.println("opened");
					conn = dataBaseConnect.getConnection();
				} catch (ClassNotFoundException e) {
					System.out.println("Can't open connection");
					e.printStackTrace();
					return null;
				}
				
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(QUERY_ALL_EMPLOYEES);
				
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
		public boolean addEmployee(Employee employee) {
			// TODO Auto-generated method stub
			return false;
		}


	}

	

