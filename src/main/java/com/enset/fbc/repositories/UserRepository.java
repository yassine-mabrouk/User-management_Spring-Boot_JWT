package com.enset.fbc.repositories;

import com.enset.fbc.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> {
    public  UserEntity findByEmail(String email);
    public  UserEntity findByUserID(String userId);

}
