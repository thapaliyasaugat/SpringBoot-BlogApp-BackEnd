package com.springlearn.blogapp.SpringBlogApp.controllers;

import com.springlearn.blogapp.SpringBlogApp.impl.UserServiceImpl;
import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.UserDto;
import com.springlearn.blogapp.SpringBlogApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserDto>  createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDetail = userService.createUser(userDto);
        return new ResponseEntity<>(userDetail, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.geAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<UserDto> userById(@PathVariable("id") int id){
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<ApiResponse> deleteUserById(@PathVariable("id") int id){
        ApiResponse apiResponse = userService.deleteUser(id);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    private ResponseEntity<UserDto> updateUserById(@PathVariable("id") int id,@Valid @RequestBody UserDto userDto){
        UserDto updatedUser = userService.updateUser(userDto,id);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
}
