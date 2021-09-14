package com.enset.fbc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private  Long id ;
    private  String name;
    private String email;
    private  String UserID ;


}
