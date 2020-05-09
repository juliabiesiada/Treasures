package pl.ue.poznan.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import pl.ue.poznan.dao.DataBaseConnect;
import pl.ue.poznan.dao.UserDAOImpl;
import pl.ue.poznan.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserServiceImpl implements UserService {


	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addUser(User user) {
		String result;
		UserDAOImpl udi = new UserDAOImpl();
		result = udi.addUser(user);
		return result;
	}
	
	public String validateRegistration(User user) {
		
		String errorMsg = "";
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		
		String uname = user.getUsername();
		String fname = user.getFirstname();
		String lname = user.getLastname();
		Date bdate = user.getBirthdate();
		String city = user.getCity();
		String street = user.getStreet();
		Integer rid = user.getRoleid();
		String pcode = user.getPostcode();
		String pnumber = user.getPhonenumber();
		String password = user.getPassword();
		String passwordconf = user.getPasswordConfirm();
		String email = user.getEmail();
		
		if (uname.equals("") || fname.equals("") || lname.equals("") || bdate == null ||
		city.equals("") || street.equals("") || rid == null || pcode.equals("") || pnumber.equals("")
		|| password.equals("") || passwordconf.equals("") || email.equals("")) {
			
			errorMsg = "Input incomplete";
			return errorMsg;
		}
		
		if (!(password.equals(passwordconf))) {
			
			errorMsg = "Passwords do not match";
			return errorMsg;
			
		}
		
		if (!email.matches(regex)) {
			
			errorMsg = "Incorrect email address";
			return errorMsg;
			
		}
		
		return errorMsg;
		
	}
	
}