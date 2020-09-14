package com.example.service;

import java.util.List;

import com.example.model.CategoryModel;

public interface ICategoryService {

	List<CategoryModel> findAll();
	CategoryModel findByCode(String code);
	CategoryModel findById(long id);
	void insert(CategoryModel categoryModel);
	void update(CategoryModel categoryModel);
	int count();
}
