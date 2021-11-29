package com.codewithfibbee.wizerbooklibraryapi.service.book;

import com.codewithfibbee.wizerbooklibraryapi.dtos.BookDto;
import com.codewithfibbee.wizerbooklibraryapi.exceptions.ResourceNotFoundException;
import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import com.codewithfibbee.wizerbooklibraryapi.repository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService{

    private final IBookRepository iBookRepository;

    @Override
    public Book addBook(BookDto bookDto) {
        var book = mapper().map(bookDto, Book.class);
        book.setDateAdded(new Date());
        this.iBookRepository.save(book);
        return book;
    }

    @Override
    public Book editBook(Long bookId, BookDto bookDto) {
        var book = this.iBookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Resource Not Found"));
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setPublisher(bookDto.getPublisher());
        this.iBookRepository.save(book);
        return book;
    }

    @Override
    public Collection<Book> retrieveAllBooks() {
        return this.iBookRepository.findAll();
    }

    @Override
    public Book retrieveBook(Long bookId) {
        return this.iBookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Resource Not Found"));
    }

    @Override
    public void deleteBook(Long bookId) {
        var book = this.iBookRepository.findById(bookId);
        book.ifPresentOrElse(this.iBookRepository::delete,
                () -> {throw new ResourceNotFoundException("Resource Not Found");
        });
    }

    @Override
    public void addBookToFavourite(Long bookId) {
        var book = this.iBookRepository.findById(bookId);
        book.ifPresentOrElse(value -> value.addBookToFavourite(value),
                () -> {throw new ResourceNotFoundException("Resource Not Found");
        });
    }

    @Override
    public Collection<Book> retrieveAllFavouriteBooks() {
        return null;
    }

    private ModelMapper mapper(){
        return new ModelMapper();
    }
}
