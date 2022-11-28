package com.example.projectBook.controller;

import com.example.projectBook.model.Book;
import com.example.projectBook.model.Filter;
import com.example.projectBook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAllBooks() {
		return bookService.getAllBooks();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
		return bookService.getBookById(id);
	}
	@GetMapping("/allFiltered")
	public ResponseEntity<List<Book>> getBooksByFilter(@RequestBody Filter filter) {
		return bookService.getBooksByFilter(filter);
	}
	@PostMapping("/create")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		return bookService.createBook(book);
	}
	@PutMapping("/update")
	public ResponseEntity<Book> updateBook( @RequestBody Book book) {
		return bookService.updateBook(book);
	}
	@DeleteMapping("/deleteOne/{id}")
	public ResponseEntity<HttpStatus> deleteOne(@PathVariable("id") long id) {
		return bookService.deleteOne(id);
	}
	@PutMapping("/rate/{id}")
	public ResponseEntity<HttpStatus> rateBook(@PathVariable("id") long id, @RequestBody Integer score) {
		return bookService.rateBook(id,score);
	}
}
