package com.enset.fbc.service;

import com.enset.fbc.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(AddressDto addressDto,String emailUserAuth);
    AddressDto getAddress (Long id) throws Exception;
    AddressDto updateAddress (AddressDto addressDto ,Long id ) throws Exception;
    List<AddressDto> getAllAdresses (String emailCurrentUser);
    public void deleteAddress (Long id ) throws Exception;
}
