package com.springlearn.blogapp.SpringBlogApp.repositories;

import com.springlearn.blogapp.SpringBlogApp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
