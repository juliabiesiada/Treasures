package dao;

import java.util.List;


import myjava.User;

public interface UserDAO {
	
	User getUser(int id);
	List<User> getUsers();
	String addUser(User user);
	boolean updateUser(User user);
	int getUserID(User user);

}
