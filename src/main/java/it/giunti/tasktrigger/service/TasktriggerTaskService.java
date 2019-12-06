package it.giunti.tasktrigger.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.tasktrigger.model.dao.TasktriggerTaskDao;
import it.giunti.tasktrigger.model.entity.TasktriggerTask;
 
@Service("tasktriggerTaskService")
public class TasktriggerTaskService {
 
    @Autowired
    @Qualifier("tasktriggerTaskDao")
    private TasktriggerTaskDao taskDao;
 
    @Transactional
    public TasktriggerTask getTaskById(int id) {
        return taskDao.selectTaskById(id);
    }
    
    @Transactional
    public TasktriggerTask getTaskByName(String name) {
        return taskDao.selectTaskByName(name);
    }
    
    @Transactional
    public void addTask(TasktriggerTask task) {
    	taskDao.insertTask(task);
    }
 
    @Transactional
    public void modifyTask(TasktriggerTask task) {
        taskDao.updateTask(task);
    }
 
    @Transactional
    public List<TasktriggerTask> getAllTasks() {
        return taskDao.selectAllTasks();
    }
 
    @Transactional
    public void removeTask(int id) {
        taskDao.deleteTask(id);
    }
 
}
