package com.enset.fbc.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "groupes")
@Data
@NoArgsConstructor
public class GroupesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id ;
    private  String name ;
    @ManyToMany(fetch = FetchType.LAZY)
    // creation du table intermediatre
    @JoinTable(name = "groupes_users",joinColumns = {@JoinColumn(name = "groupe_id")},
    inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<UserEntity> users=new HashSet<>();
}
