package com.martin.books;

import com.martin.books.domain.Book;
import com.martin.books.domain.BookEntity;

public final class TestData {

    private TestData() {
    }

    public static Book testBook() {
        return Book.builder()
                .isbn("isbn-1234")
                .title("Watchmen")
                .author("Alan Moore")
                .build();
    }

    public static BookEntity testBookEntity() {
        return BookEntity.builder()
                .isbn("isbn-1234")
                .title("Watchmen")
                .author("Alan Moore")
                .build();
    }
}
