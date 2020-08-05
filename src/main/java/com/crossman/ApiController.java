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
	private StoredAddressRepository storedAddressRepository;

	@Autowired
	private UserAddressJoinRepository joinRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/addresses")
	public Iterable<StoredAddress> findAllAddresses() {
		logger.debug("findAllAddresses()");
		return storedAddressRepository.findAll();
	}

	@RequestMapping(value = "/address", method = {RequestMethod.POST, RequestMethod.PUT})
	public StoredAddress createAddress(@RequestBody Address address) {
		logger.debug("createAddress({})", address);
		StoredAddress storedAddress = address.toStoredAddress();
		return storedAddressRepository.save(storedAddress);
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
	public StoredAddress findAddressById(@PathVariable("id") String id) {
		logger.debug("findAddressById({})", id);
		return storedAddressRepository.findById(id).orElse(null);
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
	public StoredAddress deleteAddress(@PathVariable("id") String id) {
		logger.debug("deleteAddress({})", id);
		Optional<StoredAddress> storedAddressOption = storedAddressRepository.findById(id);
		storedAddressRepository.deleteById(id);
		return storedAddressOption.orElse(null);
	}

	@RequestMapping("/users")
	public Iterable<User> findAllUsers() {
		logger.debug("findAllUsers()");
		return userRepository.findAll();
	}

	@RequestMapping(value = "/user", method = {RequestMethod.POST,RequestMethod.PUT})
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
