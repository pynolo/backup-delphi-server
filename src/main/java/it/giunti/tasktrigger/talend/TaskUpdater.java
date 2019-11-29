package it.giunti.tasktrigger.talend;

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

import it.giunti.tasktrigger.model.entity.TasktriggerTask;
import it.giunti.tasktrigger.service.TasktriggerTaskService;

@Component
public class TaskUpdater {
	
	@Autowired
	TalendApi talendApi;
	@Autowired
	TasktriggerTaskService taskService;
	
	public void updateTasks() throws IOException {
		String response = "";
    	response = talendApi.getExecutables();
    	//Json parsing
    	JsonReader reader = Json.createReader(new StringReader(response));
    	JsonArray taskArray = reader.readArray();
    	Iterator<JsonValue> iter = taskArray.iterator();
    	markAllTasksAsUnavailable();
    	while (iter.hasNext()) {
    		JsonValue value = iter.next();
    		JsonObject obj = value.asJsonObject();
    		saveOrUpdateTask(obj);
    	}
	}
	
	private void markAllTasksAsUnavailable() {
		//Exixting Tasks on DB:
		List<TasktriggerTask> taskList = taskService.getAllTasks();
		//Set all as unavailable
		for (TasktriggerTask task:taskList) {
			task.setAvailable(false);
			taskService.modifyTask(task);
		}
	}
	
	private void saveOrUpdateTask(JsonObject obj) throws JsonException {
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
			List<TasktriggerTask> taskList = taskService.getAllTasks();
			//Match and update
			boolean found = false;
			for (TasktriggerTask task:taskList) {
				if (task.getExecutable().equals(executable)) {
					found = true;
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
				TasktriggerTask task = new TasktriggerTask();
				task.setExecutable(executable);
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
