package com.codewithfibbee.wizerbooklibraryapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String description;

    @Column
    private String publisher;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateAdded;

    @OneToMany
    Set<Book> favourites = null;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;


    public void addBookToFavourite(Book book){
        createFavouriteListIfNull();
        this.favourites.add(book);
    }

    public void removeBookFromFavourite(Book book){
        createFavouriteListIfNull();
        this.favourites.remove(book);
    }

    private void createFavouriteListIfNull() {
        if(favourites == null){
            favourites = new LinkedHashSet<>();
        }
    }




}
