package com.enset.fbc.service;

import com.enset.fbc.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService  extends UserDetailsService {
    public UserDto createUser(UserDto userDto);


    UserDto getUser(String email);

   // UserDto getUserByUserId(String userId);

    //UserDto updateUser(String id, UserDto userDto);

//    void deleteUser(String userId);

}
