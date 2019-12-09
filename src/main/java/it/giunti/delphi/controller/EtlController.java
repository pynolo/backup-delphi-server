package it.giunti.delphi.controller;

import java.util.LinkedHashMap;

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
import it.giunti.delphi.EtlException;
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
			etlService.updateTasks();
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
    }
    
    @PostMapping("/api/executebyid")
    public EtlExecution executeById(@Valid @RequestBody LinkedHashMap<String, String> paramsMap) throws ControllerException {
    	try {
    		EtlExecution etlExecution;
			etlExecution = etlService.executeById(paramsMap.get("executable"));
			return etlExecution;
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
    }
    
    @GetMapping("/api/executebyname/{name}")
    public EtlExecution executeByName(@PathVariable(value = "name") String name) throws ControllerException {
    	try {
    		EtlExecution etlExecution;
			etlExecution = etlService.executeByName(name);
			return etlExecution;
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
    }
    
    @PostMapping("/api/findexecution")
    public EtlExecution findExecution(@Valid @RequestBody LinkedHashMap<String, String> paramsMap) throws ControllerException {
    	String executionId = paramsMap.get("executionId");
    	try {
    		EtlExecution etlExecution;
			etlExecution = etlService.findExecution(executionId);
			return etlExecution;
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
    }
}
