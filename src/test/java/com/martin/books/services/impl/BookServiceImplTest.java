package com.martin.books.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.martin.books.TestData;
import com.martin.books.domain.Book;
import com.martin.books.domain.BookEntity;
import com.martin.books.repositories.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl underTest;

    @Test
    public void testThatBookIsSaved() {
        final Book book = TestData.testBook();
        final BookEntity bookEntity = TestData.testBookEntity();

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);
        final Book result = underTest.create(book);
        assertEquals(book, result);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoBook() {
        final String isbn = "not-found-isbn";

        when(bookRepository.findById(eq(isbn))).thenReturn(Optional.empty());
        final Optional<Book> result = underTest.findById(isbn);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatFindByIdReturnsBookWhenExists() {
        final Book book = TestData.testBook();
        final BookEntity bookEntity = TestData.testBookEntity();

        when(bookRepository.findById(eq(book.getIsbn()))).thenReturn(Optional.of(bookEntity));
        final Optional<Book> result = underTest.findById(book.getIsbn());
        assertEquals(Optional.of(book), result);
    }

    @Test
    public void testThatFindAllBooksReturnsAllBooks() {
        final Book book = TestData.testBook();
        final List<Book> allBooks = new ArrayList<Book>();
        allBooks.add(book);

        final BookEntity bookEntity = TestData.testBookEntity();
        final List<BookEntity> allBookEntities = new ArrayList<BookEntity>();
        allBookEntities.add(bookEntity);

        when(bookRepository.findAll()).thenReturn(allBookEntities);
        final List<Book> result = underTest.findAll();
        assertEquals(allBooks, result);
    }

    @Test
    public void testThatBookIsUpdated() {
        final String newAuthor = "Neil Gaminan";
        final Book book = TestData.testBook();
        book.setAuthor(newAuthor);

        final BookEntity bookEntity = TestData.testBookEntity();
        bookEntity.setAuthor(newAuthor);

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);
        final Book result = underTest.update(book);
        
        assertEquals(book, result);
    }

    @Test
    public void testThatBookIsDeleted() {
        final Book book = TestData.testBook();
        final BookEntity bookEntity = TestData.testBookEntity();

        underTest.delete(book);

        verify(bookRepository, times(1)).delete(bookEntity);
    }
}
