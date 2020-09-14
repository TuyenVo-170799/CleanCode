package com.example.service;

import java.util.List;

import com.example.model.PostModel;

public interface IPostService {
	
	List<PostModel> findAll();
	PostModel findById(long id);
	PostModel insert(PostModel postModel);
	void update(PostModel postModel);
	void delete(long id);
	int count();
}
