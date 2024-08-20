package com.martin.books.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martin.books.domain.Book;
import com.martin.books.domain.BookEntity;
import com.martin.books.repositories.BookRepository;
import com.martin.books.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(final Book book) {
        final BookEntity bookEntity = bookToBookEntity(book);
        final BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return bookEntityToBook(savedBookEntity);
    }

    @Override
    public Optional<Book> findById(String isbn) {
        final Optional<BookEntity> existingBookEntity = bookRepository.findById(isbn);
        return existingBookEntity.map(book -> bookEntityToBook(book));
    }

    @Override
    public List<Book> findAll() {
        final List<BookEntity> books = bookRepository.findAll();
        List<Book> mappedBooks = new ArrayList<Book>();
        books.forEach(book -> mappedBooks.add(bookEntityToBook(book)));
        return mappedBooks;
    }

    @Override
    public Book update(final Book book) {
        final BookEntity bookEntity = bookToBookEntity(book);
        final BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return bookEntityToBook(savedBookEntity);
    }

    @Override
    public void delete(Book book) {
        final BookEntity bookEntity = bookToBookEntity(book);
        bookRepository.delete(bookEntity);
    }

    private BookEntity bookToBookEntity(Book book) {
        return BookEntity.builder()
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .title(book.getTitle())
                .build();
    }

    private Book bookEntityToBook(BookEntity bookEntity) {
        return Book.builder()
                .isbn(bookEntity.getIsbn())
                .author(bookEntity.getAuthor())
                .title(bookEntity.getTitle())
                .build();
    }
}
