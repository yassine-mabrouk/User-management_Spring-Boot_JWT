package com.enset.fbc.serviceImpli;

import com.enset.fbc.dto.AddressDto;
import com.enset.fbc.entities.AddressEntity;
import com.enset.fbc.entities.UserEntity;
import com.enset.fbc.repositories.AddressRepository;
import com.enset.fbc.repositories.UserRepository;
import com.enset.fbc.service.AddressService;
import com.enset.fbc.shared.Helper;
import com.enset.fbc.shared.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AddressServiceImpli implements AddressService {
     @Autowired
    AddressRepository addressRepository;
     @Autowired
    Helper helper;
     @Autowired
    UserRepository userRepository;
    @Override
    public AddressDto createAddress(AddressDto addressDto, String emailUserAuth) {
        UserEntity userAuth=userRepository.findByEmail(emailUserAuth);
        AddressEntity addressEntity= ObjectMapperUtils.map(addressDto,AddressEntity.class);

        addressEntity.setAddressID(helper.generateStringId(10));
        addressEntity.setUser(userAuth);
        addressRepository.save(addressEntity);
        return ObjectMapperUtils.map(addressEntity,AddressDto.class);
    }

    @Override
    public AddressDto getAddress(Long id) throws Exception {
          Optional<AddressEntity> addressEntity= addressRepository.findById(id);
          if(!addressEntity.isPresent()) throw new Exception("Address with this Id :"+id+"Not existe");
        return ObjectMapperUtils.map(addressEntity.get(),AddressDto.class);
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto, Long id) throws Exception {
        Optional<AddressEntity> addressEntity= addressRepository.findById(id);
        if(!addressEntity.isPresent()) throw new Exception("Address with this Id :"+id+"Not exist!!");
        AddressEntity toUpdated =ObjectMapperUtils.map(addressDto,AddressEntity.class);
        toUpdated.setId(addressEntity.get().getId());
        toUpdated.setAddressID(addressEntity.get().getAddressID());
        toUpdated.setUser(addressEntity.get().getUser());
        AddressEntity updated = addressRepository.save(toUpdated);
        return ObjectMapperUtils.map(updated,AddressDto.class) ;
    }

    @Override
    public List<AddressDto> getAllAdresses(String emailCurrentUser) {
        UserEntity currentUser=userRepository.findByEmail(emailCurrentUser);
        List<AddressEntity> addressEntityList= currentUser.isAdmin() ? addressRepository.findAll():addressRepository.findByUser(currentUser);

        List<AddressDto> addressDtoList=new ArrayList<>();
            addressDtoList=ObjectMapperUtils.mapAll(addressEntityList,AddressDto.class);
        return addressDtoList;
    }

    @Override
    public void deleteAddress(Long id) throws Exception {
        Optional<AddressEntity> addressEntity= addressRepository.findById(id);
        if(!addressEntity.isPresent()) throw new Exception("Address with this Id :"+id+"Not exist!!");
        addressRepository.delete(addressEntity.get());
    }
}
