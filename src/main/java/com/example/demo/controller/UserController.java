package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AuthReq;
import com.example.demo.entity.User;
import com.example.demo.globalExpceptionHandler.CustomException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Convertor;
import com.example.demo.service.JwtService;
import com.example.demo.dto.UserData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/Login")
	public String authenticateAndGetToken(@Valid @RequestBody AuthReq authReq) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword()));
		if (authentication.isAuthenticated()) {
			User user = userRepository.findByEmail(authReq.getEmail()).orElse(null);
			String token = jwtService.generateToken(authReq.getEmail());
			user.setToken(token);
			userRepository.save(user);
			return token;
		}
		// return jwtService.generateToken(authReq.getEmail());}
		else
			throw new CustomException("No authorities given to user");
	}

	@PostMapping("/add")
	@PreAuthorize("hasAuthority('Librarian')")
	public String add(@RequestBody UserData userData) {
		User user = Convertor.userdetailstouser(userData);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "added";
	}

	@PostMapping("/Logout")
	@PreAuthorize("hasAuthority('Librarian')")
	public String Logout(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		String token = authHeader.substring(7);
		if (userRepository.findByToken(token) != null) {
			User user = userRepository.findByToken(token);
			user.setToken(null);
			userRepository.save(user);
			return "Logged_out";
		} else
			throw new CustomException("User Logged out");
	}
}
