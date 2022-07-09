package com.springlearn.blogapp.SpringBlogApp.impl;

import com.springlearn.blogapp.SpringBlogApp.entities.User;
import com.springlearn.blogapp.SpringBlogApp.exceptions.ResourceNotFoundException;
import com.springlearn.blogapp.SpringBlogApp.payloads.ApiResponse;
import com.springlearn.blogapp.SpringBlogApp.payloads.UserDto;
import com.springlearn.blogapp.SpringBlogApp.repositories.UserRepo;
import com.springlearn.blogapp.SpringBlogApp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto user) {
        User userEntity = new User();
        BeanUtils.copyProperties(user,userEntity);
        User savedUser = userRepo.save(userEntity);

        UserDto returnUser = new UserDto();
        BeanUtils.copyProperties(savedUser,returnUser);
        return returnUser;
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        User userDetail = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

        userDetail.setName(user.getName());
         userDetail.setEmail(user.getEmail());
        userDetail.setPassword(user.getPassword());
         userDetail.setAbout(user.getAbout());

        User updatedUser = userRepo.save(userDetail);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(updatedUser,returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User userDetail  = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userDetail,returnValue);
        return returnValue;
    }

    @Override
    public List<UserDto> geAllUsers() {
        List<User> allUser = userRepo.findAll();
        List<UserDto> returnValue = allUser
                .stream()
                .map((item)-> usertoDto(item)).collect(Collectors.toList());
        return returnValue;
    }

    @Override
    public ApiResponse deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        userRepo.deleteById(userId);
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMessage("User Deleted Sucessfully");
        apiResponse.setSuccess(true);
        return apiResponse;
    }
    private UserDto usertoDto(User user){
//        UserDto userDto = new UserDto();
//        BeanUtils.copyProperties(user,userDto);

        UserDto userDto= modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
