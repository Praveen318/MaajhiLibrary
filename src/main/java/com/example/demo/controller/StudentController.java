package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.StudentDetails;
import com.example.demo.entity.Student;
import com.example.demo.globalExpceptionHandler.CustomException;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/addStudent")
	@PreAuthorize("hasAuthority('Librarian')")
	public Student addStudent(@Valid @RequestBody StudentDetails studentDetails) {
		return studentService.getAddStudent(studentDetails.getName(), studentDetails.getMobile());
	}


	@GetMapping("/getAllStudents")
	@PreAuthorize("hasAuthority('Librarian')")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@PutMapping("/updateMobileNumber/{studentId}")
	@PreAuthorize("hasAuthority('Librarian')")
	public String updateMobileNumber(@PathVariable Long studentId, @RequestBody String mobile) {
		if (mobile.matches("\\d{10}")) {
			return studentService.getupdateMobileNumber(studentId, mobile);
		} else {
			return "Enter Valid Mobile number";
		}
	}

	@GetMapping("/getstudentByMobile")
	@PreAuthorize("hasAuthority('Librarian')")
	public Student getstudentByMobile(@RequestBody String mobile) {
		if (mobile.matches("\\d{10}")) {
			return studentService.getstudentByMobile(mobile);
		} else {
			throw new CustomException("Enter Valid Mobile number");
		}

	}

}
