package com.pesto.tech.service;

import com.pesto.tech.dao.UserDAO;
import com.pesto.tech.data.dto.UserDTO;
import com.pesto.tech.data.entity.UserEntity;
import com.pesto.tech.data.entity.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDAO userDAO;

    private final PasswordEncoder encoder;

    private final ModelMapper modelMapper;

    public UserService(UserDAO userDAO, PasswordEncoder encoder, ModelMapper modelMapper) {
        this.userDAO = userDAO;
        this.encoder = encoder;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getAllUsers(){
        return userDAO.users()
                .stream()
                .map(o->modelMapper.map(o,UserDTO.class))
                .toList();
    }



    public UserDTO saveUser(UserDTO dto){
        Assert.notNull(dto.getUserEmail(),"");
        Assert.notNull(dto.getPassword(),"");

        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        entity.setRole(UserRole.USER);
        entity.setPassword(encoder.encode(entity.getPassword()));
        UserEntity userEntity = userDAO.saveUser(entity);
        return modelMapper.map(userEntity,UserDTO.class);
    }

    public UserDTO updateUser(UserDTO dto){
        Assert.notNull(dto.getUserId(),"");
        Assert.notNull(dto.getUserEmail(),"");
        Assert.notNull(dto.getPassword(),"");
        Optional<UserEntity> byUserId = userDAO.findByUserId(dto.getUserId());
        if(byUserId.isPresent()){
            UserEntity entity = modelMapper.map(dto, UserEntity.class);
            entity.setUserId(byUserId.get().getUserId());
            UserEntity userEntity = userDAO.saveUser(entity);
            return modelMapper.map(userEntity,UserDTO.class);
        }
        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        UserEntity userEntity = userDAO.saveUser(entity);
        return modelMapper.map(userEntity,UserDTO.class);
    }

    public String loginUser(UserDTO dto){
        Assert.notNull(dto.getUserEmail(),"");
        Assert.notNull(dto.getPassword(),"");

        Optional<UserDetails> userDetails = userDAO.userDetails(dto.getUserEmail());
        if(userDetails.isPresent() &&  encoder.matches(dto.getPassword(),userDetails.get().getPassword())){
            UserEntity entity =(UserEntity) userDetails.get();
            System.out.println(entity.getUserId());
            return entity.getRole().toString();
        }
        return "";
    }
}
