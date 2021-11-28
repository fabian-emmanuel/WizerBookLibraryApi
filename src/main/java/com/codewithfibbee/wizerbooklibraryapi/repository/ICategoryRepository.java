package com.codewithfibbee.wizerbooklibraryapi.repository;

import com.codewithfibbee.wizerbooklibraryapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
