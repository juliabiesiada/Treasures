package pl.ue.poznan.model;

import java.sql.Date;

public class User {

	Integer id;
	String username;
	String firstname;
	String lastname;
	String city;
	Date birthdate;
	Integer roleid;
	String street;
	String postcode;
	String phonenumber;
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	String password;
	String passwordConfirm;
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public User(String username, String firstname, String lastname, String city, Date birthdate,
			Integer roleid, String street, String postcode, String phonenumber, String password, String passwordConfirm,
			String email) {
		super();
		this.id=id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.birthdate = birthdate;
		this.roleid = roleid;
		this.phonenumber = phonenumber;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.postcode = postcode;
		this.street = street;
		this.email = email;
	}
	public User() {
		super();
	}
	
	
}
