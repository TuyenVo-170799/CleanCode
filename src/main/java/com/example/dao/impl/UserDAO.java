package com.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.dao.IUserDAO;
import com.example.database.DatabaseConnection;
import com.example.model.UserModel;

public class UserDAO implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPassword(String userName, String password) {
		String sql = "SELECT * FROM user WHERE username=? AND password=?";
		UserModel user = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			statement = connection.prepareStatement(sql);
			statement.setString(1, userName);
			statement.setNString(2, password);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user = new UserModel();
				user.setId(resultSet.getLong("id"));
				user.setUserName(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
			}
			return user;
		}
		catch (SQLException e) {
			return null;
		}
	}

	
}
