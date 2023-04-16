package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import com.example.demo.globalExpceptionHandler.CustomException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private StudentRepository studentRepository;

	public Book getAddBook(String name) {
		Book book = new Book();
		book.setName(name);
		book.setStatus(false);
		return bookRepository.save(book);
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public String getdeleteBook(Long bookId) {
		bookRepository.deleteById(bookId);
		return "book removed!!" + bookId;
	}

	public String getIssueBook(long bookId, long studentId) {
		Book book = bookRepository.findById(bookId).orElse(null);
		if (book == null) {
			return ("Book not found");
		}
		if (book.getStatus()) {
			return ("Book already issued");
		}
		Student student = studentRepository.findById(studentId).orElse(null);
		if (student == null) {
			return ("Student not found");
		}
		book.setStatus(true);
		book.setStudent(student);
//		student.getBooks().add(book);
		bookRepository.save(book);
//		studentRepository.save(student);
		return ("Book issued successfully");
	}

	public String getReturnBook(Long bookId) {
		Book book = bookRepository.findById(bookId).orElse(null);
		if (book == null) {
			return ("Book not found");
		}
		if (!book.getStatus()) {
			return ("Book already returned");
		}
//        Student student = book.getStudent();
		book.setStatus(false);
		book.setStudent(null);
//        student.getBooks().remove(book);
		bookRepository.save(book);
//        studentRepository.save(student);
		return ("Book returned successfully");
	}

	public Book getBookById(Long bookId) {
		Book book = bookRepository.findById(bookId).orElse(null);
		if (book == null) {
			throw new CustomException("Book not Found");
		} else
			return book;
	}
}