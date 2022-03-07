package com.codewithfibbee.wizerbooklibraryapi.controller.book;


import com.codewithfibbee.wizerbooklibraryapi.dtos.BookDto;
import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import com.codewithfibbee.wizerbooklibraryapi.service.book.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("${api.basepath}/books")
@RequiredArgsConstructor
public class BookController implements IBookController {
    private final IBookService iBookService;

    @Override
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok().body(this.iBookService.createBook(bookDto));
    }

    @Override
    @PutMapping("/{bookId}")
    public ResponseEntity<Book> editBook(@RequestBody BookDto bookDto, @PathVariable Long bookId) {
        return ResponseEntity.ok().body(this.iBookService.editBook(bookId, bookDto));
    }

    @Override
    @GetMapping
    public ResponseEntity<Collection<Book>> listBooks() {
        return ResponseEntity.ok().body(this.iBookService.retrieveAllBooks());
    }

    @Override
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok().body(this.iBookService.retrieveBook(bookId));
    }

    @Override
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long bookId) {
        this.iBookService.deleteBook(bookId);
        return ResponseEntity.ok().body("Deleted Successfully");
    }
}
