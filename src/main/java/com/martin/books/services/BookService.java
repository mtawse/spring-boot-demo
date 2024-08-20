package com.martin.books.services;

import java.util.List;
import java.util.Optional;

import com.martin.books.domain.Book;

public interface BookService {

    Book create(Book book);

    List<Book> findAll();

    Optional<Book> findById(String isbn);

    Book update(Book book);

    void delete(Book book);
}
