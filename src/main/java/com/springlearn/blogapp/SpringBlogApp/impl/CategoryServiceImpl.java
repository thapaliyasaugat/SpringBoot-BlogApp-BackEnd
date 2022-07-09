package com.springlearn.blogapp.SpringBlogApp.impl;

import com.springlearn.blogapp.SpringBlogApp.entities.Category;
import com.springlearn.blogapp.SpringBlogApp.exceptions.ResourceNotFoundException;
import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.CategoryDto;
import com.springlearn.blogapp.SpringBlogApp.payloads.UserDto;
import com.springlearn.blogapp.SpringBlogApp.repositories.CategoryRepo;
import com.springlearn.blogapp.SpringBlogApp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryRepo.save(modelMapper.map(categoryDto,Category.class));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category updatedCategory = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        updatedCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        updatedCategory.setCategoryDescription(categoryDto.getCategoryDescription());
        Category returnValue = categoryRepo.save(updatedCategory);
        return modelMapper.map(returnValue,CategoryDto.class);
    }

    @Override
    public ApiResponse deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        categoryRepo.deleteById(categoryId);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setMessage("Category Deleted Sucessfully");
        return apiResponse;
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getListOfAllCategory() {
        List<Category> listOfCategories = categoryRepo.findAll();
        List<CategoryDto> finalList = listOfCategories.stream().map(category->modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return finalList;
    }
}
