package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;
import com.example.demo.entity.Issued_Book;
import com.example.demo.entity.Issued_Student;
import com.example.demo.entity.Student;
import com.example.demo.globalExpceptionHandler.CustomException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.Issued_BookRepository;
import com.example.demo.repository.Issued_StudentRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private Issued_StudentRepository issued_StudentRepository;
	@Autowired
	private Issued_BookRepository issued_BookRepository;

	public Book getAddBook(String name) {
		Book book = new Book();
		book.setName(name);
		book.setStatus(false);
		try {
		book=bookRepository.save(book);
		Issued_Book book1=Convertor.booktoStudent_Book(book);
		issued_BookRepository.save(book1);
		return book;
		}
		catch(Exception ex){
			throw new CustomException("Ënter vaild name");
		}
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}


	public String getIssueBook(long bookId, long studentId) {
		Book book = bookRepository.findById(bookId).orElse(null);
		Issued_Book book1 = issued_BookRepository.findById(bookId).orElse(null);
		if (book == null) {
			return ("Book not found");
		}
		if (book.getStatus()) {
			return ("Book already issued");
		}
		Issued_Student student1 = issued_StudentRepository.findById(studentId).orElse(null);
		Student student = studentRepository.findById(studentId).orElse(null);
		if (student == null) {
			return ("Student not found");
		}
		book.setStatus(true);
		book.setReturnDate(null);
		book.setIssueDate(LocalDate.now());
		book.setStudent(student1);
		student.getBooks().add(book1);
		bookRepository.save(book);
		studentRepository.save(student);
		return ("Book issued successfully");
	}

	public String getReturnBook(Long bookId) {
		Book book = bookRepository.findById(bookId).orElse(null);
		Issued_Book book1 = issued_BookRepository.findById(bookId).orElse(null);
		if (book == null) {
			return ("Book not found");
		}
		if (!book.getStatus()) {
			return ("Book already returned");
		}
		Issued_Student student1 = book.getStudent();
		Student student=studentRepository.findById(student1.getId()).orElse(null);
		book.setStatus(false);
		book.setReturnDate(LocalDate.now());
        student.getBooks().remove(book1);
		bookRepository.save(book);
        studentRepository.save(student);
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