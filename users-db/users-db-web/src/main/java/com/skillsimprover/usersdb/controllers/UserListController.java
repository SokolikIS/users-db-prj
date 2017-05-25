package com.skillsimprover.usersdb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserListController {


	@RequestMapping("/userList.html")
	public String m1() {
		return "";
	}
}
