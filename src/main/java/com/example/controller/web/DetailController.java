package com.example.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.IPostService;
import com.example.service.impl.PostService;

@WebServlet(urlPatterns = {"/detail"})
public class DetailController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private IPostService postService = new PostService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		long id = Long.parseLong(req.getParameter("id"));
		req.setAttribute("post", postService.findById(id));
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/detail.jsp");
		rd.forward(req, resp);
	}
}
