package com.enset.fbc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    private long id;
    private  String addressID;
    private String city ;
    private String country ;
    private String street ;
    private String postal ;
    private String type;
    private UserDto user;
}
