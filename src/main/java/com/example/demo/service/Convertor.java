package com.example.demo.service;

import org.modelmapper.ModelMapper;
import com.example.demo.dto.UserData;
import com.example.demo.entity.User;

public class Convertor {
	private static ModelMapper modelMapper = new ModelMapper();

	public static User userdetailstouser(UserData userData) {
		User user = modelMapper.map(userData, User.class);
		return user;
	}

}
