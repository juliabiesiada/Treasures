package pl.ue.poznan.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import pl.ue.poznan.model.Offer;

public class OfferDAOImpl implements OfferDAO {

	private DataBaseConnect dataBaseConnect = null;
	private Connection conn = null;

	private static final String TABLE_OFFERS = "offers";
	private static final String TABLE_USERS = "users";
	private static final String ALL_COLUMNS = "(oid, \"TITLE\", \"DESCRIPTION\",\"DATA_ADDED\", \"PRICE\", \"USERS_USERNAME\", \"CATEGORIES_CID\", \"PICTURE1\", \"PICTURE2\", \"PICTURE3\", \"PICTURE4\", \"PICTURE5\")";
	private static final String INSERT_QUERY = "INSERT INTO " + TABLE_OFFERS + " " + ALL_COLUMNS
			+ " VALUES ((OFFER_ID_SEQ.NEXTVAL), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ALL_OFFERS = "SELECT * FROM " + TABLE_OFFERS;
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_OFFERS + 
			" WHERE OID = ?";
	private static final String COLS_OFFER_SHOW = "o.oid, o.title, o.description, o.data_added, o.price, o.categories_cid, o.picture1, o.users_username";
	private static final String SELECT_3_FROM_CITY = "SELECT " + COLS_OFFER_SHOW + 
			" FROM " + TABLE_OFFERS + " o LEFT JOIN " + TABLE_USERS + 
			" u ON  o.users_username = u.username " +
			"WHERE NOT username = ? " +
			"AND ROWNUM <= 3 " + 
			"AND u.city = ? " + 
			"ORDER BY o.data_added";

	public OfferDAOImpl() {
		dataBaseConnect = new DataBaseConnect(conn);
	}

	@Override
	public Offer getOfferById(Integer id) throws IOException {
		
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
				Blob image = resultSet.getBlob("PICTURE1");
				InputStream inputStream = image.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
				offer = new Offer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getFloat(5), resultSet.getString(12), resultSet.getInt(6), base64Image);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return offer;
	}

	@Override
	public String addOffer(Offer offer) throws FileNotFoundException {
		String result = "";
		FileInputStream fis;
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
			ps.setNull(7, java.sql.Types.BLOB);
			ps.setNull(8, java.sql.Types.BLOB);
			ps.setNull(9, java.sql.Types.BLOB);
			ps.setNull(10, java.sql.Types.BLOB);
			ps.setNull(11, java.sql.Types.BLOB);
			
			//inserting images
			if (!offer.getMyloc().isEmpty()) {
				File image= new File(offer.getMyloc());
				fis=new FileInputStream(image);
				ps.setBinaryStream(7, (InputStream) fis, (int) (image.length()));
			}
			if (!offer.getMyloc2().isEmpty()) {
				File image2= new File(offer.getMyloc());
				fis=new FileInputStream(image2);
				ps.setBinaryStream(8, (InputStream) fis, (int) (image2.length()));
			}
			if (!offer.getMyloc3().isEmpty()) {
				File image3= new File(offer.getMyloc());
				fis=new FileInputStream(image3);
				ps.setBinaryStream(9, (InputStream) fis, (int) (image3.length()));
			}
			if (!offer.getMyloc4().isEmpty()) {
				File image4= new File(offer.getMyloc());
				fis=new FileInputStream(image4);
				ps.setBinaryStream(10, (InputStream) fis, (int) (image4.length()));
			}
			if (!offer.getMyloc5().isEmpty()) {
				File image5= new File(offer.getMyloc());
				fis=new FileInputStream(image5);
				ps.setBinaryStream(11, (InputStream) fis, (int) (image5.length()));
			}
			
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
	public List<Offer> getOffers() throws IOException {
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
				Blob image = resultSet.getBlob("PICTURE1");
				String base64Image = "";
				if (image != null) {
					InputStream inputStream = image.getBinaryStream();
	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                byte[] buffer = new byte[4096];
	                int bytesRead = -1;
	                 
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);                  
	                }
	                 
	                byte[] imageBytes = outputStream.toByteArray();
	                base64Image = Base64.getEncoder().encodeToString(imageBytes);
	                
	                inputStream.close();
	                outputStream.close();
				}
				offers.add(new Offer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getFloat(5), resultSet.getString(12), resultSet.getInt(6), base64Image));
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

	@Override
	public List<Offer> getNewestOffers(String city, String username) throws IOException {
		List<Offer> offers = new ArrayList<Offer>();
		ResultSet resultSet;
		try {
			try {
				conn = dataBaseConnect.openConnection();
			} catch (ClassNotFoundException e) {
				System.out.println("Can't open connection");
				e.printStackTrace();
			}

			PreparedStatement ps = conn.prepareStatement(SELECT_3_FROM_CITY);
			
			ps.setString(1, username);
			ps.setString(2, city);
			
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Blob image = resultSet.getBlob("PICTURE1");
				String base64Image = "";
				if (image != null) {
				InputStream inputStream = image.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
				}
				offers.add(new Offer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getFloat(5), resultSet.getString(8), resultSet.getInt(6), base64Image));
			}

			ps.close();
			resultSet.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			dataBaseConnect.closeConnection();
		}

		return offers;
	}

}
