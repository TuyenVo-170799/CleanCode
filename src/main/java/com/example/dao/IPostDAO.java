package com.example.dao;

import java.util.List;

import com.example.model.PostModel;

public interface IPostDAO {

	List<PostModel> findAll();
	PostModel findById(long id);
	Long insert(PostModel postModel);
	void update(PostModel postModel);
	void delete(long id);
	int count();
}
