package com.springlearn.blogapp.SpringBlogApp.services;

import com.springlearn.blogapp.SpringBlogApp.entities.Category;
import com.springlearn.blogapp.SpringBlogApp.entities.Post;
import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.PostDto;
import com.springlearn.blogapp.SpringBlogApp.payloads.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
PostDto createPost(PostDto postDto, Integer categoryId , Integer userId);
PostDto updatePost(PostDto postDto , Integer id,Integer categoryId);
PostDto getPostById(Integer id);
PostResponse getAllPosts(Integer pageNumber , Integer pageSize,String sortBy, String sortDir);
ApiResponse deletePost(Integer id);
PostResponse getPostByCategory(Integer categoryId,Integer pageNumber , Integer pageSize);
PostResponse getPostByUserId(Integer userId,Integer pageNumber , Integer pageSize);
List<PostDto> searchPost(String keyword);

}
