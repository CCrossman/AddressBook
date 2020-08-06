package com.crossman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class ViewController {
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

	@Autowired
	private ApiController apiController;

	@Autowired
	private StoredUserRepository userRepository;

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

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public ResponseEntity<String> userDeleted(@RequestBody String email) {
		logger.debug("userDeleted({})", email);
		userRepository.deleteById(email);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping("/address")
	public String addressAdder(Model model) {
		logger.debug("addressAdder()");
		model.addAttribute("address", new UserAddress(null, null, null, null, null, null, null));
		return "addressAdder";
	}

	@RequestMapping(value = "/address", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
	public String addressAdded(Model model, UserAddress userAddress) {
		logger.debug("addressAdded({})", userAddress);
		addAddress(userAddress);
		model.addAttribute("address", userAddress);
		return "addressAdded";
	}

	@RequestMapping(value = "/address", method = RequestMethod.DELETE)
	public ResponseEntity<String> addressDeleted(@RequestBody UserAddressId userAddressId) {
		logger.debug("addressDeleted({})", userAddressId);
		removeAddress(userAddressId);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	private void addAddress(UserAddress userAddress) {
		logger.debug("addAddress({})", userAddress);
		if (userAddress == null || userAddress.getEmail() == null) {
			return;
		}
		Optional<StoredUser> userOption = userRepository.findById(userAddress.getEmail());
		userOption.ifPresent(su -> {
			Set<StoredAddress> addresses = su.getAddresses() == null ? new HashSet<>() : new HashSet<>(su.getAddresses());
			addresses.add(userAddress.toAddress().toStoredAddress());
			StoredUser user = new StoredUser(su.getEmail(), su.getFirstName(), su.getLastName(), addresses);
			userRepository.save(user);
		});
	}

	private void removeAddress(UserAddressId userAddressId) {
		logger.debug("removeAddress({})", userAddressId);
		if (userAddressId == null || userAddressId.getEmail() == null || userAddressId.getAddressId() == null) {
			return;
		}
		Optional<StoredUser> userOption = userRepository.findById(userAddressId.getEmail());
		userOption.ifPresent(su -> {
			Set<StoredAddress> addresses = su.getAddresses() == null ? new HashSet<>() : new HashSet<>(su.getAddresses());
			addresses.removeIf(sa -> sa.getId().equals(userAddressId.getAddressId()));
			StoredUser user = new StoredUser(su.getEmail(), su.getFirstName(), su.getLastName(), addresses);
			userRepository.save(user);
		});
	}
}
