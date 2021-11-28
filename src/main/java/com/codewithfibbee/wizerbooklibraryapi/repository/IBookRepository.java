package com.codewithfibbee.wizerbooklibraryapi.repository;

import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface IBookRepository extends JpaRepository<Book, Long> {
}
