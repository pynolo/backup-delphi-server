package it.giunti.tasktrigger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import it.giunti.tasktrigger.ControllerException;
import it.giunti.tasktrigger.EtlException;
import it.giunti.tasktrigger.etl.EtlExecution;
import it.giunti.tasktrigger.service.EtlService;

@RestController
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
    public EtlExecution launchExecution(String executable) throws ControllerException {
    	try {
    		EtlExecution etlExecution;
			etlExecution = etlService.launchExecution(executable);
			return etlExecution;
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
    }
    
    @PostMapping("/api/findexecution")
    public EtlExecution findExecution(String executionId) throws ControllerException {
    	try {
    		EtlExecution etlExecution;
			etlExecution = etlService.findExecution(executionId);
			return etlExecution;
		} catch (EtlException e) {
			throw new ControllerException(e.getMessage(), e);
		}
    }
}
