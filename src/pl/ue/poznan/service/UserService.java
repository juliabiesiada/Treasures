package pl.ue.poznan.service;

import java.util.List;

import pl.ue.poznan.model.User;

public interface UserService {
	
	User getUser(int id);
	String addUser(User user);

}
