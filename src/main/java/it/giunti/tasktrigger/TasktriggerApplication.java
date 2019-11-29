package it.giunti.tasktrigger;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import it.giunti.tasktrigger.talend.TaskUpdater;
 
@SpringBootApplication
public class TasktriggerApplication {
	
	private static Logger LOG = LoggerFactory.getLogger(TasktriggerApplication.class);

	@Autowired
	TaskUpdater taskUpdater;
	
    public static void main(String[] args) {
        SpringApplication.run(TasktriggerApplication.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
    	try {
			taskUpdater.updateTasks();
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
    }
}