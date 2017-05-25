package com.skillsimprover.usersdb.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skillsimprover.usersdb.domain.User;

@Controller
public class LoginController {

	@RequestMapping("/login.html")
	public ModelAndView showLoginPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("login");

		return model;
	}

	@RequestMapping("auth.html")
	public String authoriseUser(User user, HttpSession session, HttpServletRequest reqest) {
		System.out.println(user);
		return "redirect:userList.html";
	}

	@ModelAttribute
	private User getDefaultUser() {
		return new User();
	}
}
