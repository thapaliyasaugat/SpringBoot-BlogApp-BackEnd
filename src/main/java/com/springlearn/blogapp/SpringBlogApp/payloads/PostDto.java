package com.springlearn.blogapp.SpringBlogApp.payloads;

import com.springlearn.blogapp.SpringBlogApp.entities.Category;
import com.springlearn.blogapp.SpringBlogApp.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
private Integer postId;
    @NotEmpty
private String title;
    @NotEmpty
private String content;
    private Date addedDate;
    private String imageName;
    private CategoryDto category;
    private UserDto user;

}
