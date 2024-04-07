package com.pesto.tech.dao;

import com.pesto.tech.data.entity.UserEntity;
import com.pesto.tech.data.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {

    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> users(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> findByUserId(Long userId){
        return userRepository.findById(userId);
    }

    public UserEntity saveUser(UserEntity entity){
        return userRepository.save(entity);
    }

    public Optional<UserDetails> userDetails(String userEmail){
        return userRepository.findByUserEmail(userEmail);
    }
}
