package com.enset.fbc.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private  Long id ;
    private  String userID;
    private  String name ;
    private String email;
    private  String  password;
    private  String encryptedPassword;
    private  String emailVerificationToken;
    private  Boolean getEmailVerificationStatus;
}
