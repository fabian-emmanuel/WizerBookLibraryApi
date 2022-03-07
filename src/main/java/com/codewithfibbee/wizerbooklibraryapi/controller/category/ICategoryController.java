package com.codewithfibbee.wizerbooklibraryapi.controller.category;

import com.codewithfibbee.wizerbooklibraryapi.dtos.CategoryDto;
import com.codewithfibbee.wizerbooklibraryapi.model.Category;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;

import javax.validation.Valid;
import java.util.Collection;

@Api(tags = {"Category Controller"})
public interface ICategoryController {

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created successfully"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @ApiOperation(value = "Create Category")
    ResponseEntity<Category> addCategory(@Valid CategoryDto categoryDto);

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource edited successfully"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @ApiOperation(value = "Edit Category")
    ResponseEntity<Category> editCategory(@Valid CategoryDto categoryDto, Long categoryId);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resources retrieved successfully")})
    @ApiOperation(value = "List Categories")
    ResponseEntity<Collection<Category>> listCategories();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource retrieved successfully")})
    @ApiOperation(value = "Get a Category")
    ResponseEntity<Category> getCategory(Long categoryId);

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource added to Category successfully"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @ApiOperation(value = "Add Book To Category")
    ResponseEntity<Object> addBookToCategory(Long bookId, Long categoryId);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource deleted successfully"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @ApiOperation(value = "Delete Category")
    ResponseEntity<Object> deleteCategory(Long categoryId);

}
