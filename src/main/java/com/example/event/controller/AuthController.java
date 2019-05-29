package com.example.event.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.event.dao.UserDao;
import com.example.event.domain.User;

@Controller
public class AuthController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String addGet(Model model) throws Exception {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(
			@Valid User user,
			Errors errors,
			HttpSession session) throws Exception {

		if (!errors.hasErrors()) {
			user = userDao.findByLoginIdAndLoginPass(user.getLoginId(), user.getPass());
			if (user != null) {
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("loginId", user.getLoginId());
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("typeId", user.getType());
				return "redirect:/eventList";
			} else {
				errors.reject("errors.login");
				return "login";
			}
		} else {
			return "login";
		}

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logoutDone";

	}

}
