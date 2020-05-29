package pl.ue.poznan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.ue.poznan.model.Offer;

public class OfferDAOImpl implements OfferDAO {

	private DataBaseConnect dataBaseConnect = null;
	private Connection conn = null;

	private static final String TABLE_OFFERS = "offers";
	private static final String ALL_COLUMNS = "(oid, \"TITLE\", \"DESCRIPTION\",\"DATA_ADDED\", \"PRICE\", \"USERS_USERNAME\", \"CATEGORIES_CID\")";
	private static final String INSERT_QUERY = "INSERT INTO " + TABLE_OFFERS + " " + ALL_COLUMNS
			+ " VALUES ((OFFER_ID_SEQ.NEXTVAL), ?, ?, ?, ?, ?, ?)";
	private static final String ALL_OFFERS = "SELECT * FROM " + TABLE_OFFERS;
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_OFFERS + 
			" WHERE OID = ?";

	public OfferDAOImpl() {
		dataBaseConnect = new DataBaseConnect(conn);
	}

	@Override
	public Offer getOfferById(Integer id) {
		
		Offer offer = null;
		
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				offer = new Offer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getFloat(5), resultSet.getString(12), resultSet.getInt(6));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return offer;
	}

	@Override
	public String addOffer(Offer offer) {
		String result = "";
		try {
			conn = dataBaseConnect.openConnection();
		} catch (ClassNotFoundException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}

		try {
			PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);

			ps.setString(1, offer.getTitle());
			ps.setString(2, offer.getDescription());
			ps.setDate(3, offer.getDateAdded());
			ps.setFloat(4, offer.getPrice());
			ps.setString(5, offer.getUsers_username());
			ps.setInt(6, offer.getCategories_cid());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			result = "Adding failed";
		}
		return result;
	}

	@Override
	public String updateOfer(Offer offer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> getOffers() {
		List<Offer> offers = new ArrayList<Offer>();
		try {
			try {
				conn = dataBaseConnect.openConnection();
			} catch (ClassNotFoundException e) {
				System.out.println("Can't open connection");
				e.printStackTrace();
			}

			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(ALL_OFFERS);

			while (resultSet.next()) {
				offers.add(new Offer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getFloat(5), resultSet.getString(12), resultSet.getInt(6)));
			}

			stmt.close();
			resultSet.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			dataBaseConnect.closeConnection();
		}

		return offers;
	}

}
