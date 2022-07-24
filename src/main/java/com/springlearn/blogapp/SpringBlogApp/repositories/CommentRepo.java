package com.springlearn.blogapp.SpringBlogApp.repositories;

import com.springlearn.blogapp.SpringBlogApp.entities.Comment;
import com.springlearn.blogapp.SpringBlogApp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findByPost(Post post);
}
