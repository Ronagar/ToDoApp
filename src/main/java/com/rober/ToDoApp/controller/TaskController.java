package com.rober.ToDoApp.controller;

import com.rober.ToDoApp.model.Task;
import com.rober.ToDoApp.model.TaskStatus;
import com.rober.ToDoApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toDoApp")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTask(@PathVariable Long id){
        return taskService.getTaskbByID(id);
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus taskStatus){
        return taskService.updateTaskStatus(id, taskStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status){
        List<Task> resTasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(resTasks);
    }


}
