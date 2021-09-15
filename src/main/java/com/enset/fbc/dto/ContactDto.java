package com.enset.fbc.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDto {
    private long id;
    private  String contactID;
    private String mobile ;
    private  String skype ;
    private UserDto user ;
}
