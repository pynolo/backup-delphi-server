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

import it.giunti.delphi.TaskTypeEnum;
import it.giunti.delphi.etl.EtlApi;
import it.giunti.delphi.model.dao.DelphiTaskDao;
import it.giunti.delphi.model.entity.DelphiTask;
 
@Service("delphiTaskService")
public class DelphiTaskService {

	@Autowired
	private EtlApi talendApi;
    @Autowired
    @Qualifier("delphiTaskDao")
    private DelphiTaskDao taskDao;
 
    @Transactional
    public DelphiTask getTaskById(int id) {
        return taskDao.selectTaskById(id);
    }
    
//    @Transactional
//    public DelphiTask getTaskByName(String name) {
//        return taskDao.selectTaskByName(name);
//    }
    
    @Transactional
    public DelphiTask addTask(DelphiTask task) {
    	return taskDao.insertTask(task);
    }
 
    @Transactional
    public DelphiTask modifyTask(DelphiTask task) {
        return taskDao.updateTask(task);
    }
 
    @Transactional
    public List<DelphiTask> getAllAvailableTasks() {
        return taskDao.selectAllTasks(true);
    }
 
    @Transactional
    public void removeTask(int id) {
        taskDao.deleteTask(id);
    }

    @Transactional
    public List<DelphiTask> getAllTasksByUser(String username) {
    	return taskDao.selectAllTasksByUser(username);
    }
    
	
	@Transactional
	public void updateTasksAndPlans() throws IOException {
		String taskResponse = "";
    	taskResponse = talendApi.getAllTasks(TaskTypeEnum.TASK);
    	String planResponse = "";
    	planResponse = talendApi.getAllTasks(TaskTypeEnum.PLAN);
    	//Disattiva
    	markAllAsUnavailable();
    	//Task json parsing
    	JsonReader taskReader = Json.createReader(new StringReader(taskResponse));
    	JsonArray taskArray = taskReader.readArray();
    	Iterator<JsonValue> taskIter = taskArray.iterator();
    	while (taskIter.hasNext()) {
    		JsonValue value = taskIter.next();
    		JsonObject obj = value.asJsonObject();
    		saveOrUpdate(obj, TaskTypeEnum.TASK);
    	}
    	taskReader.close();
    	//Plan json parsing
    	JsonReader planReader = Json.createReader(new StringReader(planResponse));
    	JsonArray planArray = planReader.readArray();
    	Iterator<JsonValue> planIter = planArray.iterator();
    	while (planIter.hasNext()) {
    		JsonValue value = planIter.next();
    		JsonObject obj = value.asJsonObject();
    		saveOrUpdate(obj, TaskTypeEnum.PLAN);
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
	
	private void saveOrUpdate(JsonObject obj, TaskTypeEnum type) throws JsonException {
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
