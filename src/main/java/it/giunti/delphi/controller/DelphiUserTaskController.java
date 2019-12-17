package it.giunti.delphi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.giunti.delphi.ControllerException;
import it.giunti.delphi.model.entity.DelphiUserTask;
import it.giunti.delphi.service.DelphiUserTaskService;
 
@RestController
@CrossOrigin(origins = "*")
public class DelphiUserTaskController {
 
    @Autowired
    @Qualifier("delphiUserTaskService")
    private DelphiUserTaskService userTaskService;
 
    @GetMapping("/api/viewusertask/{username}/{executable}")
    public DelphiUserTask viewUserTaskById(
    		@PathVariable(value = "username") String username,
    		@PathVariable(value = "executable") String executable) {
        return userTaskService.getUserTask(username, executable);
    }
    
    @PostMapping("/api/bindusertask")
    public void bindUserTask(@Valid @RequestBody DelphiUserTask userTask) throws ControllerException {
    	if (userTask != null) {
    		userTaskService.addUserTask(userTask.getUsername(), userTask.getExecutable());
    	} else {
    		throw new ControllerException("userTask is null");
    	}
    }
 
    @PostMapping("/api/unbindusertask")
    public void unbindUserTask(@Valid @RequestBody DelphiUserTask userTask)  throws ControllerException {
    	if (userTask != null) {
    		userTaskService.removeUserTask(userTask.getUsername(), userTask.getExecutable());
    	} else {
    		throw new ControllerException("userTask is null");
    	}
    }

}