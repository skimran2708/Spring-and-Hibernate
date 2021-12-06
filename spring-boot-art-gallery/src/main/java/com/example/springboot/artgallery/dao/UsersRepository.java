package com.example.springboot.artgallery.dao;

import com.example.springboot.artgallery.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    public Optional<Users> findByUsername(String username);

    public Long deleteByUsername(String username);
}
