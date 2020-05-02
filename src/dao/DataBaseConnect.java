package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnect {
	
	private static String CONN_URL = "jdbc:oracle:thin:@kti-lab1.ue.poznan.pl:1521/venusdb.ue.poznan";
	private static String USER = "wap02";
	private static String PASS = "wap32d"; 
	
	private Connection conn;

	public DataBaseConnect(Connection conn) {
		this.conn = conn;
	}
	
	public Connection openConnection () throws ClassNotFoundException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.conn = DriverManager.getConnection(CONN_URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("=======> Can't open connection to database <=========");
			e.printStackTrace();
		}
		return conn;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public void closeConnection() {
		
		if (conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				System.out.println("=======> Can't close connection to database <=========");
				e.printStackTrace();
			}
		}
			
	}

}
