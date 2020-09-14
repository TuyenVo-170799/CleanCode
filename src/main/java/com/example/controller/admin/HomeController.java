package com.example.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.ICategoryService;
import com.example.service.IPostService;
import com.example.service.impl.CategoryService;
import com.example.service.impl.PostService;

@WebServlet(urlPatterns = {"/admin/home"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private IPostService postService = new PostService();
	private ICategoryService categoryService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setAttribute("numberOfPosts", postService.count());
		req.setAttribute("numberOfCategories", categoryService.count());
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/home.jsp");
		rd.forward(req, resp);
	}
}
