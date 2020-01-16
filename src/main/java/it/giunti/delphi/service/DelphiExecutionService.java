package it.giunti.delphi.service;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.delphi.EtlException;
import it.giunti.delphi.TaskType;
import it.giunti.delphi.etl.EtlApi;
import it.giunti.delphi.model.dao.DelphiExecutionDao;
import it.giunti.delphi.model.entity.DelphiExecution;

@Service("delphiExecutionService")
public class DelphiExecutionService {

	@Autowired
	private EtlApi talendApi;
    @Autowired
    @Qualifier("delphiExecutionDao")
    private DelphiExecutionDao exeDao;
    
    @Transactional
    public DelphiExecution executeByExecutable(TaskType type, String executable) throws EtlException {
    	DelphiExecution dbExe = retrieveExecutionByExecutable(type, executable);
    	DelphiExecution result = null;
    	if (dbExe == null) {
    		// Never run before -> run and insert db
    		DelphiExecution transientExe = remoteExecuteByExecutable(type, executable);
    		result = exeDao.insertExecution(transientExe);
    	} else {
    		if (dbExe.getFinishTimestamp() == null) dbExe.setFinishTimestamp("");
    		if (dbExe.getFinishTimestamp().length() == 0){
    			// Still running -> error
    			throw new EtlException("Task is still running", new Exception());
    		} else {
    			// Ready for new execution -> run, delete db & insert
    			DelphiExecution transientExe = remoteExecuteByExecutable(type, executable);
    			exeDao.deleteExecution(dbExe.getId());
    			result = exeDao.insertExecution(transientExe);
    		}
    	}
    	return result;
    }
    
    @Transactional
    public DelphiExecution retrieveExecutionByExecutable(TaskType type, String executable) throws EtlException {
    	DelphiExecution dbExe = exeDao.selectExecutionByExecutable(executable);
    	if (dbExe != null) {
    		return retrieveExecutionByExecutionId(type, dbExe.getId());
    	} else {
    		return null;
    	}
    }
    
    @Transactional
    public DelphiExecution retrieveExecutionByExecutionId(TaskType type, String executionId) throws EtlException {
    	DelphiExecution etlExe = remoteFindExecution(type, executionId);
    	if (etlExe != null) {
	    	DelphiExecution dbExe = exeDao.selectExecutionByExecutionId(executionId);
	    	if (dbExe != null) {
		    	dbExe.setErrorMessage(etlExe.getErrorMessage());
		    	dbExe.setErrorType(etlExe.getErrorType());
		    	dbExe.setExecutable(etlExe.getExecutable());
		    	dbExe.setExecutionStatus(etlExe.getExecutionStatus());
		    	dbExe.setFinishTimestamp(etlExe.getFinishTimestamp());
		    	dbExe.setStartTimestamp(etlExe.getStartTimestamp());
		    	dbExe.setJobId(etlExe.getJobId());
		    	dbExe.setJobVersion(etlExe.getJobVersion());
		    	exeDao.updateExecution(dbExe);
		    	return dbExe;
	    	} else {
	    		return null;
	    	}
    	}
    	return null;
    }

	private DelphiExecution remoteExecuteByExecutable(TaskType type, String executable) throws EtlException {
		try {
			String responseJson = talendApi.postExecution(type, executable);
			JsonReader reader = Json.createReader(new StringReader(responseJson));
			JsonObject obj = reader.readObject();
			DelphiExecution exe = new DelphiExecution();
			exe.setId(obj.getString("executionId"));
			exe.setExecutable(executable);
			return exe;
		} catch (IOException e) {
			throw new EtlException(e.getMessage(), e);
		}
	}

	private DelphiExecution remoteFindExecution(TaskType type, String executionId) throws EtlException {
		try {
			String responseJson = talendApi.getExecution(type, executionId);
			JsonReader reader = Json.createReader(new StringReader(responseJson));
			JsonObject obj = reader.readObject();
			DelphiExecution exe = new DelphiExecution();
			//exe.setAccountId(obj.getString("accountId"));
			//exe.setContainerId(obj.getString("containerId"));
			//exe.setEnvironmentVersion(obj.getString("environmentVersion"));
			try {exe.setErrorMessage(obj.getString("errorMessage"));} catch (Exception e) {}
			try {exe.setErrorType(obj.getString("errorType"));} catch (Exception e) {}
			//exe.setExecutionDestination(obj.getString("executionDestination"));
			exe.setId(obj.getString("executionId"));
			exe.setExecutionStatus(obj.getString("executionStatus"));
			//exe.setExecutionType(obj.getString("executionType"));
			try {exe.setFinishTimestamp(obj.getString("finishTimestamp"));} catch (Exception e) {}
			exe.setJobId(obj.getString("jobId"));
			//exe.setJobVersion(obj.getString("jobVersion"));
			//exe.setNumberOfProcessedRows(obj.getString("numberOfProcessedRows"));
			//exe.setNumberOfRejectedRows(obj.getString("numberOfRejectedRows"));
			//exe.setPlanId(obj.getString("planId"));
			//exe.setRemoteEngineClusterId(obj.getString("remoteEngineClusterId"));
			//exe.setRunProfileId(obj.getString("runProfileId"));
			try {exe.setStartTimestamp(obj.getString("startTimestamp"));} catch (Exception e) {}
			//exe.setWorkspaceId(obj.getString("workspaceId"));
			return exe;
		} catch (IOException e) {
			throw new EtlException(e.getMessage(), e);
		}
	}
	
}
