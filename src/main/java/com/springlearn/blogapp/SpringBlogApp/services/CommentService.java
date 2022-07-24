package com.springlearn.blogapp.SpringBlogApp.services;

import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.CommentDto;
import com.springlearn.blogapp.SpringBlogApp.payloads.PostDto;
import com.springlearn.blogapp.SpringBlogApp.payloads.UserDto;

import java.util.List;

public interface CommentService {
CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);
ApiResponse deleteComment(Integer commentId, Integer userId);
CommentDto updateComment(CommentDto commentDto, Integer commentId ,Integer userId);
List<CommentDto> getAllComments(Integer postId);
}
