package com.pesto.tech.controller;

import com.pesto.tech.data.dto.ResponseDTO;
import com.pesto.tech.data.dto.TaskDTO;
import com.pesto.tech.service.TaskService;
import com.pesto.tech.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseDTO> userTasks(Long userId){
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(taskService.getAllUserTasks(userId)));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseDTO> saveTask(@Valid @RequestBody TaskDTO dto){
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(taskService.saveTask(dto)));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseDTO> updateTask(@Valid @RequestBody TaskDTO dto){
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(taskService.updateTAsk(dto)));
    }

    @PutMapping("/delete/{taskId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseDTO> deleteTask(@PathVariable("taskId") Long taskId){
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(taskService.deleteTask(taskId)));
    }

}
