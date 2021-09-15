package com.enset.fbc.serviceImpli;

import com.enset.fbc.dto.UserDto;
import com.enset.fbc.entities.UserEntity;
import com.enset.fbc.errors.ErrorMessages;
import com.enset.fbc.repositories.UserRepository;
import com.enset.fbc.response.UserResponse;
import com.enset.fbc.service.UserService;
import com.enset.fbc.shared.Helper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserSeviceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Helper helper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto createUser(UserDto userDto)  {

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
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<UserEntity> ckeckUserEntity = userRepository.findById(id);
        if(!ckeckUserEntity.isPresent()) throw new UsernameNotFoundException("User not exit with this Id :"+id);
        UserEntity userEntity=ckeckUserEntity.get();
        userEntity.setName(userDto.getName());
       UserEntity update= userRepository.save(userEntity);
        UserDto responseUserDto=new UserDto();
        BeanUtils.copyProperties(update,responseUserDto);
        return responseUserDto;
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserEntity> ckeckUserEntity = userRepository.findById(id);
        if(!ckeckUserEntity.isPresent()) throw new UsernameNotFoundException("User not exit with this Id :"+id);
        UserEntity userEntity=ckeckUserEntity.get();
         userRepository.delete(userEntity);
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
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserID(userId);
        if(userEntity == null) throw new UsernameNotFoundException("User not exit with this Id :"+userId);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }
    @Override
    public UserDto getUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()) throw new UsernameNotFoundException("User not exit with this Id :"+id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity.get(), userDto);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers(int page, int limit) {
         if(page >0) page=page-1;
        // decrimenter la page car dans spring boot la page debut de 0
        Pageable pageableRequest= PageRequest.of(page,limit);
        Page<UserEntity> pageUsers=userRepository.findAll(pageableRequest);
        List<UserEntity> users=pageUsers.getContent();
        List<UserDto> userDtoList=new ArrayList<>();
        for (UserEntity userEntity:users) {
            UserDto userDto=new UserDto();
            BeanUtils.copyProperties(userDto,userEntity);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    // cette methode est dans interfavce UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findByEmail(email);
        if (userEntity==null) throw  new UsernameNotFoundException("User not found "+email);

        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
    }


}
