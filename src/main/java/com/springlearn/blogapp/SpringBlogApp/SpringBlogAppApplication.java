package com.springlearn.blogapp.SpringBlogApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBlogAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBlogAppApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
}
}
