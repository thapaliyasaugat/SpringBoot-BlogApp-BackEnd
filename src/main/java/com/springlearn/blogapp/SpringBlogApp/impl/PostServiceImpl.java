package com.springlearn.blogapp.SpringBlogApp.impl;

import com.springlearn.blogapp.SpringBlogApp.entities.Category;
import com.springlearn.blogapp.SpringBlogApp.entities.Post;
import com.springlearn.blogapp.SpringBlogApp.entities.User;
import com.springlearn.blogapp.SpringBlogApp.exceptions.ResourceNotFoundException;
import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.PostDto;
import com.springlearn.blogapp.SpringBlogApp.repositories.CategoryRepo;
import com.springlearn.blogapp.SpringBlogApp.repositories.PostRepo;
import com.springlearn.blogapp.SpringBlogApp.repositories.UserRepo;
import com.springlearn.blogapp.SpringBlogApp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired private UserRepo userRepo;
    @Autowired private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId , Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
       User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepo.save(post);
        return modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        Post post = postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(category);
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        List<PostDto> returnPosts = posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return returnPosts;
    }

    @Override
    public ApiResponse deletePost(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
postRepo.deleteById(id);
ApiResponse response = new ApiResponse();
response.setMessage("post deleted sucessfully.");
response.setSuccess(true);
        return response;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        List<Post> posts = postRepo.findByCategory(category);
        List<PostDto> returnposts = posts.stream().map((post) ->(
            modelMapper.map(post,PostDto.class)
        )).collect(Collectors.toList());
        return returnposts;
    }

    @Override
    public List<PostDto> getPostByUserId(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> returnposts = posts.stream().map((post) ->(
                modelMapper.map(post,PostDto.class)
        )).collect(Collectors.toList());
        return returnposts;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }
}
