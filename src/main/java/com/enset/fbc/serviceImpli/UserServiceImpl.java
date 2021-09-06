package com.enset.fbc.serviceImpli;

import com.enset.fbc.dto.UserDto;
import com.enset.fbc.entities.UserEntity;
import com.enset.fbc.repositories.UserRepository;
import com.enset.fbc.service.UserService;
import com.enset.fbc.shared.Helper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Helper helper;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity checkUser = userRepository.findByEmail(userDto.getEmail());
        // verifier existance of the user in BD
        if (checkUser!=null) throw  new RuntimeException("User existe !!!");
         UserEntity userEntity=new UserEntity();
         BeanUtils.copyProperties(userDto,userEntity);
          userEntity.setUserID(helper.generateStringId(32));
          userEntity.setEncryptedPassword("setEncryptedPassword");
          userEntity.setGetEmailVerificationStatus(true);
          userRepository.save(userEntity);
          UserDto responseUserDto=new UserDto();
          BeanUtils.copyProperties(userEntity,responseUserDto);
          return responseUserDto;
    }

    @Override
    public UserDto getUser(String email) {

        UserDto userDto = new UserDto();

        return userDto;
    }
}
