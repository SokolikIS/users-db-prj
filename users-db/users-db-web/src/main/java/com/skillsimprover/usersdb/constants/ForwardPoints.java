package com.skillsimprover.usersdb.constants;

public enum ForwardPoints {

	UserListController("userList.html"),

	LoginView("/WEB-INF/pages/login.jsp"),

	UserListView("/WEB-INF/pages/userList.jsp");

	private String url;

	private ForwardPoints(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
