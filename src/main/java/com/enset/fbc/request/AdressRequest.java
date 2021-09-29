package com.enset.fbc.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdressRequest {
    private String city ;
    private String country ;
    private String street ;
    private String postal ;
    private String type;
}
