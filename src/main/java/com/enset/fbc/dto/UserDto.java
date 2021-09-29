package com.enset.fbc.dto;

import com.enset.fbc.request.AdressRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {

    private  Long id ;
    private  String userID;
    private  String name ;
    private String email;
    private boolean admin;
    private  String  password;
    private  String encryptedPassword;
    private  String emailVerificationToken;
    private  Boolean getEmailVerificationStatus;
    private List<AddressDto> addresses;
    private ContactDto contact;
}
