package com.crossman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

	@Autowired
	private ApiController apiController;

	@RequestMapping("/users")
	public String users(Model model) {
		Iterable<StoredUser> users = apiController.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}
}
