package com.example.springboot.artgallery.service;

import com.example.springboot.artgallery.dao.UsersRepository;
import com.example.springboot.artgallery.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{
    private UsersRepository usersRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository theUsersRepository) {
        usersRepository=theUsersRepository;
        passwordEncoder=new BCryptPasswordEncoder();
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findByUsername(String username) {
        Optional<Users> result = usersRepository.findByUsername(username);

        Users theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the artist
            throw new RuntimeException("Did not find User - " + theUser);
        }

        return theUser;
    }

    @Override
    public void save(Users theUser) {

        String encodedPassword = passwordEncoder.encode(theUser.getPassword());
        theUser.setPassword("{bcrypt}"+encodedPassword);

        usersRepository.save(theUser);
    }

    @Override
    public void deleteByUsername(String theUsername) {
        usersRepository.deleteByUsername(theUsername);
    }

}


