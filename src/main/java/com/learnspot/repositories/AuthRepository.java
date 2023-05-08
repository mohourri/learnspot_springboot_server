package com.learnspot.repositories;

import com.learnspot.entities.User;

public interface AuthRepository {
	public User login(String email, String password);
	public User register(User user);
}
