package com.codewithfibbee.wizerbooklibraryapi.repository;

import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface IBookRepository extends JpaRepository<Book, Long> {

    @Query("select b from  Book b")
    Collection<Book> listAllBooks();
}
