package com.enset.fbc.entities;

import com.enset.fbc.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "addresses")
public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String addressID;
    private String city ;
    private String country ;
    private String street ;
    @Column(nullable = false)
    private String postal ;
    private String type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
