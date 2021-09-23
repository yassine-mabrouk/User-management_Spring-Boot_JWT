package com.enset.fbc.repositories;

import com.enset.fbc.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> {
    public  UserEntity findByEmail(String email);
    public  UserEntity findByUserID(String userId);
    // use the native Query
   // @Query(name = "select * from users u where u.name= ?1",nativeQuery = true) // ?1: num pour ordre de paramaetre de rechers
    //Page<UserEntity> getAllUsers(Pageable p,String name);
    // utilser les parametre avec nom
    //@Query(name = "select * from users u where u.name=:name",nativeQuery = true) // ?1: num pour ordre de paramaetre de rechers
    //Page<UserEntity> getAllUsers(Pageable p,@Param("name") String name);
    // ===============Utiliser jpql ====
    //@Query("select user from UserEntity user")
   // Page<UserEntity> getAllUsers(Pageable p);

    Page<UserEntity> findByNameContains(Pageable pageable,String name );// recupere user avec name
}
