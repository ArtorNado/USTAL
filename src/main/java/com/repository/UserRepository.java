package com.repository;


import com.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserLoginPasAndUserPassword(String userLogin, String password);

    Optional<User> findByUserLogin(String userLogin);

   /* Optional<List<User>> findByUserLogin(String userLogin);*/

}
