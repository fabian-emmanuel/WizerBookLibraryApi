package com.codewithfibbee.wizerbooklibraryapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Date dateCreated;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Book> books = null;

    public void addBookToCategory(Book book){
        createCategoryListIfNull();
        book.setCategory(this);
        this.books.add(book);
    }

    public void removeBookFromCategory(Book book){
        createCategoryListIfNull();
        book.setCategory(null);
        this.books.remove(book);
    }

    private void createCategoryListIfNull() {
        if(books == null){
            books = new LinkedHashSet<>();
        }
    }
}
