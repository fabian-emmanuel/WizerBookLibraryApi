package com.codewithfibbee.wizerbooklibraryapi.dtos;

import lombok.Data;

@Data
public class BookDto {
    String title;
    String description;
    String publisher;
    String author;
}
