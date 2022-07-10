package com.springlearn.blogapp.SpringBlogApp.impl;

import com.springlearn.blogapp.SpringBlogApp.entities.Category;
import com.springlearn.blogapp.SpringBlogApp.entities.Post;
import com.springlearn.blogapp.SpringBlogApp.entities.User;
import com.springlearn.blogapp.SpringBlogApp.exceptions.ResourceNotFoundException;
import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.PostDto;
import com.springlearn.blogapp.SpringBlogApp.payloads.PostResponse;
import com.springlearn.blogapp.SpringBlogApp.repositories.CategoryRepo;
import com.springlearn.blogapp.SpringBlogApp.repositories.PostRepo;
import com.springlearn.blogapp.SpringBlogApp.repositories.UserRepo;
import com.springlearn.blogapp.SpringBlogApp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PageRanges;
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
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
        Sort sort=null;
if(sortDir.equalsIgnoreCase("asc")){
   sort = Sort.by(sortBy).ascending();
}else{
sort = Sort.by(sortBy).descending();
}
        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePosts = postRepo.findAll(p);
        List<Post> posts = pagePosts.getContent();
        List<PostDto> returnPosts = posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(returnPosts);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElememts(pagePosts.getTotalElements());
        postResponse.setLastPage(pagePosts.isLast());
        return postResponse;
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
    public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize) {

        Pageable p=PageRequest.of(pageNumber,pageSize);
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        Page<Post> pagePosts = postRepo.findByCategory(category,p);
List<Post> posts = pagePosts.getContent();
        List<PostDto> returnposts = posts.stream().map((post) ->(
            modelMapper.map(post,PostDto.class)
        )).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(returnposts);;
        postResponse.setTotalElememts(pagePosts.getTotalElements());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setPageNumber(pagePosts.getNumber());
        return postResponse;
    }

    @Override
    public PostResponse getPostByUserId(Integer userId,Integer pageNumber , Integer pageSize) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePosts = postRepo.findByUser(user,p);
        List<Post> posts = pagePosts.getContent();
        List<PostDto> returnposts = posts.stream().map((post) ->(
                modelMapper.map(post,PostDto.class)
        )).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(returnposts);;
        postResponse.setTotalElememts(pagePosts.getTotalElements());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setPageNumber(pagePosts.getNumber());
        return postResponse;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts = postRepo.findByTitleContaining(keyword);
//        List<Post> posts = postRepo.findByTitleContaining("%"+keyword+"%");
        List<PostDto> returnPosts = posts.stream().map((post->(modelMapper.map(post,PostDto.class)))).collect(Collectors.toList());
        return returnPosts;
            }
}
