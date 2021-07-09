package com.shopbike.repository;

import com.shopbike.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.idUser <> 1")
    Iterable<User> findAllUser();
}
