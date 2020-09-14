package com.example.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.example.dao.IPostDAO;
import com.example.dao.impl.PostDAO;
import com.example.model.PostModel;
import com.example.service.IPostService;

public class PostService implements IPostService {
	
	private IPostDAO postDao = new PostDAO();
	
	@Override
	public List<PostModel> findAll() {
		return postDao.findAll();
	}

	@Override
	public PostModel findById(long id) {
		return postDao.findById(id);
	}

	@Override
	public PostModel insert(PostModel postModel) {
		postModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long id = postDao.insert(postModel);
		return postDao.findById(id);
	}

	@Override
	public void update(PostModel postModel) {
		postModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		postDao.update(postModel);
	}

	@Override
	public void delete(long id) {
		postDao.delete(id);
	}

	@Override
	public int count() {
		return postDao.count();
	}

}
