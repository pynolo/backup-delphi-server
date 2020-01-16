package it.giunti.delphi.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.delphi.EtlException;
import it.giunti.delphi.TaskType;
import it.giunti.delphi.etl.EtlApi;
import it.giunti.delphi.model.dao.DelphiTaskDao;
import it.giunti.delphi.model.entity.DelphiTask;
import it.giunti.delphi.model.entity.DelphiExecution;

@Service("etlService")
public class EtlService {

	@Autowired
	private EtlApi talendApi;
    @Autowired
    @Qualifier("delphiTaskDao")
    private DelphiTaskDao taskDao;
    
	public DelphiExecution executeById(TaskType type, String executable) throws EtlException {
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

//	@Transactional
//	public DelphiExecution executeByName(String name) throws EtlException {
//		DelphiTask task = taskDao.selectTaskByName(name);
//		return executeById(task.getExecutable());
//	}
	
	public DelphiExecution findExecution(TaskType type, String executionId) throws EtlException {
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
	
	@Transactional
	public void updateTasksAndPlans() throws IOException {
		String taskResponse = "";
    	taskResponse = talendApi.getAllTasks(TaskType.TASK);
    	String planResponse = "";
    	planResponse = talendApi.getAllTasks(TaskType.PLAN);
    	//Disattiva
    	markAllAsUnavailable();
    	//Task json parsing
    	JsonReader taskReader = Json.createReader(new StringReader(taskResponse));
    	JsonArray taskArray = taskReader.readArray();
    	Iterator<JsonValue> taskIter = taskArray.iterator();
    	while (taskIter.hasNext()) {
    		JsonValue value = taskIter.next();
    		JsonObject obj = value.asJsonObject();
    		saveOrUpdate(obj, TaskType.TASK);
    	}
    	taskReader.close();
    	//Plan json parsing
    	JsonReader planReader = Json.createReader(new StringReader(planResponse));
    	JsonArray planArray = planReader.readArray();
    	Iterator<JsonValue> planIter = planArray.iterator();
    	while (planIter.hasNext()) {
    		JsonValue value = planIter.next();
    		JsonObject obj = value.asJsonObject();
    		saveOrUpdate(obj, TaskType.PLAN);
    	}
    	planReader.close();
    	
	}
	
	private void markAllAsUnavailable() {
		//Exixting Tasks on DB:
		List<DelphiTask> taskList = taskDao.selectAllTasks(false);
		//Set all as unavailable
		for (DelphiTask task:taskList) {
			task.setAvailable(false);
			taskDao.updateTask(task);
		}
	}
	
	private void saveOrUpdate(JsonObject obj, TaskType type) throws JsonException {
		String executable = obj.getString("executable");
		if (executable == null) executable = "";
		if (executable.length() > 0) {
			String name = obj.getString("name");
			JsonObject workspace = obj.getJsonObject("workspace");
			String workspaceId = workspace.getString("id");
			String workspaceName = workspace.getString("name");
			JsonObject environment = workspace.getJsonObject("environment");
			String environmentId = environment.getString("id");
			String environmentName = environment.getString("name");
			//Existing Tasks on DB:
			List<DelphiTask> taskList = taskDao.selectAllTasks(false);
			//Match and update by executable
			boolean found = false;
			for (DelphiTask task:taskList) {
				if (hasSameExecutable(task, executable)) {
					found = true;
					task.setType(type.getTypeName());
					task.setName(name);
					task.setWorkspaceId(workspaceId);
					task.setWorkspaceName(workspaceName);
					task.setEnvironmentId(environmentId);
					task.setEnvironmentName(environmentName);
					task.setAvailable(true);
					taskDao.updateTask(task);
				}
			}
			//Match and update by details
			if (!found) {
				for (DelphiTask task:taskList) {
					if (hasSameDetails(task, name, workspaceName, environmentName)) {
						found = true;
						task.setExecutable(executable);
						task.setType(type.getTypeName());
						task.setName(name);
						task.setWorkspaceId(workspaceId);
						task.setWorkspaceName(workspaceName);
						task.setEnvironmentId(environmentId);
						task.setEnvironmentName(environmentName);
						task.setAvailable(true);
						taskDao.updateTask(task);
					}
				}
			}
			//Save if not found
			if (!found) {
				DelphiTask task = new DelphiTask();
				task.setExecutable(executable);
				task.setType(type.getTypeName());
				task.setName(name);
				task.setDescription("");
				task.setWorkspaceId(workspaceId);
				task.setWorkspaceName(workspaceName);
				task.setEnvironmentId(environmentId);
				task.setEnvironmentName(environmentName);
				task.setAvailable(true);
				taskDao.insertTask(task);
			}
		} else {
			throw new JsonException("'executable' does not have a value");
		}
	}
	
	private boolean hasSameExecutable(DelphiTask oldTask, String newExecutable) {
		return oldTask.getExecutable().equals(newExecutable);
	}
	
	private boolean hasSameDetails(DelphiTask oldTask, String newName, String newWorkspaceName, String newEnvironmentName) {
		if (newName == null) newName = "";
		if (newWorkspaceName == null) newWorkspaceName = "";
		if (newEnvironmentName == null) newEnvironmentName = "";
		return oldTask.getName().trim().equalsIgnoreCase(newName.trim()) &&
				oldTask.getWorkspaceName().trim().equalsIgnoreCase(newWorkspaceName.trim()) &&
				oldTask.getEnvironmentName().trim().equalsIgnoreCase(newEnvironmentName.trim());
	}
	
}
