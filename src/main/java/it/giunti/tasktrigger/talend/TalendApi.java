package it.giunti.tasktrigger.talend;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.giunti.tasktrigger.TasktriggerConfiguration;

@Component
public class TalendApi {
	
	@Autowired
	TasktriggerConfiguration config;
	
	//List jobs
	public String getExecutables() throws IOException {
		String urlPath="/executables";
		String responseString = HttpUtils.executeGet(
				config.getEndpoint()+urlPath,
				config.getToken());
		return responseString;
	}
	
	//Launch execution
	public String postExecutions(String jsonBody) throws IOException {
		String urlPath="/executions";
		String responseString = HttpUtils.executePost(
				config.getEndpoint()+urlPath,
				config.getToken(),
				jsonBody);
		return responseString;
	}
	
	//Execution status
	public String getExecutions(String executionId) throws IOException {
		String urlPath="/executables/"+executionId;
		String responseString = HttpUtils.executeGet(
				config.getEndpoint()+urlPath,
				config.getToken());
		return responseString;
	}
}
