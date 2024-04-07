package com.pesto.tech.data.dto;

import com.pesto.tech.data.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO extends BaseDTO{

    private Long taskId;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private TaskStatus status;
    private UserDTO user;
}
