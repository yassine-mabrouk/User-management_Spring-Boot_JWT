package com.enset.fbc.controllers;


import com.enset.fbc.dto.UserDto;
import com.enset.fbc.entities.UserEntity;
import com.enset.fbc.errors.ErrorMessages;
import com.enset.fbc.errors.UserException;
import com.enset.fbc.repositories.UserRepository;
import com.enset.fbc.request.UserRequest;
import com.enset.fbc.response.UserResponse;
import com.enset.fbc.service.UserService;
import com.enset.fbc.shared.ObjectMapperUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")  //localhost:8080/users
public class UserController {
    @Autowired
    UserService userService;
  @Autowired
    UserRepository userRepository;
    // recuperer un user
    // produces = MediaType.APPLICATION_XML_VALUE : produire une resultat xml
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id ){
        UserDto userDto=userService.getUserById(id);
        return new ResponseEntity<>(ObjectMapperUtils.map(userDto,UserResponse.class),HttpStatus.OK);

    }
    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public List<UserResponse> getAllUser(@RequestParam(value = "page",defaultValue = "1") int page ,
                                         @RequestParam(value = "limit",defaultValue = "5") int limit,
                                         @RequestParam(value = "search",defaultValue = "") String search){
        List<UserResponse> userResponseList =new ArrayList<>();
        List<UserDto> users=userService.getAllUsers(page,limit,search);
        if(users!=null){
            userResponseList=ObjectMapperUtils.mapAll(users,UserResponse.class);
        }
        return userResponseList;
    }



    @PostMapping

    public ResponseEntity<UserResponse>  creteUser(@RequestBody @Valid UserRequest user) throws Exception {

        if (user.getName().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErroMessage());

        UserDto createUser =userService.createUser(ObjectMapperUtils.map(user,UserDto.class));
        return  new ResponseEntity<>(ObjectMapperUtils.map(createUser,UserResponse.class),HttpStatus.CREATED);

    }
     @PutMapping(path = "/{id}")
     public  ResponseEntity<UserResponse>  updateUser(@RequestBody UserRequest user,@PathVariable  Long id){

         UserDto createUser =userService.updateUser(id,ObjectMapperUtils.map(user,UserDto.class));

         return  new ResponseEntity<>(ObjectMapperUtils.map(createUser,UserResponse.class),HttpStatus.ACCEPTED);
    }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Object> deleteUser(@PathVariable Long id){
          userService.deleteUser(id);
         return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
}

