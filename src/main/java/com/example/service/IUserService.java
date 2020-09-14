package com.example.service;

import com.example.model.UserModel;

public interface IUserService {

	UserModel findByUserNameAndPassword(String userName, String password);
}
