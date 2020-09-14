package com.example.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.PostModel;
import com.example.model.UserModel;
import com.example.service.ICategoryService;
import com.example.service.IPostService;
import com.example.service.impl.CategoryService;
import com.example.service.impl.PostService;
import com.example.utils.FormUtil;
import com.example.utils.Session;

@WebServlet(urlPatterns = {"/admin-post/home", "/admin-post/add", "/admin-post/edit", "/admin-post/delete"})
public class PostController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "C:\\Users\\vodin\\eclipse-workspace\\test\\src\\main\\webapp\\template\\web\\img";

	private IPostService postService = new PostService();
	private ICategoryService categoryService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();
		
		try {
			switch (action) {
			case "/admin-post/home":
				displayHome(req, resp);
				break;
			case "/admin-post/add":
				displayFormAdd(req, resp);
				break;
			case "/admin-post/edit":
				displayFormEdit(req, resp);
				break;
			case "/admin-post/delete":
				deletePost(req, resp);
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
		List<PostModel> posts = postService.findAll();
		req.setAttribute("posts", posts);
		req.setAttribute("alert", req.getParameter("alert"));
		req.setAttribute("message", req.getParameter("message"));
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/posts.jsp");
		rd.forward(req, resp);
	}
	
	private void displayFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("categories", categoryService.findAll());
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/add_post.jsp");
		rd.forward(req, resp);
	}
	
	private void displayFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		PostModel post = postService.findById(id);
		req.setAttribute("post", post);
		req.setAttribute("categories", categoryService.findAll());
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/edit_post.jsp");
		rd.forward(req, resp);
	}

	private void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		postService.delete(id);
		resp.sendRedirect("/test/admin-post/home?message=Delete success&alert=success");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String action = req.getServletPath();
		switch (action) {
		case "/admin-post/add":
			addPost(req, resp);
			break;
		case "/admin-post/edit":
			editPost(req, resp);
			break;
		default:
			break;
		}
	}
	
	private void addPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PostModel post = FormUtil.toModel(req);
		UserModel user = (UserModel) Session.getInstance().getSession(req, "USER");
		post.setCreatedBy(user.getUserName());
		PostModel posted = postService.insert(post);
		String id = String.valueOf(posted.getId());
		try {
			post.getImage().write(new File(UPLOAD_DIRECTORY + File.separator + id + File.separator + posted.getThumbnail()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/test/admin-post/home?message=Add success&alert=success");
	}
	
	private void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PostModel post = FormUtil.toModel(req);
		UserModel user = (UserModel) Session.getInstance().getSession(req, "USER");
		post.setModifiedBy(user.getUserName());
		PostModel oldPost = postService.findById(post.getId());
		if (post.getThumbnail() == "") {
			post.setThumbnail(oldPost.getThumbnail());
		} else {
			try {
				post.getImage().write(new File(UPLOAD_DIRECTORY + File.separator + String.valueOf(post.getId()) + File.separator + post.getThumbnail()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		postService.update(post);
		resp.sendRedirect("/test/admin-post/home?message=Edit success&alert=success");
	}
	
}
