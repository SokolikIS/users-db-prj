package com.skillsimprover.usersdb.service.impl;

import java.util.List;

import com.skillsimprover.usersdb.dao.UserDao;
import com.skillsimprover.usersdb.domain.User;
import com.skillsimprover.usersdb.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.loadAllUsers();
	}

	@Override
	public User getUserById(Integer userId) {
		return userDao.loadUserById(userId);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.loadUserByUserName(userName);
	}

	@Override
	public Integer saveUser(User user) {
		if (user.getId() == null) {
			return userDao.storeUser(user);
		}

		userDao.updateUser(user);
		return user.getId();
	}

	@Override
	public void deleteUser(Integer userId) {
		userDao.deleteUser(userId);
	}
}
