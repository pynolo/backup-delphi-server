package it.giunti.tasktrigger.talend;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.giunti.tasktrigger.TasktriggerConfiguration;

@Component
public class TalendApi {
	@Autowired
	public TasktriggerConfiguration config;
	
	public String getExecutables() throws IOException {
		String urlPath="/executables";
		String responseString = HttpUtils.executeRequest(config.getEndpoint()+urlPath, config.getToken());
		return responseString;
	}
}
