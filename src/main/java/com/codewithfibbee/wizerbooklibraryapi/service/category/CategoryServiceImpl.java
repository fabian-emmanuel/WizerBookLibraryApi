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
public class CategoryServiceImpl implements ICategoryService{
    private final ICategoryRepository iCategoryRepository;
    private final IBookRepository iBookRepository;

    @Override
    public Category addCategory(CategoryDto categoryDto) {
        var category = mapper().map(categoryDto, Category.class);
        category.setDateCreated(new Date());
        this.iCategoryRepository.save(category);
        return category;
    }

    @Override
    public Category editCategory(Long categoryId, CategoryDto categoryDto) {
        var category = this.iCategoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Resource Not Found"));
        category.setName(categoryDto.getName());
        this.iCategoryRepository.save(category);
        return category;
    }

    @Override
    public Collection<Category> listAllCategories() {
        return this.iCategoryRepository.findAll();
    }

    @Override
    public Category getACategory(Long categoryId) {
        return this.iCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
    }

    @Override
    public void addBookToCategory(Long bookId, Long categoryId) {
        var book = this.iBookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        var category = this.iCategoryRepository.findById(categoryId);
        category.ifPresentOrElse(
                value -> value.addBookToCategory(book),
                () -> {throw new ResourceNotFoundException("Resource Not Found");
                });
    }

    @Override
    public void deleteCategory(Long categoryId) {
        var category = this.iCategoryRepository.findById(categoryId);
        category.ifPresentOrElse(this.iCategoryRepository::delete,
                () -> {throw new ResourceNotFoundException("Resource Not Found");
        });

    }

    private ModelMapper mapper(){
        return new ModelMapper();
    }
}
