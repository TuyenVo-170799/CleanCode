package com.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.ICategoryDAO;
import com.example.database.DatabaseConnection;
import com.example.model.CategoryModel;

public class CategoryDAO implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				CategoryModel category = new CategoryModel();
				category.setId(resultSet.getLong("id"));
				category.setCategoryName(resultSet.getString("categoryName"));
				category.setCategoryCode(resultSet.getString("categoryCode"));
				categories.add(category);
			}
			return categories;
		}
		catch (SQLException e) {
			return null;
		}
	}

	@Override
	public CategoryModel findByCode(String code) {
		String sql = "SELECT * from category WHERE categoryCode = ?";
		CategoryModel category = new CategoryModel();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			statement = connection.prepareStatement(sql);
			statement.setString(1, code);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				category.setId(resultSet.getLong("id"));
				category.setCategoryName(resultSet.getString("categoryName"));
				category.setCategoryCode(resultSet.getString("categoryCode"));
			}
			return category;
		}
		catch (SQLException e) {
			return null;
		}
	}

	@Override
	public int count() {
		String sql = "SELECT COUNT(id) AS numberOfCategories FROM category";
		int total = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				total = resultSet.getInt("numberOfCategories");
			}
			return total;
		}
		catch (SQLException e) {
			return 0;
		}
	}

	@Override
	public void insert(CategoryModel categoryModel) {
		String sql = "INSERT INTO category(categoryName, categoryCode) VALUE (?, ?)";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setString(1, categoryModel.getCategoryName());
			statement.setString(2, categoryModel.getCategoryCode());
			statement.executeUpdate();
			connection.commit();
		}
		catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void update(CategoryModel categoryModel) {
		String sql = "UPDATE category SET categoryName=?, categoryCode=? WHERE id=?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setString(1, categoryModel.getCategoryName());
			statement.setString(2, categoryModel.getCategoryCode());
			statement.setLong(3, categoryModel.getId());
			statement.executeUpdate();
			connection.commit();
		}
		catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public CategoryModel findById(long id) {
		String sql = "SELECT * FROM category WHERE id=?";
		CategoryModel categoryModel = new CategoryModel();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				categoryModel.setId(resultSet.getLong("id"));
				categoryModel.setCategoryName(resultSet.getString("categoryName"));
				categoryModel.setCategoryCode(resultSet.getString("categoryCode"));
			}
			return categoryModel;
		}
		catch (SQLException e) {
			return null;
		}
	}
}
