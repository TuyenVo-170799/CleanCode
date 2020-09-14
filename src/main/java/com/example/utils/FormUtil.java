package com.example.utils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.model.PostModel;
import com.example.service.ICategoryService;
import com.example.service.impl.CategoryService;

public class FormUtil {
	
	private static ICategoryService categoryService = new CategoryService();

	// convert attribute from form to model (+ upload image)
	public static PostModel toModel(HttpServletRequest request) {
		PostModel post = new PostModel();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item: multiparts) {
					if (!item.isFormField()) {
						post.setThumbnail(new File(item.getName()).getName());
						post.setImage(item);
					}
					else {
						String fieldName = item.getFieldName();
						String fieldValue = item.getString();
						// change from ISO to UTF-8
						byte[] b = fieldValue.getBytes(StandardCharsets.ISO_8859_1);
						if (fieldName.equals("id")) {
							post.setId(Long.parseLong(new String(b, StandardCharsets.UTF_8)));
						} else if (fieldName.equals("title")) {
							post.setTitle(new String(b, StandardCharsets.UTF_8));
						} else if (fieldName.equals("description")) {
							post.setDescription(new String(b, StandardCharsets.UTF_8));
						} else if (fieldName.equals("categoryCode")) {
							post.setCategoryId(categoryService.findByCode(new String(b, StandardCharsets.UTF_8)).getId());
						} else if (fieldName.equals("content")) {
							post.setContent(new String(b, StandardCharsets.UTF_8));
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return post;
	}
}
