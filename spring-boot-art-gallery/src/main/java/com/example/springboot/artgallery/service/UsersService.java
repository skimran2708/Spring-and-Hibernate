package com.example.springboot.artgallery.service;


import com.example.springboot.artgallery.entity.Users;


public interface UsersService {

    public Users findByUsername(String theUsername);

    public void save(Users theUser);

    public void deleteByUsername(String theUsername);
}
