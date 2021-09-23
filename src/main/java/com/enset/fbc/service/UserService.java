package com.enset.fbc.service;

import com.enset.fbc.dto.UserDto;
import com.enset.fbc.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService  extends UserDetailsService {
    public UserDto createUser(UserDto userDto) throws Exception;
    public UserDto updateUser(Long userId,UserDto userDto);
    void deleteUser(Long id);


    UserDto getUser(String email); // pour security
    UserDto getUserByUserId(String userId);// pour secuirty
    UserDto getUserById(Long id);// pour api get

    List<UserDto> getAllUsers(int page, int limit,String search);
}
