package com.martin.books.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.martin.books.domain.Book;
import com.martin.books.services.BookService;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path = "/books")
    public ResponseEntity<Book> createBook(@RequestBody final Book book) {
        final Book savedBook = bookService.create(book);
        final ResponseEntity<Book> response = new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
        return response;
    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<Book>> allBooks() {
        final List<Book> allBooks = bookService.findAll();
        final ResponseEntity<List<Book>> response = new ResponseEntity<List<Book>>(allBooks, HttpStatus.OK);
        return response;
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> retriveBook(@PathVariable String isbn) {
        final Optional<Book> foundBook = bookService.findById(isbn);
        return foundBook.map(book -> new ResponseEntity<Book>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody final Book updatedBook) {
        final Optional<Book> foundBook = bookService.findById(isbn);
        return foundBook.map(book -> new ResponseEntity<Book>(bookService.update(updatedBook), HttpStatus.OK))
                .orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
        final Optional<Book> foundBook = bookService.findById(isbn);
        if (foundBook.isPresent()) {
            bookService.delete(foundBook.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
