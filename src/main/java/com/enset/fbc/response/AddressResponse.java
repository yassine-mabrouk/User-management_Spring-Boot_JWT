package com.enset.fbc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressResponse {
    private  long id ;
    private  String addressID;
    private String city ;
    private String country ;
    private String street ;
    private String postal ;
    private String type;
}
