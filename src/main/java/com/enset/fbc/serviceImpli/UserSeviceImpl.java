package com.enset.fbc.serviceImpli;

import com.enset.fbc.dto.UserDto;
import com.enset.fbc.entities.UserEntity;
import com.enset.fbc.repositories.UserRepository;
import com.enset.fbc.service.UserService;
import com.enset.fbc.shared.Helper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserSeviceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Helper helper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity checkUser = userRepository.findByEmail(userDto.getEmail());
        // verifier existance of the user in BD
        if (checkUser!=null) throw  new RuntimeException("User existe !!!");
         UserEntity userEntity=new UserEntity();
         BeanUtils.copyProperties(userDto,userEntity);
          userEntity.setUserID(helper.generateStringId(32));
          userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
          userEntity.setGetEmailVerificationStatus(true);
          userRepository.save(userEntity);
          UserDto responseUserDto=new UserDto();
          BeanUtils.copyProperties(userEntity,responseUserDto);
          return responseUserDto;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException("User not exit with this email :"+email);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findByEmail(email);
        if (userEntity==null) throw  new UsernameNotFoundException("User not found "+email);

        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
    }

    // cette methode est dans interfavce UserDetailsService

}
