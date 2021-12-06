package com.example.springboot.artgallery.service;


import com.example.springboot.artgallery.entity.Users;

import java.util.List;

public interface UsersService {

    public List<Users> findAll();

    public Users findByUsername(String theUsername);

    public void save(Users theUser);

    public void deleteByUsername(String theUsername);
}
