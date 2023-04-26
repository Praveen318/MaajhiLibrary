package com.example.demo.service;

import org.modelmapper.ModelMapper;
import com.example.demo.dto.UserData;
import com.example.demo.entity.Book;
import com.example.demo.entity.Issued_Book;
import com.example.demo.entity.Issued_Student;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;

public class Convertor {
	private static ModelMapper modelMapper = new ModelMapper();

	public static User userdetailstouser(UserData userData) {
		User user = modelMapper.map(userData, User.class);
		return user;
	}

	public static Issued_Book booktoStudent_Book(Book book) {
		Issued_Book book1 = modelMapper.map(book, Issued_Book.class);
		return book1;
	}

	public static Issued_Student studenttoBook_Student(Student student) {
		Issued_Student student1 = modelMapper.map(student, Issued_Student.class);
		return student1;
	}

}
