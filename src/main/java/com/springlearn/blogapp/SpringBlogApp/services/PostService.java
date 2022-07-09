package com.springlearn.blogapp.SpringBlogApp.services;

import com.springlearn.blogapp.SpringBlogApp.entities.Category;
import com.springlearn.blogapp.SpringBlogApp.entities.Post;
import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
PostDto createPost(PostDto postDto, Integer categoryId , Integer userId);
PostDto updatePost(PostDto postDto , Integer id,Integer categoryId);
PostDto getPostById(Integer id);
List<PostDto> getAllPosts();
ApiResponse deletePost(Integer id);
List<PostDto> getPostByCategory(Integer categoryId);
List<PostDto> getPostByUserId(Integer userId);
List<PostDto> searchPost(String keyword);

}
