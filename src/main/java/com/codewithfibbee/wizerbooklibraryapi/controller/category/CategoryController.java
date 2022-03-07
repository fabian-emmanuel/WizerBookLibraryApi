package com.codewithfibbee.wizerbooklibraryapi.controller.category;

import com.codewithfibbee.wizerbooklibraryapi.dtos.CategoryDto;
import com.codewithfibbee.wizerbooklibraryapi.model.Category;
import com.codewithfibbee.wizerbooklibraryapi.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("${api.basepath}/categories")
@RequiredArgsConstructor
public class CategoryController implements ICategoryController {
    private final ICategoryService iCategoryService;


    @Override
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().body(this.iCategoryService.createCategory(categoryDto));
    }

    @Override
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> editCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long categoryId) {
        return ResponseEntity.ok().body(this.iCategoryService.updateCategory(categoryId, categoryDto));
    }

    @Override
    @GetMapping
    public ResponseEntity<Collection<Category>> listCategories() {
        return ResponseEntity.ok().body(this.iCategoryService.listAllCategories());
    }

    @Override
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok().body(this.iCategoryService.getACategory(categoryId));
    }

    @Override
    @PostMapping("/{bookId}/category/{categoryId}")
    public ResponseEntity<Object> addBookToCategory(@PathVariable Long bookId,@PathVariable Long categoryId) {
        this.iCategoryService.addBookToCategory(bookId, categoryId);
        return ResponseEntity.ok().body("Added Successfully");
    }

    @Override
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long categoryId) {
        this.iCategoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().body("Deleted Successfully");
    }
}
