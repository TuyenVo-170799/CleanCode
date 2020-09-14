package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	public static Connection initializeDatabase() {
		
		try {
			Class.forName(DRIVER);
			String url = "jdbc:mysql://localhost:3306/myblog";
			String user = "root";
			String pass = "12345";
			return DriverManager.getConnection(url, user, pass);
		}
		catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

}
