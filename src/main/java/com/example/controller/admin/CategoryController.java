package com.example.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.CategoryModel;
import com.example.service.ICategoryService;
import com.example.service.impl.CategoryService;

@WebServlet(urlPatterns = {"/admin-category/home", "/admin-category/add", "/admin-category/edit", "/admin-category/delete"})
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ICategoryService categoryService = new CategoryService(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		try {
			switch (action) {
			case "/admin-category/home":
				displayHome(req, resp);
				break;
			case "/admin-category/add":
				displayForm(req, resp);
				break;
			case "/admin-category/edit":
				displayEditForm(req, resp);
				break;
			default:
				displayHome(req, resp);
				break;
			}
		}
		catch (Exception e) {
			
		}
	}
	
	private void displayHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CategoryModel> categories = categoryService.findAll();
		req.setAttribute("categories", categories);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/categories.jsp");
		rd.forward(req, resp);
	}
	
	private void displayForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/add_category.jsp");
		rd.forward(req, resp);
	}
	
	private void displayEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		CategoryModel category = categoryService.findById(id);
		req.setAttribute("category", category);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/edit_category.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
			case "/admin-category/add":
				addCategory(req, resp);
				break;
			case "/admin-category/edit":
				updateCategory(req, resp);
				break;
		}
	}
	
	private void addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setCategoryName(req.getParameter("categoryName"));
		categoryModel.setCategoryCode(req.getParameter("categoryCode"));
		categoryService.insert(categoryModel);
		resp.sendRedirect("/test/admin-category/home");
	}
	
	private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		CategoryModel categoryModel = categoryService.findById(id);
		categoryModel.setCategoryName(req.getParameter("categoryName"));
		categoryModel.setCategoryCode(req.getParameter("categoryCode"));
		categoryService.update(categoryModel);
		resp.sendRedirect("/test/admin-category/home");
	}
}
