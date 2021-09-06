package com.enset.fbc.service;

import com.enset.fbc.dto.UserDto;


public interface UserService {
    public UserDto createUser(UserDto userDto);


    UserDto getUser(String email);

   // UserDto getUserByUserId(String userId);

    //UserDto updateUser(String id, UserDto userDto);

//    void deleteUser(String userId);

}
