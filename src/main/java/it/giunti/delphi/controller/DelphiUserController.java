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

import it.giunti.delphi.model.entity.DelphiUser;
import it.giunti.delphi.service.DelphiUserService;
 
@RestController
@CrossOrigin(origins = "*")
public class DelphiUserController {
 
    @Autowired
    @Qualifier("delphiUserService")
    private DelphiUserService userService;
 
    @PostMapping("/api/createuser")
    public DelphiUser createNewUser(@Valid @RequestBody DelphiUser user) {
    	return userService.addUser(user);
    }
 
    @PutMapping("/api/changeuser")
    public void changeExistingUser(@Valid @RequestBody DelphiUser user) {
    	userService.modifyUser(user);
    }
 
    @DeleteMapping("/api/removeuser/{id}")
    public void removeUser(@PathVariable(value = "id") String id) {
    	userService.removeUser(id);
    }
 
    @GetMapping("/api/viewsingleuser/{id}")
    public DelphiUser viewUserById(@PathVariable(value = "id") String id) {
        return userService.getUserByUsername(id);
    }
 
    @GetMapping("/api/viewallusers")
    public List<DelphiUser> viewAllUsers() {
        return userService.getAllUsers();
    }
 
}
