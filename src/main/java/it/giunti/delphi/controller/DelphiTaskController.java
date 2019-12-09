package it.giunti.delphi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.giunti.delphi.model.entity.DelphiTask;
import it.giunti.delphi.service.DelphiTaskService;
 
@RestController
@CrossOrigin(origins = "*")
public class DelphiTaskController {
 
    @Autowired
    @Qualifier("delphiTaskService")
    private DelphiTaskService taskService;
 
    @PostMapping("/api/createtask")
    public void createNewTask(@Valid @RequestBody DelphiTask task) {
    	taskService.addTask(task);
    }
 
    @PutMapping("/api/changetask")
    public void changeExistingTask(@Valid @RequestBody DelphiTask task) {
    	taskService.modifyTask(task);
    }
 
    @DeleteMapping("/api/removetask/{id}")
    public void removeTask(@PathVariable(value = "id") int id) {
    	taskService.removeTask(id);
    }
 
    @GetMapping("/api/viewsingletask/{id}")
    public DelphiTask viewTaskById(@PathVariable(value = "id") int id) {
        return taskService.getTaskById(id);
    }
 
    @GetMapping("/api/viewalltasks")
    public List<DelphiTask> viewAllTasks() {
        return taskService.getAllTasks();
    }
 
}
