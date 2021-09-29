package com.enset.fbc.controllers;

import com.enset.fbc.dto.AddressDto;
import com.enset.fbc.request.AdressRequest;
import com.enset.fbc.response.AddressResponse;
import com.enset.fbc.service.AddressService;
import com.enset.fbc.shared.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(@RequestBody AdressRequest adressRequest, Principal principal){
         AddressDto addressDto=ObjectMapperUtils.map(adressRequest,AddressDto.class);

        AddressDto addressDto2=addressService.createAddress(addressDto,principal.getName());
        AddressResponse addressResponse=ObjectMapperUtils.map(addressDto2,AddressResponse.class);
        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<AddressResponse>>getAllAddresses(Principal principal){
        // priciapl contiel les donnte de ahtentififaction
        List<AddressDto> addressDtoList=addressService.getAllAdresses(principal.getName());
        List<AddressResponse> addressResponseList=new ArrayList<>();
        if(addressDtoList!=null){
            addressResponseList=ObjectMapperUtils.mapAll(addressDtoList,AddressResponse.class);
        }
        return  new ResponseEntity<>(addressResponseList, HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable Long id) throws Exception {
        AddressDto addressDto=addressService.getAddress(id);
        AddressResponse addressResponse=ObjectMapperUtils.map(addressDto,AddressResponse.class);
        return  new ResponseEntity<>(addressResponse,HttpStatus.OK);
    }
    @PutMapping (path = "/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@RequestBody AddressDto addressDto,@PathVariable("id") Long id ) throws Exception {
        AddressDto udpated =addressService.updateAddress(addressDto,id);
        AddressResponse addressResponse=ObjectMapperUtils.map(udpated,AddressResponse.class);
        return  new ResponseEntity<>(addressResponse,HttpStatus.ACCEPTED);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteAddress (@PathVariable Long id ) throws Exception {
        addressService.deleteAddress(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
