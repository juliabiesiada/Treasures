package pl.ue.poznan.dao;

import java.util.List;

import pl.ue.poznan.model.User;

public interface UserDAO {
	
	User getUser(String username, String password);
	User getUserByUsername(String username);
	String addUser(User user);
	String updateUser(User user);

}
