package com.springlearn.blogapp.SpringBlogApp.controllers;

import com.springlearn.blogapp.SpringBlogApp.entities.Category;
import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.CategoryDto;
import com.springlearn.blogapp.SpringBlogApp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    @PutMapping("/{catId}")
    public ResponseEntity updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("catId") int id){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto,id);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }
@DeleteMapping("/{catId}")
    public ResponseEntity deleteCategory(@PathVariable("catId") int id){
        ApiResponse response = categoryService.deleteCategory(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/{catId}")
    public ResponseEntity getCategoryById(@PathVariable("catId") int id){
        CategoryDto category = categoryService.getCategory(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getAllCategory(){
        List<CategoryDto> categories = categoryService.getListOfAllCategory();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

}
