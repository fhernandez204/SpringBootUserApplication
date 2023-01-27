package com.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.User;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
//@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/public/users")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<User> users = new ArrayList<User>();

			userRepository.findAll().forEach(users::add);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/public/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/public/users/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> userData = userRepository.findByEmail(email);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/admin/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User _user = userRepository
					.save(new User(user.getName(), user.getUserName(), user.getEmail(), user.getPhone()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/admin/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setName(user.getName());
			_user.setUserName(user.getUserName());
			_user.setEmail(user.getEmail());
			_user.setPhone(user.getPhone());
		return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/admin/users/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
