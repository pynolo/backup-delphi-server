package it.giunti.tasktrigger.controller;

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

import it.giunti.tasktrigger.model.entity.TasktriggerUser;
import it.giunti.tasktrigger.service.TasktriggerUserService;
 
@RestController
@CrossOrigin(origins = "*")
public class TasktriggerUserController {
 
    @Autowired
    @Qualifier("tasktriggerUserService")
    private TasktriggerUserService userService;
 
    @PostMapping("/api/createuser")
    public void createNewUser(@Valid @RequestBody TasktriggerUser user) {
    	userService.addUser(user);
    }
 
    @PutMapping("/api/changeuser")
    public void changeExistingUser(@Valid @RequestBody TasktriggerUser user) {
    	userService.modifyUser(user);
    }
 
    @DeleteMapping("/api/removeuser/{id}")
    public void removeUser(@PathVariable(value = "id") int id) {
    	userService.removeUser(id);
    }
 
    @GetMapping("/api/viewsingleuser/{id}")
    public TasktriggerUser viewUserById(@PathVariable(value = "id") int id) {
        return userService.getUserById(id);
    }
 
    @GetMapping("/api/viewallusers")
    public List<TasktriggerUser> viewAllUsers() {
        return userService.getAllUsers();
    }
 
}
