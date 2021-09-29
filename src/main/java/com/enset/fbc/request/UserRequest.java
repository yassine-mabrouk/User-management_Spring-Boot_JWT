package com.enset.fbc.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotNull(message = "Le nom  ne doit pas etre null")
    @Size(min = 3,message = "Le nom doit avoir au moins 3 caracteres ")
    private  String name ;
    @Email(message = "Le champs email doit respecter le format email")
    @NotNull(message = "Le champs email ne doit pas etre null")
    private String email;
    private boolean admin;
    @Size(min = 5,message = "Password doit avoir au moins 3 caracteres ")
    @NotNull(message = "Password ne doit pas etre null")
   // @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}",message = "Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters")
    private  String  password;
    private List<AdressRequest> addresses;
    private ContactRequest contact;
}
