package com.pesto.tech.service;

import com.pesto.tech.dao.TaskDAO;
import com.pesto.tech.data.dto.TaskDTO;
import com.pesto.tech.data.entity.TaskEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskDAO taskDAO;

    private final ModelMapper modelMapper;

    public TaskService(TaskDAO taskDAO, ModelMapper modelMapper) {
        this.taskDAO = taskDAO;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<TaskDTO> getAllUserTasks(Long userId){
        return taskDAO.findTasksByUserId(userId)
                .stream()
                .map(o->modelMapper.map(o,TaskDTO.class))
                .toList();
    }

    @Transactional
    public TaskDTO saveTask(TaskDTO dto){
        TaskEntity entity = modelMapper.map(dto, TaskEntity.class);
        TaskEntity taskEntity = taskDAO.saveTask(entity);
        return modelMapper.map(taskEntity,TaskDTO.class);
    }

    @Transactional
    public TaskDTO updateTAsk(TaskDTO dto){
        Optional<TaskEntity> taskByTaskId = taskDAO.findTaskByTaskId(dto.getTaskId());
        if(taskByTaskId.isPresent()){
            TaskEntity entity = modelMapper.map(dto, TaskEntity.class);
            entity.setTaskId(taskByTaskId.get().getTaskId());
            TaskEntity taskEntity = taskDAO.saveTask(entity);
            return modelMapper.map(taskEntity,TaskDTO.class);
        }
        return null;
    }

    @Transactional
    public String deleteTask(Long taskId){
        Optional<TaskEntity> taskByTaskId = taskDAO.findTaskByTaskId(taskId);
        if(taskByTaskId.isPresent()){
            taskDAO.deleteTask(taskByTaskId.get());
            return "Task deleted";
        }else {
            return null;
        }

    }
}
