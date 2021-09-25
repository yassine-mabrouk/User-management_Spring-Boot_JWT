package com.enset.fbc.response;

import com.enset.fbc.dto.AddressDto;
import com.enset.fbc.request.AdressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private  Long id ;
    private  String name;
    private String email;
    private boolean admin;
    private  String UserID ;
   private List<AddressResponse> addresses;
   private ContactResponse contact;
}
