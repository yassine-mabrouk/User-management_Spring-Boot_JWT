package com.enset.fbc.request;

import com.enset.fbc.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactRequest {
    private String mobile ;
    private  String skype ;
}
