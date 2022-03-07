package com.codewithfibbee.wizerbooklibraryapi.service.book;

import com.codewithfibbee.wizerbooklibraryapi.dtos.BookDto;
import com.codewithfibbee.wizerbooklibraryapi.exceptions.ResourceNotFoundException;
import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import com.codewithfibbee.wizerbooklibraryapi.repository.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private IBookRepository repository;

    @InjectMocks
    @Spy
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldAddBookSuccessfully() {
        // Setup
        BookDto bookDto = new BookDto();
        bookDto.setTitle("title");
        bookDto.setDescription("description");
        bookDto.setPublisher("publisher");
        bookDto.setAuthor("author");


        when(repository.save(any(Book.class))).thenReturn(any(Book.class));

        // Run the test
        Book result = bookService.createBook(bookDto);

        // Verify the results
        verify(repository).save(any(Book.class));
        assertNotNull(result);
    }

    @Test
    void shouldEditBookSuccessfully() {
        // Setup
        BookDto bookDto = new BookDto();
        bookDto.setTitle("TITLE");
        bookDto.setDescription("DESCRIPTION");
        bookDto.setPublisher("PUBLISHER");
        bookDto.setAuthor("AUTHOR");

        Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setAuthor("author");
        book1.setDescription("description");
        book1.setPublisher("publisher");

        when(repository.findById(anyLong())).thenReturn(Optional.of(book1));

        // Run the test
        Book result = bookService.editBook(book1.getId(), bookDto);

        // Verify the results
        assertNotNull(result);
        assertEquals("TITLE", result.getTitle());
        assertEquals("AUTHOR", result.getAuthor());
        assertEquals("PUBLISHER", result.getPublisher());
        assertEquals("AUTHOR", result.getAuthor());


    }

    @Test
    void testEditBook_ShouldThrowResourceNotFoundWhenBookIsNotPresent() {
        // Setup
        BookDto bookDto = new BookDto();
        bookDto.setTitle("title");
        bookDto.setDescription("description");
        bookDto.setPublisher("publisher");
        bookDto.setAuthor("author");

        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        // Run the test
        assertThrows(ResourceNotFoundException.class,() -> this.bookService.editBook(0L, bookDto));
    }

    @Test
    void shouldListAllBooksSuccessfully() {
        // Run the test
        // Verify the results
        assertNotNull(this.bookService.retrieveAllBooks());

    }


    @Test
    void shouldRetrieveABookSuccessfully() {
        // Setup
        Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setAuthor("author");
        book1.setDescription("description");
        book1.setPublisher("publisher");

        when(repository.findById(0L)).thenReturn(Optional.of(book1));
        // Run the test
        // Verify the results
        assertNotNull(this.bookService.retrieveBook(book1.getId()));
    }

    @Test
    void shouldDeleteBookSuccessfully() {
        // Setup
        // Configure IBookRepository.findById(...).
        Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setAuthor("author");
        book1.setDescription("description");
        book1.setPublisher("publisher");

        when(repository.findById(0L)).thenReturn(Optional.of(book1));

        // Run the test
        bookService.deleteBook(0L);

        // Verify the results
        verify(repository).delete(any(Book.class));
    }
}
