package com.springlearn.blogapp.SpringBlogApp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private int id;
@NotEmpty
@Size(message = "name can't be empty")
    private String name;
@Email
@NotEmpty(message = "email is invalid.")
    private String email;
@NotNull
@Size(min = 6 , message = "password must be greater than 6 characters.")
    private String password;
    private String about;
}
