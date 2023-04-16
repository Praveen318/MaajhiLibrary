package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Student;
import com.example.demo.globalExpceptionHandler.CustomException;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student getAddStudent(String name, String mobileNumber) {
		Student student = new Student();
		student.setName(name);
		student.setMobile(mobileNumber);
		return studentRepository.save(student);
		// return studentRepository.findByName(name);
	}

	public String getupdateMobileNumber(Long studentId, String mobile) {
		try {
			Student existingStudent = studentRepository.findById(studentId).orElse(null);
			if (existingStudent == null)
				return "StudentId :" + studentId + " not found";
			existingStudent.setMobile(mobile);
			studentRepository.save(existingStudent);
			return existingStudent.getName() + " updated mobile number " + existingStudent.getMobile();
		} catch (Exception ex) {
			return "This mobile number is already present";
		}
	}

	public String getdeleteStudent(Long studentId) {
		studentRepository.deleteById(studentId);
		return "student removed!!" + studentId;
	}

	public List<Student> getAllStudents() {
		System.out.println("dsdfs");
		return studentRepository.findAll();
	}

	public Student getstudentByMobile(String mobile) {
		Student student = studentRepository.findByMobile(mobile).orElse(null);
		if (student == null) {
			throw new CustomException("Student not Found");
		} else
			return student;
	}
}
