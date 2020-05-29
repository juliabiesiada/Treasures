package pl.ue.poznan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAOImpl implements CategoryDAO {

	private DataBaseConnect dataBaseConnect = null;
	private Connection conn = null;

	private final String GET_CATEGORY_NAME = "SELECT NAME FROM CATEGORIES WHERE CID = ?";

	public CategoryDAOImpl() {
		dataBaseConnect = new DataBaseConnect(conn);
	}
	
	
	public String getCategoryById(Integer cid) {
		String name = "";
		ResultSet rs;
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}

		try {
			PreparedStatement ps = conn.prepareStatement(GET_CATEGORY_NAME);

			ps.setInt(1, cid);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				name = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			name = "ERROR";
		}
		return name;
	}

}
