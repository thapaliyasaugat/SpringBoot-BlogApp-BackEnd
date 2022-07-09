package com.springlearn.blogapp.SpringBlogApp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldname;
    long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldname, int fieldValue) {
        super(String.format("%s not found with %s : %d",resourceName,fieldname,fieldValue));
        this.resourceName = resourceName;
        this.fieldname = fieldname;
        this.fieldValue = fieldValue;
    }
}
