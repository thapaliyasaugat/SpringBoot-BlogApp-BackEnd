package com.springlearn.blogapp.SpringBlogApp.repositories;

import com.springlearn.blogapp.SpringBlogApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
