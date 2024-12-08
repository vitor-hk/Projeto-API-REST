package com.login.user.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.user.domain.models.User;

import java.util.List;
import java.util.UUID;


@Repository
public interface UsersRepository extends JpaRepository<User, UUID>{
    
    List<User> findAll();
    User findByMail(String mail);
    User findByLogin(String login);
    boolean existsByLoginOrMail(String login, String mail);
}