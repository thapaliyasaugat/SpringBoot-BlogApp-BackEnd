package com.springlearn.blogapp.SpringBlogApp.services;

import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    //create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    //delete
    ApiResponse deleteCategory(Integer categoryId);
    //get
    CategoryDto getCategory(Integer categoryId);
    //getall
    List<CategoryDto> getListOfAllCategory();
}
