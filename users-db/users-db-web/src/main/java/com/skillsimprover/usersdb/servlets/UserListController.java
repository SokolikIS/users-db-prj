package com.skillsimprover.usersdb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skillsimprover.usersdb.beans.ErrorBean;
import com.skillsimprover.usersdb.constants.ForwardPoints;
import com.skillsimprover.usersdb.domain.User;
import com.skillsimprover.usersdb.service.UserService;
import com.skillsimprover.usersdb.utils.StringUtils;
import com.skillsimprover.usersdb.utils.http.HttpUtils;

//@WebServlet("/userList.html")
public class UserListController extends BaseController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String actionParam = request.getParameter("action");

		if ("save_user".equalsIgnoreCase(actionParam)) {
			doSaveUser(request);
		} else if ("update_user".equalsIgnoreCase(actionParam)) {
			doUpdateUser(request);
		} else if ("delete_user".equalsIgnoreCase(actionParam)) {
			doDeleteUser(request);
		}

		UserService userService = (UserService) getBean("userService");

		List<User> allUsers = userService.getAllUsers();
		HttpSession session = request.getSession();
		session.setAttribute("all_users_attr", allUsers);

		forwardToPoint(ForwardPoints.UserListView, request, response);
	}

	private void doSaveUser(HttpServletRequest request) {
		User user = buildUserFromRequest(request);
		List<ErrorBean> validationErrors = validateUser(user);
		if (!validationErrors.isEmpty()) {
			request.setAttribute("validation_errors", validationErrors);
			request.setAttribute("current_user", user);
			return;
		}

		UserService userService = (UserService) getBean("userService");

		userService.saveUser(user);
	}

	private void doUpdateUser(HttpServletRequest request) {
		Integer userId = HttpUtils.getIntParam(request, "user_id");
		if (userId == null) {
			return;
		}

		UserService userService = (UserService) getBean("userService");
		User user = userService.getUserById(userId);

		request.setAttribute("current_user", user);
	}

	private void doDeleteUser(HttpServletRequest request) {
		Integer userId = HttpUtils.getIntParam(request, "user_id");
		if (userId == null) {
			return;
		}

		UserService userService = (UserService) getBean("userService");
		userService.deleteUser(userId);
	}

	private List<ErrorBean> validateUser(User user) {

		List<ErrorBean> errors = new ArrayList<>();

		String userName = user.getUserName();
		if (StringUtils.isBlank(userName)) {
			ErrorBean emptyName = new ErrorBean("userName.error", "userListPage.userName.error");
			errors.add(emptyName);
		}

		String password = user.getPassword();
		if (StringUtils.isBlank(password)) {
			ErrorBean emptyPassword = new ErrorBean("password.error", "userListPage.password.error");
			errors.add(emptyPassword);
		}

		return errors;
	}

	private User buildUserFromRequest(HttpServletRequest request) {
		Integer id = HttpUtils.getIntParam(request, "hdn_user_id");
		String userName = request.getParameter("txt_login");
		String password = request.getParameter("txt_password");

		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassword(password);

		return user;
	}

	private Object getBean(String beanName) {
		Object bean = null;

		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-web.xml"))  {
			bean = context.getBean(beanName);
		}

		return bean;
	}
}
