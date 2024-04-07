package com.pesto.tech.controller;

import com.pesto.tech.data.dto.UserDTO;
import com.pesto.tech.service.UserService;
import com.pesto.tech.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> users(){
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(userService.getAllUsers()));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> userLogin(@Valid @RequestBody UserDTO dto){
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(userService.loginUser(dto)));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserDTO dto){
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(userService.saveUser(dto)));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO dto){
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(userService.updateUser(dto)));
    }

}
