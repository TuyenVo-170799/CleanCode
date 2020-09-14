package com.example.utils;

import javax.servlet.http.HttpServletRequest;

public class Session {

	private static Session instance = null;
	
	private Session() {
		
	}
	
	public static Session getInstance() {
		if (instance == null) {
			instance = new Session();
		}
		return instance;
	}
	
	public void putSession(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}
	
	public Object getSession(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
	
	public void removeSession(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}
}
