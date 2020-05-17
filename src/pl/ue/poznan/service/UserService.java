package pl.ue.poznan.service;

import java.util.List;

import pl.ue.poznan.model.User;

public interface UserService {
	
	User getUser(String username, String password);
	User getUserByUsername(String username);
	String addUser(User user);
	String updateUser(User user);

}
