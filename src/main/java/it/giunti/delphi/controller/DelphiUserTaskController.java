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
    public DelphiMatch viewUserTask(
    		@PathVariable(value = "username") String username,
    		@PathVariable(value = "executable") String executable) {
        DelphiUserTask ut = userTaskService.getUserTask(username, executable);
        DelphiMatch match = new DelphiMatch();
        match.setUsername(username);
        match.setExecutable(executable);
        match.setMatch(ut != null);
        return match;
    }
    
    @PostMapping("/api/changeusertask")
    public void changeUserTask(@Valid @RequestBody DelphiMatch match) throws ControllerException {
    	if (match != null) {
    		if (match.isMatch()) {
    			userTaskService.addUserTask(match.getUsername(), match.getExecutable());
    		} else {
    			userTaskService.removeUserTask(match.getUsername(), match.getExecutable());
    		}
    	} else {
    		throw new ControllerException("DelphiMatch is null");
    	}
    }

}
