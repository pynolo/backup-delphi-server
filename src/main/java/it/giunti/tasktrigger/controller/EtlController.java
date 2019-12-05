package it.giunti.tasktrigger.controller;

import java.util.LinkedHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.giunti.tasktrigger.ControllerException;
import it.giunti.tasktrigger.EtlException;
import it.giunti.tasktrigger.etl.EtlExecution;
import it.giunti.tasktrigger.service.EtlService;

@RestController
@CrossOrigin(origins = "*")
public class EtlController {

    @Autowired
    @Qualifier("etlService")
    private EtlService etlService;
    
    @PostMapping("/api/launchtaskupdater")
    public void launchTaskUpdater() throws ControllerException {
    	try {
			etlService.launchTaskUpdater();
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
    }
    
    @PostMapping("/api/launchexecution")
    public EtlExecution launchExecution(@Valid @RequestBody LinkedHashMap<String, String> paramsMap) throws ControllerException {
    	try {
    		EtlExecution etlExecution;
			etlExecution = etlService.launchExecution(paramsMap.get("executable"));
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
