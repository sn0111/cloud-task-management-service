package com.pesto.tech.data.dto;

import com.pesto.tech.data.entity.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO{
    private Long userId;
    private String userName;
    @NotNull
    private String userEmail;
    @NotNull
    private String password;
    private UserRole role;
}
