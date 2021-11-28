package com.codewithfibbee.wizerbooklibraryapi.service.category;

import com.codewithfibbee.wizerbooklibraryapi.dtos.CategoryDto;
import com.codewithfibbee.wizerbooklibraryapi.model.Category;

import java.util.Collection;

public interface ICategoryService {
    Category addCategory(CategoryDto categoryDto);
    Category editCategory(Long categoryId, CategoryDto categoryDto);
    Collection<Category> listAllCategories();
    Category getACategory(Long categoryId);
    void addBookToCategory(Long bookId, Long categoryId);
    void deleteCategory(Long categoryId);


}
