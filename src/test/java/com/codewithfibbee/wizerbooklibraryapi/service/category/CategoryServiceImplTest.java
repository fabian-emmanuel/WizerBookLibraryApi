package com.codewithfibbee.wizerbooklibraryapi.service.category;

import com.codewithfibbee.wizerbooklibraryapi.dtos.CategoryDto;
import com.codewithfibbee.wizerbooklibraryapi.exceptions.ResourceNotFoundException;
import com.codewithfibbee.wizerbooklibraryapi.model.Book;
import com.codewithfibbee.wizerbooklibraryapi.model.Category;
import com.codewithfibbee.wizerbooklibraryapi.repository.IBookRepository;
import com.codewithfibbee.wizerbooklibraryapi.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private ICategoryRepository repository;

    @Mock
    private IBookRepository iBookRepository;

    @InjectMocks
    @Spy
    private CategoryServiceImpl service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldCreateCategorySuccessfully() {
        // Setup
        final CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("name");

        final Category category = new Category();
        category.setId(0L);
        category.setName("name");

        when(repository.save(any(Category.class))).thenReturn(category);

        // Run the test
        Category result = service.createCategory(categoryDto);

        // Verify the results
        verify(repository).save(any(Category.class));
        assertNotNull(result);
    }

    @Test
    void shouldUpdateCategorySuccesfully() {
        // Setup
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("NAME");

        Category category1 = new Category();
        category1.setId(0L);
        category1.setName("name");

        when(repository.findById(anyLong())).thenReturn(Optional.of(category1));

        // Run the test
        final Category result = service.updateCategory(category1.getId(), categoryDto);

        // Verify the results
        verify(repository).save(any(Category.class));
        assertNotNull(result);
        assertEquals("NAME", result.getName());

    }

    @Test
    void testUpdateCategory_ShouldThrowResourceNotFoundWhenCategoryIsNotPresent() {
        // Setup
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("NAME");

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Run the test
        assertThrows(ResourceNotFoundException.class,() -> this.service.updateCategory(0L, categoryDto));
    }

    @Test
    void shouldListAllCategoriesSuccessfully() {
        assertNotNull(this.service.listAllCategories());
    }

    @Test
    void testListAllCategories_ShouldReturnEmptyListWhenNoCategoryIsPresent() {
        // Setup
        when(repository.listAllCategories()).thenReturn(Collections.emptyList());

        // Run the test
        Collection<Category> result = service.listAllCategories();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldRetrieveACategory() {
        // Setup
        // Configure ICategoryRepository.findById(...).
        Category category1 = new Category();
        category1.setId(0L);
        category1.setName("name");

        when(repository.findById(0L)).thenReturn(Optional.of(category1));

        // Run the test
        // Verify the results
        assertNotNull(this.service.getACategory(category1.getId()));
    }


    @Test
    void shouldAddBookToCategorySuccessfully() {
        // Setup
        // Configure IBookRepository.findById(...).
        Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setAuthor("author");
        book1.setDescription("description");
        book1.setPublisher("publisher");

        Category category = new Category();
        category.setId(0L);
        category.setName("name");

        when(repository.findById(anyLong())).thenReturn(Optional.of(category));
        when(iBookRepository.findById(anyLong())).thenReturn(Optional.of(book1));

        // Run the test
        // Verify the results
        this.service.addBookToCategory(0L, 0L);
        assertNotNull(category.getBooks());
    }

    @Test
    void shouldRemoveBookFromCategorySuccessfully() {
        // Setup
        final Book book1 = new Book();
        book1.setId(0L);
        book1.setTitle("title");
        book1.setAuthor("author");
        book1.setDescription("description");
        book1.setPublisher("publisher");

        Category category = new Category();
        category.setId(0L);
        category.setName("name");
        category.addBookToCategory(book1);


        when(repository.findById(0L)).thenReturn(Optional.of(category));
        when(iBookRepository.findById(anyLong())).thenReturn(Optional.of(book1));

        // Run the test
        service.removeBookFromCategory(book1.getId(), 0L);
        assertEquals(0, this.service.getACategory(0L).getBooks().size());

    }

    @Test
    void shouldDeleteCategorySuccessfully() {
        // Setup
        final Category category1 = new Category();
        category1.setId(0L);
        category1.setName("name");
        category1.setDateCreated(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        when(repository.findById(0L)).thenReturn(Optional.of(category1));

        // Run the test
        service.deleteCategory(0L);

        // Verify the results
        verify(repository).delete(any(Category.class));
    }
}
