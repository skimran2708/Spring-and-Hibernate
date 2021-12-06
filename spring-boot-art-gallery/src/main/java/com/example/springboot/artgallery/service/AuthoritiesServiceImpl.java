package com.example.springboot.artgallery.service;

import com.example.springboot.artgallery.dao.AuthoritiesRepository;
import com.example.springboot.artgallery.entity.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService{

    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    public AuthoritiesServiceImpl(AuthoritiesRepository theAuthoritiesRepository) {
        authoritiesRepository=theAuthoritiesRepository;
    }

    @Override
    public void save(Authorities theAuthority) {
        authoritiesRepository.save(theAuthority);
    }
}
