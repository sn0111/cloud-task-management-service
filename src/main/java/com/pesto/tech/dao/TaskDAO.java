package com.pesto.tech.dao;

import com.pesto.tech.data.entity.TaskEntity;
import com.pesto.tech.data.repo.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskDAO {

    private final TaskRepository taskRepository;

    public TaskDAO(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> findTasksByUserId(Long userId){
        return taskRepository.findByUserUserId(userId);
    }

    public TaskEntity saveTask(TaskEntity entity){
        return taskRepository.save(entity);
    }

    public Optional<TaskEntity> findTaskByTaskId(Long taskId){
        return taskRepository.findById(taskId);
    }

    public void deleteTask(TaskEntity entity){
        taskRepository.delete(entity);
    }
}
