package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.BookDetails;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/addBook")
	@PreAuthorize("hasAuthority('Librarian')")
	public Book addBook(@Valid @RequestBody BookDetails bookDetails) {
		return bookService.getAddBook(bookDetails.getName());
	}

	@PostMapping("/issueBook/{bookId}")
	@PreAuthorize("hasAuthority('Librarian')")
	public String issueBook(@PathVariable Long bookId, @RequestBody Long studentId) {
		return bookService.getIssueBook(bookId, studentId);
	}

	@PostMapping("/returnBook/{bookId}")
	@PreAuthorize("hasAuthority('Librarian')")
	public String returnBook(@PathVariable Long bookId) {
		return bookService.getReturnBook(bookId);
	}

	@GetMapping("/getAllBooks")
	@PreAuthorize("hasAuthority('Librarian')")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping("/getBookById/{bookId}")
	@PreAuthorize("hasAuthority('Librarian')")
	public Book getBookById(@PathVariable Long bookId) {
		return bookService.getBookById(bookId);
	}

}
