package com.codewithfibbee.wizerbooklibraryapi.util;

import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import com.codewithfibbee.wizerbooklibraryapi.model.Category;

import java.util.Date;

public class TestModels {

    public static Book book(String title,
                            String author,
                            String description,
                            String publisher){
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setAuthor(author);
        book.setDescription(description);
        book.setDateAdded(new Date());
        book.setPublisher(publisher);
        return book;
    }

    public static Category category(String name){
        Category category = new Category();
        category.setName(name);
        return category;
    }
}
