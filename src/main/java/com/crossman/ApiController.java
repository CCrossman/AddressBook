package com.crossman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/users")
	public Iterable<User> findAllUsers() {
		logger.debug("findAllUsers()");
		return userRepository.findAll();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		logger.debug("createUser({})", user);
		return userRepository.save(user);
	}

	@RequestMapping(value = "/user/{email}", method = RequestMethod.GET)
	public User findUserById(@PathVariable("email") String email) {
		logger.debug("findUserById({})", email);
		return userRepository.findById(email).orElse(null);
	}

	@RequestMapping(value = "/user/{email}", method = RequestMethod.DELETE)
	public User deleteUser(@PathVariable("email") String email) {
		logger.debug("deleteUser({})", email);
		Optional<User> userOption = userRepository.findById(email);
		userRepository.deleteById(email);
		return userOption.orElse(null);
	}
}
