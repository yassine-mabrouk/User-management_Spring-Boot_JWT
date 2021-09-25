package com.enset.fbc.repositories;

import com.enset.fbc.entities.AddressEntity;
import com.enset.fbc.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository  extends JpaRepository<AddressEntity, Long> {
 List<AddressEntity> findByUser(UserEntity currentUser);
}
