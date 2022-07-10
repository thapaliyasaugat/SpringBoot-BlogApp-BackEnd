package com.springlearn.blogapp.SpringBlogApp.controllers;

import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.PostDto;
import com.springlearn.blogapp.SpringBlogApp.payloads.PostResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.UserDto;
import com.springlearn.blogapp.SpringBlogApp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity createPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") Integer userId , @PathVariable("categoryId") Integer categoryId){
        PostDto post = postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @PutMapping("/post/{postId}/category/{categoryId}/posts")
    public ResponseEntity updatePost(@Valid @RequestBody PostDto postDto,@PathVariable("postId") Integer postId , @PathVariable("categoryId") Integer categoryId){
        PostDto post = postService.updatePost(postDto,postId,categoryId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity getpostbyId(@PathVariable("postId") Integer postId){
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity gelAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value = "sortBy" ,defaultValue = "postId",required = false) String sortBy,
            @RequestParam(value = "sortDir" ,defaultValue = "asc",required = false) String sortDir
    ){
        PostResponse posts = postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity gePostByUser(@PathVariable("userId") Integer userid,
    @RequestParam(value = "pageNumber" ,defaultValue = "0",required = false) Integer pageNumber,
    @RequestParam(value = "pageSize" ,defaultValue = "10",required = false) Integer pageSize
    ){
        PostResponse posts= postService.getPostByUserId(userid,pageNumber,pageSize);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    } @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity gePostByCategory(@PathVariable("categoryId") Integer categoryId,
                                           @RequestParam(value = "pageNumber" ,defaultValue = "0",required = false) Integer pageNumber,
                                           @RequestParam(value = "pageSize" ,defaultValue = "10",required = false) Integer pageSize
    ){
        PostResponse posts= postService.getPostByCategory(categoryId,pageNumber,pageSize);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity deletePostById(@PathVariable("postId") Integer postId){
        ApiResponse responce = postService.deletePost(postId);
        return new ResponseEntity<>(responce,HttpStatus.OK);
    }
    @GetMapping("/posts/contains/{keyword}")
    public ResponseEntity searchPostByKeyword(@PathVariable("keyword") String keyword){
        List<PostDto> posts = postService.searchPost(keyword);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
}
