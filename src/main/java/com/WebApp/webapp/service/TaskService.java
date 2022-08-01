package com.WebApp.webapp.service;

import com.WebApp.webapp.entity.Task;
import com.WebApp.webapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public void addNewTask(Task task){
        boolean exist = getAllTasks().stream()
                .anyMatch(p-> p.getTask().equals(task.getTask()));
        if (exist){
            throw new IllegalStateException("Task already exist");
        }
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId){
        boolean exist = taskRepository.existsById(taskId);
        if (!exist){
            throw new IllegalStateException("Task not found");
        }
        taskRepository.deleteById(taskId);
    }
}
