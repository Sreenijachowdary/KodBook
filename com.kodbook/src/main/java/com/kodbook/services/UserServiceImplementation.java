package com.kodbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.User;
import com.kodbook.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository repo;

	
	public boolean userExists(String username, String email) {
		User user1 = repo.findByUsername(username);
		User user2 = repo.findByemail(email);
		if(user1 != null || user2 != null)
		{
			return true;
		}
		else {
		return false;
		}
	}

	
	public void addUser(User user) {
		repo.save(user);
		
	}

	public boolean validateUser(String username, String password) {
		String dbPass = repo.findByUsername(username).getPassword();
		if(password.equals(dbPass)) {
					return true;
			}
		return false;
		}


	@Override
	public User getUser(String username) {
		return repo.findByUsername(username);
	}


	@Override
	public void updateUser(User user) {
		
		repo.save(user);
	}
}
