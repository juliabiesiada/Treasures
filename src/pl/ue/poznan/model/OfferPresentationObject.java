package pl.ue.poznan.model;

import java.util.Date;

//temporary solution allowing matching an id of a category to a category name
//in the presentation layer
public class OfferPresentationObject {

	Integer oid;
	String title;
	String description;
	Date dateAdded;
	Float price;
	String users_username;
	String category_name;
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
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public OfferPresentationObject() {
		super();
	}
	public OfferPresentationObject(Integer oid, String title, String description, Date dateAdded, Float price,
			String users_username, String category_name) {
		super();
		this.oid = oid;
		this.title = title;
		this.description = description;
		this.dateAdded = dateAdded;
		this.price = price;
		this.users_username = users_username;
		this.category_name = category_name;
	}
	
}
