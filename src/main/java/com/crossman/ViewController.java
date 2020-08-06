package com.crossman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;

@Controller
public class ViewController {
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

	@Autowired
	private ApiController apiController;

	@RequestMapping("/users")
	public String users(Model model) {
		logger.debug("users()");
		Iterable<StoredUser> users = apiController.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@RequestMapping("/user")
	public String userCreator(Model model) {
		logger.debug("userCreator()");
		model.addAttribute("user", new User(null, null, null, Collections.emptySet()));
		return "userCreator";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
	public String userCreatorSubmit(Model model, User user) {
		logger.debug("userCreatorSubmit({})", user);
		StoredUser storedUser = apiController.createUser(user);
		model.addAttribute("user", user);
		return "userCreated";
	}
}
