package com.enset.fbc.entities;


import com.enset.fbc.request.AdressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(nullable = false,unique = true)
    private String email;
    private  String encryptedPassword;
    private  String emailVerificationToken;
    //@Column(columnDefinition = "boolean default false") // pour donner une description
    @Column(nullable = true)
    private  Boolean getEmailVerificationStatus=false;
    @OneToMany (mappedBy = "user",cascade = CascadeType.ALL)
   // cascade = CascadeType.ALL : pour creer les adresse dans la creation de user
    private List<AddressEntity> addresses;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private ContactEntity contact ;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "users")
    private Set<GroupesEntity> groupes=new HashSet<>();

    public UserEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
