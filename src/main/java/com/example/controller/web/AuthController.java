package com.example.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.UserModel;
import com.example.service.IUserService;
import com.example.service.impl.UserService;
import com.example.utils.Session;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
			case "/login":
				login(req, resp);
				break;
			case "/logout":
				Session.getInstance().removeSession(req, "USER");
				resp.sendRedirect("/test/login");
				break;
		}
		
	}
	
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = req.getParameter("message");
		if (message != null) {
			req.setAttribute("message", message);
		}
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/login.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		UserModel user = userService.findByUserNameAndPassword(userName, password);
		if (user != null) {
			Session.getInstance().putSession(req, "USER", user);
			resp.sendRedirect("/test/admin/home");
		} else {
			resp.sendRedirect("/test/login?message=username or password invalid");
		}
	}
}
