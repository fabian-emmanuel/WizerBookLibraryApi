package com.codewithfibbee.wizerbooklibraryapi.repository;

import com.codewithfibbee.wizerbooklibraryapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c")
    Collection<Category> listAllCategories();
}
