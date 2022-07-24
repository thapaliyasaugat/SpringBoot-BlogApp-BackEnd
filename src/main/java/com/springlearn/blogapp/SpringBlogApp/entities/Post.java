package com.springlearn.blogapp.SpringBlogApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="post")
@Getter
@Setter
public class Post {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
@Column(name = "post_title", length = 100,nullable = false)
private String title;
@Column(length = 100000)
private String content;
private String imageName;
private Date addedDate;

@ManyToOne
@JoinColumn(name = "category_id")
private Category category;

@JsonIgnore
@ManyToOne
private User user;

@JsonIgnore
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
private List<Comment> comments = new ArrayList<>();
}
