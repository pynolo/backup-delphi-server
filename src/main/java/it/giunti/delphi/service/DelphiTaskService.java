package it.giunti.delphi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.delphi.model.dao.DelphiTaskDao;
import it.giunti.delphi.model.entity.DelphiTask;
 
@Service("delphiTaskService")
public class DelphiTaskService {
 
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
    public List<DelphiTask> getAllTasks() {
        return taskDao.selectAllTasks();
    }
 
    @Transactional
    public void removeTask(int id) {
        taskDao.deleteTask(id);
    }

    @Transactional
    public List<DelphiTask> getAllTasksByUser(String username) {
    	return taskDao.selectAllTasksByUser(username);
    }
}
