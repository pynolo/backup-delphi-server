package it.giunti.delphi;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.event.EventListener;

import it.giunti.delphi.etl.TaskUpdater;
 
@SpringBootApplication
public class DelphiApplication extends SpringBootServletInitializer {
	
	private static Logger LOG = LoggerFactory.getLogger(DelphiApplication.class);

	@Autowired
	TaskUpdater taskUpdater;
	
    public static void main(String[] args) {
        SpringApplication.run(DelphiApplication.class, args);
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