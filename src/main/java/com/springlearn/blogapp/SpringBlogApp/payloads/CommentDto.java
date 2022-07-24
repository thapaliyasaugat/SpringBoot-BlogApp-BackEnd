package com.springlearn.blogapp.SpringBlogApp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
private int id;
@NotEmpty
@Size(max = 10000)
private String text;
private UserDto user;
private PostDto post;
}
