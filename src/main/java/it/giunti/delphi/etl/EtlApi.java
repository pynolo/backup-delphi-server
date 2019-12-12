package it.giunti.delphi.etl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.giunti.delphi.DelphiConfiguration;

@Component
public class EtlApi {
	
	@Autowired
	DelphiConfiguration config;
	
	//List tasks
	public String getAllTasks() throws IOException {
		String urlPath="/executables";
		String responseString = HttpUtils.executeGet(
				config.getEndpoint()+urlPath,
				config.getToken());
		return responseString;
	}
	
	//List plans
	public String getAllPlans() throws IOException {
		String urlPath="/executables/plans";
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
