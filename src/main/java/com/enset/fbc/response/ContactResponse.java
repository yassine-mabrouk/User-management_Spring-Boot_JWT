package com.enset.fbc.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactResponse {
    private long id;
    private  String contactID;
    private String mobile ;
    private  String skype ;
}
