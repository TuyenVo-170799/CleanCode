package com.example.service.impl;

import com.example.dao.IUserDAO;
import com.example.dao.impl.UserDAO;
import com.example.model.UserModel;
import com.example.service.IUserService;

public class UserService implements IUserService {

	private IUserDAO userDao = new UserDAO();
	
	@Override
	public UserModel findByUserNameAndPassword(String userName, String password) {
		return userDao.findByUserNameAndPassword(userName, password);
	}

}
