package com.enset.fbc.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequest {
    private  String email;
    private String  password;
}
