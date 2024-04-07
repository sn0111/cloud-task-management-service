package com.pesto.tech.service;

import com.pesto.tech.data.entity.UserEntity;
import com.pesto.tech.data.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> user = userRepository.findByUserEmail(username);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException("Invalid Username");
        }
    }
}