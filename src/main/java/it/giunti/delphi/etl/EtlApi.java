package it.giunti.delphi.etl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.giunti.delphi.DelphiConfiguration;
import it.giunti.delphi.TaskType;

@Component
public class EtlApi {
	
	private static final String TASK_EXECUTABLE_PATH = "/executables";//GET
	private static final String PLAN_EXECUTABLE_PATH = "/executables/plans";//GET
	private static final String TASK_EXECUTION_PATH = "/executions";//POST
	private static final String PLAN_EXECUTION_PATH = "/executions/plans";//POST
	
	@Autowired
	DelphiConfiguration config;
	
	//List tasks
	public String getAllTasks(TaskType type) throws IOException {
		String urlPath=TASK_EXECUTABLE_PATH;
		if (type == TaskType.PLAN) urlPath = PLAN_EXECUTABLE_PATH;
		String responseString = HttpUtils.executeGet(
				config.getEndpoint()+urlPath,
				config.getToken());
		return responseString;
	}
	
	//Launch execution
	public String postExecution(TaskType type, String executable) throws IOException {
		String jsonBody = "{ \"executable\": \"" + executable + "\" }";
		String urlPath=TASK_EXECUTION_PATH;
		if (type == TaskType.PLAN) urlPath = PLAN_EXECUTION_PATH;
		String responseString = HttpUtils.executePost(
				config.getEndpoint()+urlPath,
				config.getToken(),
				jsonBody);
		return responseString;
	}
	
	//Execution status
	public String getExecution(TaskType type, String executionId) throws IOException {
		String urlPath=TASK_EXECUTION_PATH+"/"+executionId;
		if (type == TaskType.PLAN) urlPath = PLAN_EXECUTION_PATH+"/"+executionId;
		String responseString = HttpUtils.executeGet(
				config.getEndpoint()+urlPath,
				config.getToken());
		return responseString;
	}
}
