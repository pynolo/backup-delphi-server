package it.giunti.tasktrigger;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import it.giunti.tasktrigger.talend.TalendApi;
 
@SpringBootApplication
public class TasktriggerApplication {
	@Autowired
	TalendApi talendApi;
 
    public static void main(String[] args) {
        SpringApplication.run(TasktriggerApplication.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
    	String response = "";
    	try {
			response = talendApi.getExecutables();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(response);
    }
}