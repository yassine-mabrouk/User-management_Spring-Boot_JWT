package com.enset.fbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "contacts")
@Data
@NoArgsConstructor
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private  String contactID;
    @Column(nullable = false)
    private String mobile ;
    private  String skype ;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user ;
}
