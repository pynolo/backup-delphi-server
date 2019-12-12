package it.giunti.delphi.etl;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.giunti.delphi.TaskType;
import it.giunti.delphi.model.entity.DelphiTask;
import it.giunti.delphi.service.DelphiTaskService;

@Component
public class TaskUpdater {
	
	@Autowired
	EtlApi talendApi;
	@Autowired
	DelphiTaskService taskService;
	
	public void updateTasks() throws IOException {
		String taskResponse = "";
    	taskResponse = talendApi.getAllTasks();
    	String planResponse = "";
    	planResponse = talendApi.getAllPlans();
    	//Disattiva
    	markAllTasksAsUnavailable();
    	//Task json parsing
    	JsonReader reader = Json.createReader(new StringReader(taskResponse));
    	JsonArray taskArray = reader.readArray();
    	Iterator<JsonValue> taskIter = taskArray.iterator();
    	while (taskIter.hasNext()) {
    		JsonValue value = taskIter.next();
    		JsonObject obj = value.asJsonObject();
    		saveOrUpdateTask(obj, TaskType.TASK);
    	}
    	//Plan json parsing
    	reader = Json.createReader(new StringReader(planResponse));
    	JsonArray planArray = reader.readArray();
    	Iterator<JsonValue> planIter = planArray.iterator();
    	while (planIter.hasNext()) {
    		JsonValue value = planIter.next();
    		JsonObject obj = value.asJsonObject();
    		saveOrUpdateTask(obj, TaskType.PLAN);
    	}
	}
	
	private void markAllTasksAsUnavailable() {
		//Exixting Tasks on DB:
		List<DelphiTask> taskList = taskService.getAllTasks();
		//Set all as unavailable
		for (DelphiTask task:taskList) {
			task.setAvailable(false);
			taskService.modifyTask(task);
		}
	}
	
	private void saveOrUpdateTask(JsonObject obj, TaskType type) throws JsonException {
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
			//Exixting Tasks on DB:
			List<DelphiTask> taskList = taskService.getAllTasks();
			//Match and update
			boolean found = false;
			for (DelphiTask task:taskList) {
				if (task.getExecutable().equals(executable)) {
					found = true;
					task.setType(type.getTypeName());
					task.setName(name);
					task.setWorkspaceId(workspaceId);
					task.setWorkspaceName(workspaceName);
					task.setEnvironamentId(environmentId);
					task.setEnvironmentName(environmentName);
					task.setAvailable(true);
					taskService.modifyTask(task);
				}
			}
			//Save if not found
			if (!found) {
				DelphiTask task = new DelphiTask();
				task.setExecutable(executable);
				task.setType(type.getTypeName());
				task.setName(name);
				task.setDescription(name);
				task.setWorkspaceId(workspaceId);
				task.setWorkspaceName(workspaceName);
				task.setEnvironamentId(environmentId);
				task.setEnvironmentName(environmentName);
				task.setAvailable(true);
				taskService.addTask(task);
			}
		} else {
			throw new JsonException("'executable' does not have a value");
		}
	}
}
