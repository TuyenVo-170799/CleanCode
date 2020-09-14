package com.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.IPostDAO;
import com.example.database.DatabaseConnection;
import com.example.model.PostModel;

public class PostDAO implements IPostDAO {

	@Override
	public List<PostModel> findAll() {
		String sql = "SELECT * FROM post";
		List<PostModel> posts = new ArrayList<PostModel>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				PostModel post = new PostModel();
				post.setId(resultSet.getLong("id"));
				post.setTitle(resultSet.getString("title"));
				post.setThumbnail(resultSet.getString("thumbnail"));
				post.setDescription(resultSet.getString("description"));
				post.setContent(resultSet.getString("content"));
				post.setCreatedBy(resultSet.getString("createdBy"));
				post.setCreatedDate(resultSet.getTimestamp("createdDate"));
				post.setModifiedBy(resultSet.getString("modifiedBy"));
				post.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
				post.setCategoryId(resultSet.getLong("categoryId"));
				posts.add(post);
			}
			return posts;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

	@Override
	public PostModel findById(long id) {
		String sql = "SELECT * FROM post WHERE id=?";
		PostModel post = new PostModel();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				post.setId(resultSet.getLong("id"));
				post.setTitle(resultSet.getString("title"));
				post.setThumbnail(resultSet.getString("thumbnail"));
				post.setDescription(resultSet.getString("description"));
				post.setContent(resultSet.getString("content"));
				post.setCreatedBy(resultSet.getString("createdBy"));
				post.setCreatedDate(resultSet.getTimestamp("createdDate"));
				post.setModifiedBy(resultSet.getString("modifiedBy"));
				post.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
				post.setCategoryId(resultSet.getLong("categoryId"));
			}
			return post;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Long insert(PostModel postModel) {
		String sql = "INSERT INTO post (title, thumbnail, description, content, createdBy, createdDate, modifiedBy, modifiedDate, categoryId) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = DatabaseConnection.initializeDatabase();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, postModel.getTitle());
			statement.setString(2, postModel.getThumbnail());
			statement.setString(3, postModel.getDescription());
			statement.setString(4, postModel.getContent());
			statement.setString(5, postModel.getCreatedBy());
			statement.setTimestamp(6, postModel.getCreatedDate());
			statement.setString(7, postModel.getModifiedBy());
			statement.setTimestamp(8, postModel.getModifiedDate());
			statement.setLong(9, postModel.getCategoryId());
			
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		}
		catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				}
				catch (SQLException e1) {
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
		return null;
	}

	@Override
	public void update(PostModel postModel) {

		String sql = "UPDATE post SET title=?, description=?, thumbnail=?, content=?, categoryId=?, modifiedBy=?, modifiedDate=? WHERE id=?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setString(1, postModel.getTitle());
			statement.setString(2, postModel.getDescription());
			statement.setString(3, postModel.getThumbnail());
			statement.setString(4, postModel.getContent());
			statement.setLong(5, postModel.getCategoryId());
			statement.setString(6, postModel.getModifiedBy());
			statement.setTimestamp(7, postModel.getModifiedDate());
			statement.setLong(8, postModel.getId());
			statement.executeUpdate();
			connection.commit();
		}
		catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				}
				catch (SQLException e1) {
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
	public void delete(long id) {
		String sql = "DELETE FROM post WHERE id=?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
			connection.commit();
		}
		catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				}
				catch (SQLException e1) {
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
	public int count() {
		String sql = "SELECT COUNT(id) AS numberOfPosts FROM post";
		int total = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseConnection.initializeDatabase();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				total = resultSet.getInt("numberOfPosts");
			}
			return total;
		}
		catch (SQLException e) {
			return 0;
		}
	}
	
}
