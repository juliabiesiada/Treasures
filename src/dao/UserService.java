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
import myjava.User;

public class UserService implements UserDAO {
	
	private DataBaseConnect dataBaseConnect = null;
	private Connection conn = null;
	
	public static final String TABLE_USERS = "users";
	public static final String ALL_COLUMNS = "(\"uid\",\"USERNAME\",\"FIRST_NAME\",\"LAST_NAME\",\"CITY\",\"BIRTH_DATE\",\"ROLES_RID\",\"STREET\",\"POSTCODE\",\"PHONE_NUMBER\",\"PASSWORD\")";
	public static String QUERY = null;
	
	public UserService() {
		dataBaseConnect = new DataBaseConnect(conn);
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		QUERY = "SELECT * FROM " + TABLE_USERS;
		
try {
			
			try {
				conn = dataBaseConnect.openConnection();
			} catch (ClassNotFoundException e) {
				System.out.println("Can't open connection");
				e.printStackTrace();
				return null;
			}
			
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(QUERY);
			
			while(resultSet.next()) {
				users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getDate(6), resultSet.getInt(7), resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11)));
			}
			
			stmt.close();
			resultSet.close();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			dataBaseConnect.closeConnection();
		}
		
		return users;
	}
	
	@Override
	public String addUser(User user) {
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}
		QUERY = "INSERT INTO " + TABLE_USERS + " " + ALL_COLUMNS + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		String result = "Data entered succesfully";
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getCity());
			ps.setDate(6, user.getBirthdate());
			ps.setInt(7, user.getRoleid());
			ps.setString(8, user.getStreet());
			ps.setString(9, user.getPostcode());
			ps.setString(10, user.getPhonenumber());
			ps.setString(11, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Data entered incorrectly";
		}
		return result;
	}
	

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getUserID(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
	


}
