
package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserRepository;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userDao;

	@Override
	public User authenticateUser(String email, String pw) {
		// Find the user by email
		User user = userDao.findByEmail(email);

		// Return the user only if the password matches
		if (user != null && user.getPassword().equals(pw)) {
			return user;
		}

		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.findByEmail(email);
	}
}
