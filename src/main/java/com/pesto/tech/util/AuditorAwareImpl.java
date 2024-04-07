package com.pesto.tech.util;

import com.pesto.tech.dao.UserDAO;
import com.pesto.tech.data.entity.UserEntity;
import com.pesto.tech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Optional<Long> getCurrentAuditor() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserDetails> userDetails = userDAO.userDetails(userEmail);
        if(userDetails.isPresent()){
            UserEntity entity =(UserEntity) userDetails.get();
            return Optional.of(entity.getUserId());
        }else {
            throw new BadCredentialsException("Invalid Credentials");
        }
    }


}