package com.springlearn.blogapp.SpringBlogApp.repositories;

import com.springlearn.blogapp.SpringBlogApp.entities.Category;
import com.springlearn.blogapp.SpringBlogApp.entities.Post;
import com.springlearn.blogapp.SpringBlogApp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
Page<Post> findByUser(User user,Pageable pageable);
Page<Post> findByCategory(Category category, Pageable pageable);
List<Post> findByTitleContaining(String keyword);

//@Query("select p from Post p where p.title like :key")
//    List<Post> findByTitle(@Param("key") String keyword);

}
