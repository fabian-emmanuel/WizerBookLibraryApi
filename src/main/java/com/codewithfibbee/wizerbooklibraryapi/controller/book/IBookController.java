package com.codewithfibbee.wizerbooklibraryapi.controller.book;

import com.codewithfibbee.wizerbooklibraryapi.dtos.BookDto;
import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;

import javax.validation.Valid;
import java.util.Collection;

@Api(tags = {"Book Controller"})
public interface IBookController {
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource Added successfully"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @ApiOperation(value = "Add Book")
    ResponseEntity<Book> addBook(@Valid BookDto bookDto);

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource edited successfully"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @ApiOperation(value = "Edit Book")
    ResponseEntity<Book> editBook(@Valid BookDto bookDto, Long bookId);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resources retrieved successfully")})
    @ApiOperation(value = "List Books")
    ResponseEntity<Collection<Book>> listBooks();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource retrieved successfully")})
    @ApiOperation(value = "Get a Book")
    ResponseEntity<Book> getBook(Long bookId);


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource deleted successfully"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @ApiOperation(value = "Delete book")
    ResponseEntity<Object> deleteBook(Long bookId);

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource added to Favourite successfully"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @ApiOperation(value = "Add Book To Favourite")
    ResponseEntity<Object> addBookToFavourite(Long bookId);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resources retrieved successfully")})
    @ApiOperation(value = "List Favourites")
    ResponseEntity<Collection<Book>> listFavourites();

}
