package it.giunti.delphi.controller;

import java.io.IOException;
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

import it.giunti.delphi.ControllerException;
import it.giunti.delphi.model.entity.DelphiTask;
import it.giunti.delphi.service.DelphiTaskService;
 
@RestController
@CrossOrigin(origins = "*")
public class DelphiTaskController {
 
    @Autowired
    @Qualifier("delphiTaskService")
    private DelphiTaskService taskService;

	@PostMapping("/api/updatetasks")
	public void updateTasks() throws ControllerException {
		try {
			taskService.updateTasksAndPlans();
		} catch (IOException e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}

    @PostMapping("/api/createtask")
    public DelphiTask createNewTask(@Valid @RequestBody DelphiTask task) {
    	return taskService.addTask(task);
    }
 
    @PutMapping("/api/changetask")
    public void changeExistingTask(@Valid @RequestBody DelphiTask task) {
    	taskService.modifyTask(task);
    }
 
    @GetMapping("/api/viewtaskbyexecutable/{id}")
    public DelphiTask viewTaskByExecutable(@PathVariable(value = "executable") String executable) {
        return taskService.getTaskByExecutable(executable);
    }
    
    @PutMapping("/api/changetaskdescription")
    public void changeTaskDescription(@Valid @RequestBody DelphiTask task) {
    	taskService.modifyTaskDescription(task.getExecutable(), task.getDescription());
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
        return taskService.getAllAvailableTasks();
    }

    @GetMapping("/api/viewalltasks/{id}")
    public List<DelphiTask> viewAllTasksByUser(@PathVariable(value = "id") String id) {
        return taskService.getAllTasksByUser(id);
    }
}
