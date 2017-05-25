package com.skillsimprover.usersdb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skillsimprover.usersdb.beans.ErrorBean;
import com.skillsimprover.usersdb.constants.ForwardPoints;
import com.skillsimprover.usersdb.domain.User;
import com.skillsimprover.usersdb.service.UserService;
import com.skillsimprover.usersdb.utils.StringUtils;

//@WebServlet("/login.html")
public class LoginController extends BaseController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		if (!"login".equals(action)) {
			forwardToPoint(ForwardPoints.LoginView, request, response);
			return;
		}

		User user = buildUserFromRequest(request);
		List<ErrorBean> validationErrors = validateUser(user);
		if (!validationErrors.isEmpty()) {
			request.setAttribute("validation_errors", validationErrors);
			request.setAttribute("current_user", user);

			forwardToPoint(ForwardPoints.LoginView, request, response);

			return;
		}

		forwardToPoint(ForwardPoints.UserListController, request, response);
	}

	private List<ErrorBean> validateUser(User user) {
		List<ErrorBean> errors = new ArrayList<>();
		boolean isEmptyFields = false;

		String userName = user.getUserName();
		if (StringUtils.isBlank(userName)) {
			ErrorBean emptyName = new ErrorBean("userName.error", "loginPage.userName.error");
			errors.add(emptyName);
			isEmptyFields = true;
		}

		String password = user.getPassword();
		if (StringUtils.isBlank(password)) {
			ErrorBean emptyPassword = new ErrorBean("password.error", "loginPage.password.error");
			errors.add(emptyPassword);
			isEmptyFields = true;
		}

		if (isEmptyFields) {
			return errors;
		}

		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-web.xml");
		UserService service = (UserService) context.getBean("userService");
		context.close();

		User userFromDb = service.getUserByUserName(userName);

		if (userFromDb == null) {
			ErrorBean emptyName = new ErrorBean("auth.error", "loginPage.auth.error");
			errors.add(emptyName);
			return errors;
		}

		String passwordFromDb = userFromDb.getPassword();
		if (!password.equals(passwordFromDb)) {
			ErrorBean emptyName = new ErrorBean("auth.error", "loginPage.auth.error");
			errors.add(emptyName);
		}

		return errors;
	}

	private User buildUserFromRequest(HttpServletRequest request) {
		String userName = request.getParameter("txt_login");
		String password = request.getParameter("txt_password");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);

		return user;
	}
}
