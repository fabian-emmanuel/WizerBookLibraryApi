package com.codewithfibbee.wizerbooklibraryapi.service.category;

import com.codewithfibbee.wizerbooklibraryapi.dtos.CategoryDto;
import com.codewithfibbee.wizerbooklibraryapi.exceptions.ResourceNotFoundException;
import com.codewithfibbee.wizerbooklibraryapi.model.Category;
import com.codewithfibbee.wizerbooklibraryapi.repository.IBookRepository;
import com.codewithfibbee.wizerbooklibraryapi.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository iCategoryRepository;
    private final IBookRepository iBookRepository;

    private static final String CATEGORY_NOT_FOUND_ERROR_MSG = "Category Not Found";
    private static final String BOOK_NOT_FOUND_ERROR_MSG = "Book Not Found";



    @Override
    public Category createCategory(CategoryDto categoryDto) {
        var category = mapper().map(categoryDto, Category.class);
        category.setDateCreated(new Date());
        this.iCategoryRepository.save(category);
        return category;
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryDto categoryDto) {
        var category = this.iCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND_ERROR_MSG));
        category.setName(categoryDto.getName());
        this.iCategoryRepository.save(category);
        return category;
    }

    @Override
    public Collection<Category> listAllCategories() {
        return this.iCategoryRepository.listAllCategories();
    }

    @Override
    public Category getACategory(Long categoryId) {
        return this.iCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND_ERROR_MSG));
    }

    @Override
    public void addBookToCategory(Long bookId, Long categoryId) {
        var book = this.iBookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND_ERROR_MSG));

        this.iCategoryRepository.findById(categoryId)
                .ifPresentOrElse(
                        value -> value.addBookToCategory(book), CategoryServiceImpl::categoryNotFound);
    }

    @Override
    public void removeBookFromCategory(Long bookId, Long categoryId) {
        var book = this.iBookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND_ERROR_MSG));

        this.iCategoryRepository.findById(categoryId)
                .ifPresentOrElse(value -> value.removeBookFromCategory(book), CategoryServiceImpl::categoryNotFound);

    }

    @Override
    public void deleteCategory(Long categoryId) {
        this.iCategoryRepository.findById(categoryId).
                ifPresentOrElse(this.iCategoryRepository::delete, CategoryServiceImpl::categoryNotFound);

    }

    private static void categoryNotFound() {
        throw new ResourceNotFoundException(CATEGORY_NOT_FOUND_ERROR_MSG);
    }

    private ModelMapper mapper() {
        return new ModelMapper();
    }
}
