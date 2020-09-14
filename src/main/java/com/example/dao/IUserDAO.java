package com.example.dao;

import com.example.model.UserModel;

public interface IUserDAO {
	
	UserModel findByUserNameAndPassword(String userName, String password);
}
