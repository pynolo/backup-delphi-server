package it.giunti.delphi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.giunti.delphi.ControllerException;
import it.giunti.delphi.EtlException;
import it.giunti.delphi.TaskType;
import it.giunti.delphi.model.entity.DelphiExecution;
import it.giunti.delphi.service.DelphiExecutionService;

@RestController
@CrossOrigin(origins = "*")
public class DelphiExecutionController {

    @Autowired
    @Qualifier("delphiExecutionService")
    private DelphiExecutionService executionService;
    
	@GetMapping("/api/execute/{type}/{executable}")
	public DelphiExecution executeByExecutable(@PathVariable(value = "type") String typeString,
			@PathVariable(value = "executable") String executable)
			throws ControllerException {
		TaskType type;
		if (typeString != null && executable != null) {
			if (typeString.equalsIgnoreCase(TaskType.PLAN.getTypeName())) {
				type = TaskType.PLAN;
			} else {
				type = TaskType.TASK;
			}
		} else {
			throw new ControllerException("Null parameters");
		}
		try {
			DelphiExecution delphiExecution;
			delphiExecution = executionService.executeByExecutable(type, executable);
			return delphiExecution;
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
	
	@GetMapping("/api/findexecutionbyid/{type}/{executionId}")
	public DelphiExecution findExecutionById(@PathVariable(value = "type") String typeString,
			@PathVariable(value = "executionId") String executionId)
			throws ControllerException {
		TaskType type;
		if (typeString != null && executionId != null) {
			if (typeString.equalsIgnoreCase(TaskType.PLAN.getTypeName())) {
				type = TaskType.PLAN;
			} else {
				type = TaskType.TASK;
			}
		} else {
			throw new ControllerException("Null parameters");
		}
		try {
			DelphiExecution delphiExecution;
			delphiExecution = executionService.retrieveExecutionByExecutionId(type, executionId);
			return delphiExecution;
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
	
	@GetMapping("/api/findexecutionbyexecutable/{type}/{executable}")
	public DelphiExecution findExecutionByExecutable(@PathVariable(value = "type") String typeString,
			@PathVariable(value = "executable") String executable)
			throws ControllerException {
		TaskType type;
		if (typeString != null && executable != null) {
			if (typeString.equalsIgnoreCase(TaskType.PLAN.getTypeName())) {
				type = TaskType.PLAN;
			} else {
				type = TaskType.TASK;
			}
		} else {
			throw new ControllerException("Null parameters");
		}
		try {
			DelphiExecution delphiExecution;
			delphiExecution = executionService.retrieveExecutionByExecutable(type, executable);
			return delphiExecution;
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
	
}
