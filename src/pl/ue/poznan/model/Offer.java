package pl.ue.poznan.model;

import java.sql.Date;

public class Offer {

	Integer oid;
	String title;
	String description;
	Date dateAdded;
	Float price;
	String users_username;
	Integer categories_cid;
	String myloc;
	String myloc2;
	String myloc3;
	String myloc4;
	String myloc5;
	byte[] imgData;
	String base64Image;
	
	public Offer() {
		super();
	}
	public Offer(Integer oid, String title, String description, Date dateAdded, Float price, String users_username,
			Integer categories_cid) {
		super();
		this.oid = oid;
		this.title = title;
		this.description = description;
		this.dateAdded = dateAdded;
		this.price = price;
		this.users_username = users_username;
		this.categories_cid = categories_cid;
	}
	public Offer(Integer oid, String title, String description, Date dateAdded, Float price, String users_username,
			Integer categories_cid, String myloc, String myloc2, String myloc3,
			String myloc4, String myloc5) {
		super();
		this.oid = oid;
		this.title = title;
		this.description = description;
		this.dateAdded = dateAdded;
		this.price = price;
		this.users_username = users_username;
		this.categories_cid = categories_cid;
		this.myloc = myloc;
		this.myloc2 = myloc2;
		this.myloc3 = myloc3;
		this.myloc4 = myloc4;
		this.myloc5 = myloc5;
	}
	
	//constructor taking byte array as a parameter - displaying an image
	public Offer(Integer oid, String title, String description, Date dateAdded, Float price, String users_username,
			Integer categories_cid, byte[] imgData) {
		super();
		this.oid = oid;
		this.title = title;
		this.description = description;
		this.dateAdded = dateAdded;
		this.price = price;
		this.users_username = users_username;
		this.categories_cid = categories_cid;
		this.imgData = imgData;
	}
	//constructor using string version of byte array (base64)
	public Offer(Integer oid, String title, String description, Date dateAdded, Float price, String users_username,
			Integer categories_cid, String base64Image) {
		super();
		this.oid = oid;
		this.title = title;
		this.description = description;
		this.dateAdded = dateAdded;
		this.price = price;
		this.users_username = users_username;
		this.categories_cid = categories_cid;
		this.base64Image = base64Image;
	}
	public byte[] getImgData() {
		return imgData;
	}
	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getUsers_username() {
		return users_username;
	}
	public void setUsers_username(String users_username) {
		this.users_username = users_username;
	}
	public Integer getCategories_cid() {
		return categories_cid;
	}
	public void setCategories_cid(Integer categories_cid) {
		this.categories_cid = categories_cid;
	}
	public String getMyloc() {
		return myloc;
	}
	public void setMyloc(String myloc) {
		this.myloc = myloc;
	}
	public String getMyloc2() {
		return myloc2;
	}
	public void setMyloc2(String myloc2) {
		this.myloc2 = myloc2;
	}
	public String getMyloc3() {
		return myloc3;
	}
	public void setMyloc3(String myloc3) {
		this.myloc3 = myloc3;
	}
	public String getMyloc4() {
		return myloc4;
	}
	public void setMyloc4(String myloc4) {
		this.myloc4 = myloc4;
	}
	public String getMyloc5() {
		return myloc5;
	}
	public void setMyloc5(String myloc5) {
		this.myloc5 = myloc5;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
}
