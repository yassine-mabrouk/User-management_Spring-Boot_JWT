package com.enset.fbc.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    private  String userID;
    @Column(length = 255)
    private  String name ;
    @Column(nullable = true,unique = true)
    private String email;
    private  String encryptedPassword;
    private  String emailVerificationToken;
    //@Column(columnDefinition = "boolean default false") // pour donner une description
    @Column(nullable = true)
    private  Boolean getEmailVerificationStatus=false;

    public UserEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
