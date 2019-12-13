package it.giunti.delphi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import it.giunti.delphi.ControllerException;
import it.giunti.delphi.EtlException;
import it.giunti.delphi.TaskType;
import it.giunti.delphi.etl.EtlExecution;
import it.giunti.delphi.service.EtlService;

@RestController
@CrossOrigin(origins = "*")
public class EtlController {

	@Autowired
	@Qualifier("etlService")
	private EtlService etlService;

	@PostMapping("/api/updatetasks")
	public void updateTasks() throws ControllerException {
		try {
			etlService.updateTasksAndPlans();
		} catch (IOException e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}

	@GetMapping("/api/executebyid/{type}/{id}")
	public EtlExecution executeById(@PathVariable(value = "type") String typeString, @PathVariable(value = "id") String id)
			throws ControllerException {
		TaskType type;
		if (typeString != null && id != null) {
			if (typeString.equalsIgnoreCase(TaskType.PLAN.getTypeName())) {
				type = TaskType.PLAN;
			} else {
				type = TaskType.TASK;
			}
		} else {
			throw new ControllerException("Null parameters");
		}
		try {
			EtlExecution etlExecution;
			etlExecution = etlService.executeById(type,id);
			return etlExecution;
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
	
	@GetMapping("/api/findexecution/{type}/{id}")
	public EtlExecution findExecution(@PathVariable(value = "type") String typeString, @PathVariable(value = "id") String id)
			throws ControllerException {
		TaskType type;
		if (typeString != null && id != null) {
			if (typeString.equalsIgnoreCase(TaskType.PLAN.getTypeName())) {
				type = TaskType.PLAN;
			} else {
				type = TaskType.TASK;
			}
		} else {
			throw new ControllerException("Null parameters");
		}
		try {
			EtlExecution etlExecution;
			etlExecution = etlService.findExecution(type, id);
			return etlExecution;
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
	
//	@PostMapping("/api/executebyid")
//	public EtlExecution executeById(@Valid @RequestBody LinkedHashMap<String, String> paramsMap)
//			throws ControllerException {
//		try {
//			EtlExecution etlExecution;
//			etlExecution = etlService.executeById(paramsMap.get("executable"));
//			return etlExecution;
//		} catch (EtlException e) {
//			throw new ControllerException(e.getMessage(), e);
//		}
//	}

//	@GetMapping("/api/executebyname/{name}")
//	public EtlExecution executeByName(@PathVariable(value = "name") String name) throws ControllerException {
//		try {
//			EtlExecution etlExecution;
//			etlExecution = etlService.executeByName(name);
//			return etlExecution;
//		} catch (EtlException e) {
//			throw new ControllerException(e.getMessage(), e);
//		}
//	}

//	@PostMapping("/api/findexecution")
//	public EtlExecution findExecution(@Valid @RequestBody LinkedHashMap<String, String> paramsMap)
//			throws ControllerException {
//		String executionId = paramsMap.get("executionId");
//		try {
//			EtlExecution etlExecution;
//			etlExecution = etlService.findExecution(executionId);
//			return etlExecution;
//		} catch (EtlException e) {
//			throw new ControllerException(e.getMessage(), e);
//		}
//	}
}
