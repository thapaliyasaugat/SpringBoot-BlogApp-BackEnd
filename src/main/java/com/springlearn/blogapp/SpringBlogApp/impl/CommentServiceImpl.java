package com.springlearn.blogapp.SpringBlogApp.impl;

import com.springlearn.blogapp.SpringBlogApp.entities.Comment;
import com.springlearn.blogapp.SpringBlogApp.entities.Post;
import com.springlearn.blogapp.SpringBlogApp.entities.User;
import com.springlearn.blogapp.SpringBlogApp.exceptions.ResourceNotFoundException;
import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.CommentDto;
import com.springlearn.blogapp.SpringBlogApp.repositories.CommentRepo;
import com.springlearn.blogapp.SpringBlogApp.repositories.PostRepo;
import com.springlearn.blogapp.SpringBlogApp.repositories.UserRepo;
import com.springlearn.blogapp.SpringBlogApp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedPost = commentRepo.save(comment);
        return this.modelMapper.map(savedPost,CommentDto.class);
    }

    @Override
    public ApiResponse deleteComment(Integer commentId,Integer userId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        if(comment.getUser().equals(user)){
        commentRepo.deleteById(commentId);
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("comment with id " +commentId+" deleted");
        }
            throw new RuntimeException("You are not allowed to delete this item.");
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId, Integer userId) {
        Comment comment=commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        if(comment.getUser().equals(user)){
            comment.setText(commentDto.getText());
            Comment updatedComment = commentRepo.save(comment);
            return modelMapper.map(updatedComment,CommentDto.class);
        }
            throw new RuntimeException("You are not alowed to update this item.");
    }

    @Override
    public List<CommentDto> getAllComments(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        List<Comment> comments= commentRepo.findByPost(post);
        List<CommentDto> returnComments = comments.stream().map((comment)->modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return returnComments;
    }
}
