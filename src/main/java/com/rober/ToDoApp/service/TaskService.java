package com.rober.ToDoApp.service;

import com.rober.ToDoApp.model.Task;
import com.rober.ToDoApp.model.TaskStatus;
import com.rober.ToDoApp.repository.TaskRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public ResponseEntity<Object> getTaskbByID(Long id) {
        Optional<Task> res = taskRepository.findById(id);
        if (res.isEmpty()){
            return new ResponseEntity<>("That task do not exist", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Object> createTask(Task task) {
        Optional<Task> res = taskRepository.findById(task.getId());
        if(res.isPresent()){
            return new ResponseEntity<>("Task already exists.", HttpStatus.CONFLICT);
        }
        taskRepository.save(task);
        return new ResponseEntity<>("Task created succesfully.", HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateTaskStatus(Long id, TaskStatus status){
        Optional<Task> res = taskRepository.findById(id);
        if(res.isEmpty()){
            return new ResponseEntity<>("Task " + id + " does not exists.", HttpStatus.NOT_FOUND);
        }
        res.get().setStatus(status);
        taskRepository.save(res.get());
        return new ResponseEntity<>("Status updated.", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Object> deleteTask(Long id){
        if(!taskRepository.existsById(id)){
            return new ResponseEntity<>("Task " + id + "does not exists.", HttpStatus.NOT_FOUND);
        }
        taskRepository.deleteById(id);
        return new ResponseEntity<>("Task deleted.", HttpStatus.ACCEPTED);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
       return taskRepository.findAllByStatus(status);
    }
}
