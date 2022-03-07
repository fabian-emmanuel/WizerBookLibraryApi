package com.codewithfibbee.wizerbooklibraryapi.service.book;

import com.codewithfibbee.wizerbooklibraryapi.dtos.BookDto;
import com.codewithfibbee.wizerbooklibraryapi.model.Book;

import java.util.Collection;

public interface IBookService {
    Book createBook(BookDto bookDto);

    Book editBook(Long bookId, BookDto bookDto);

    Collection<Book> retrieveAllBooks();

    Book retrieveBook(Long bookId);

    void deleteBook(Long bookId);
}
