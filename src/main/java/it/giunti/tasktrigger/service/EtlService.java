package it.giunti.tasktrigger.service;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.giunti.tasktrigger.EtlException;
import it.giunti.tasktrigger.etl.EtlApi;
import it.giunti.tasktrigger.etl.EtlExecution;
import it.giunti.tasktrigger.etl.TaskUpdater;

@Service("talendService")
public class EtlService {

	@Autowired
	private EtlApi talendApi;
	@Autowired
	private TaskUpdater taskUpdater;

	public void launchTaskUpdater() throws EtlException {
		try {
			taskUpdater.updateTasks();
		} catch (IOException e) {
			throw new EtlException(e.getMessage(), e);
		}
	}

	public EtlExecution launchExecution(String executable) throws EtlException {
		String jsonBody = "{ \"executable\": \"" + executable + "\" }";
		try {
			String responseJson = talendApi.postExecutions(jsonBody);
			JsonReader reader = Json.createReader(new StringReader(responseJson));
			JsonObject obj = reader.readObject();
			EtlExecution exe = new EtlExecution();
			exe.setExecutionId(obj.getString("executionId"));
			return exe;
		} catch (IOException e) {
			throw new EtlException(e.getMessage(), e);
		}
	}

	public EtlExecution findExecution(String executionId) throws EtlException {
		try {
			String responseJson = talendApi.getExecutions(executionId);
			JsonReader reader = Json.createReader(new StringReader(responseJson));
			JsonObject obj = reader.readObject();
			EtlExecution exe = new EtlExecution();
			exe.setAccountId(obj.getString("accountId"));
			exe.setContainerId(obj.getString("containerId"));
			exe.setEnvironmentVersion(obj.getString("environmentVersion"));
			exe.setErrorMessage(obj.getString("errorMessage"));
			exe.setErrorType(obj.getString("errorType"));
			exe.setExecutionDestination(obj.getString("executionDestination"));
			exe.setExecutionId(obj.getString("executionId"));
			exe.setExecutionStatus(obj.getString("executionStatus"));
			exe.setExecutionType(obj.getString("executionType"));
			exe.setFinishTimestamp(obj.getString("finishTimestamp"));
			exe.setJobId(obj.getString("jobId"));
			exe.setJobVersion(obj.getString("jobVersion"));
			exe.setNumberOfProcessedRows(obj.getString("numberOfProcessedRows"));
			exe.setNumberOfRejectedRows(obj.getString("numberOfRejectedRows"));
			exe.setPlanId(obj.getString("planId"));
			exe.setRemoteEngineClusterId(obj.getString("remoteEngineClusterId"));
			exe.setRunProfileId(obj.getString("runProfileId"));
			exe.setStartTimestamp(obj.getString("startTimestamp"));
			exe.setWorkspaceId(obj.getString("workspaceId"));
			return exe;
		} catch (IOException e) {
			throw new EtlException(e.getMessage(), e);
		}
	}
}
