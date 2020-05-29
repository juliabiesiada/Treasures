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
	public void setUsers_uid(String users_username) {
		this.users_username = users_username;
	}
	public Integer getCategories_cid() {
		return categories_cid;
	}
	public void setCategories_cid(Integer categories_cid) {
		this.categories_cid = categories_cid;
	}
	
}
