package com.example.service.impl;

import java.util.List;

import com.example.dao.ICategoryDAO;
import com.example.dao.impl.CategoryDAO;
import com.example.model.CategoryModel;
import com.example.service.ICategoryService;

public class CategoryService implements ICategoryService {

	private ICategoryDAO categoryDao = new CategoryDAO();
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public CategoryModel findByCode(String code) {
		return categoryDao.findByCode(code);
	}

	@Override
	public int count() {
		return categoryDao.count();
	}

	@Override
	public void insert(CategoryModel categoryModel) {
		categoryDao.insert(categoryModel);
	}

	@Override
	public void update(CategoryModel categoryModel) {
		categoryDao.update(categoryModel);
	}
	
	@Override
	public CategoryModel findById(long id) {
		return categoryDao.findById(id);
	}

	
}
