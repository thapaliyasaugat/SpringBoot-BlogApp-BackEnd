package com.springlearn.blogapp.SpringBlogApp.controllers;

import com.springlearn.blogapp.SpringBlogApp.entities.Comment;
import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.CommentDto;
import com.springlearn.blogapp.SpringBlogApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //create comment
    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto,
                                                    @PathVariable("postId") Integer postId,
                                                    @PathVariable("userId") Integer userId) {
        CommentDto createdComment = commentService.createComment(commentDto, userId, postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    //update comment
    @PutMapping("/{commentId}/user/{userId}")
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto,
                                                    @PathVariable("commentId") Integer commentId,
                                                    @PathVariable("userId") Integer userId) {
        CommentDto updatedComment = commentService.updateComment(commentDto, commentId, userId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);

    }

    //delete comment
    @DeleteMapping("/{commentId}/user/{userId}")
    public ResponseEntity deleteCommentById(@PathVariable("commentId") Integer commentId
            , @PathVariable("userId") Integer userId
    ) {
        ApiResponse response = commentService.deleteComment(commentId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get all comment by post
    @GetMapping("/post/{postId}")
    public ResponseEntity commentByPostId(@PathVariable("postId") Integer postId) {
        List<CommentDto> comments = commentService.getAllComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
