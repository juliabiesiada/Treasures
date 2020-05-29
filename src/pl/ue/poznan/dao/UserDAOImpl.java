package pl.ue.poznan.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import pl.ue.poznan.dao.DataBaseConnect;
import pl.ue.poznan.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

	private DataBaseConnect dataBaseConnect = null;
	private Connection conn = null;

	public static final String TABLE_USERS = "users";
	public static final String ALL_COLUMNS = "(\"uid\",\"USERNAME\",\"FIRST_NAME\",\"LAST_NAME\",\"CITY\",\"BIRTH_DATE\",\"ROLES_RID\",\"STREET\",\"POSTCODE\",\"PHONE_NUMBER\",\"PASSWORD\", \"EMAIL\")";
	public static final String INSERT_USER = "INSERT INTO " + TABLE_USERS + " " + ALL_COLUMNS
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_ALL_USERS = "SELECT * FROM " + TABLE_USERS;
	public static final String LOGIN_USER = "SELECT * FROM " + TABLE_USERS + " WHERE USERNAME=? AND PASSWORD=?";
	public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM " + TABLE_USERS + " WHERE username = ?";
	public static final String UPDATE_USER = "UPDATE " + TABLE_USERS
			+ " SET USERNAME = ?, FIRST_NAME = ?, LAST_NAME = ?, CITY = ?, BIRTH_DATE = ?, ROLES_RID = ?, STREET = ?, POSTCODE = ?, PHONE_NUMBER = ?, PASSWORD = ?, EMAIL = ? WHERE USERNAME = ?";
	public static final String DELETE_USER = "DELETE FROM " + TABLE_USERS + " WHERE ID = ?";
	public static String QUERY = null;

	public UserDAOImpl() {
		dataBaseConnect = new DataBaseConnect(conn);
	}

	@Override
	public User getUser(String username, String password) {
		User u = new User();
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}

		try {

			PreparedStatement ps = conn.prepareStatement(LOGIN_USER);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setFirstname(rs.getString(3));
				u.setLastname(rs.getString(4));
				u.setCity(rs.getString(5));
				u.setBirthdate(rs.getDate(9));
				u.setRoleid(rs.getInt(11));
				u.setStreet(rs.getString(6));
				u.setPostcode(rs.getString(7));
				u.setPhonenumber(rs.getString(8));
				u.setPassword(rs.getString(10));
				u.setEmail(rs.getString(12));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return u;
	}

	public User getUserByUsername(String username) {
		User u = new User();
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}

		try {

			PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_USERNAME);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setFirstname(rs.getString(3));
				u.setLastname(rs.getString(4));
				u.setCity(rs.getString(5));
				u.setBirthdate(rs.getDate(9));
				u.setRoleid(rs.getInt(11));
				u.setStreet(rs.getString(6));
				u.setPostcode(rs.getString(7));
				u.setPhonenumber(rs.getString(8));
				u.setPassword(rs.getString(10));
				u.setEmail(rs.getString(12));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return u;
	}

	@Override
	public String addUser(User user) {
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}
		QUERY = "INSERT INTO " + TABLE_USERS + " " + ALL_COLUMNS
				+ "VALUES ((USER_ID_SEQ.NEXTVAL),?,?,?,?,?,?,?,?,?,?,?)";
		String result = "Data entered succesfully";
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getCity());
			ps.setDate(5, user.getBirthdate());
			ps.setInt(6, user.getRoleid());
			ps.setString(7, user.getStreet());
			ps.setString(8, user.getPostcode());
			ps.setString(9, user.getPhonenumber());
			ps.setString(10, user.getPassword());
			ps.setString(11, user.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Data entered incorrectly";
		}
		return result;
	}
	
	@Override
	public String updateUser(User user) {
		String result = "Your profile was updated";
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE_USER);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getCity());
			ps.setDate(5, user.getBirthdate());
			ps.setInt(6, user.getRoleid());
			ps.setString(7, user.getStreet());
			ps.setString(8, user.getPostcode());
			ps.setString(9, user.getPhonenumber());
			ps.setString(10, user.getPassword());
			ps.setString(11, user.getEmail());
			ps.setString(12, user.getUsername());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Update failed";
		}
		return result;
	}
}
