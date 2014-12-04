package edu.neumont.csc280.lab7.user;


public interface UserService {
	public User getUserById(Long id);
	public User getUserByUsername(String username);
	public void updateUser(User user);
}
