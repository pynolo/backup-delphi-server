package it.giunti.tasktrigger.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.giunti.tasktrigger.model.jpa.TasktriggerTask;
import it.giunti.tasktrigger.model.service.TasktriggerTaskService;
 
@RestController
public class TasktriggerTaskController {
 
    @Autowired
    @Qualifier("tasktriggerTaskService")
    private TasktriggerTaskService taskService;
 
    @PostMapping("/api/createtask")
    public void createNewTask(@Valid @RequestBody TasktriggerTask task) {
    	taskService.addTask(task);
    }
 
    @PutMapping("/api/changetask")
    public void changeExistingTask(@Valid @RequestBody TasktriggerTask task) {
    	taskService.modifyTask(task);
    }
 
    @DeleteMapping("/api/removetask/{id}")
    public void removeTask(@PathVariable(value = "id") long id) {
    	taskService.removeTask(id);
    }
 
    @GetMapping("/api/viewsingletask/{id}")
    public TasktriggerTask viewTaskById(@PathVariable(value = "id") long id) {
        return taskService.getTaskById(id);
    }
 
    @GetMapping("/api/viewalltasks")
    public List<TasktriggerTask> viewAllTasks() {
        return taskService.getAllTasks();
    }
 
}
