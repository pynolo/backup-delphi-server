package it.giunti.tasktrigger.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.tasktrigger.model.dao.TasktriggerUserTaskDao;
import it.giunti.tasktrigger.model.entity.TasktriggerUserTask;

@Service("tasktriggerUserTaskService")
public class TasktriggerUserTaskService {

	@Autowired
	@Qualifier("tasktriggerUserTaskDao")
	private TasktriggerUserTaskDao utDao;

	@Transactional
	public TasktriggerUserTask getUserTaskById(int id) {
		return utDao.selectUserTaskById(id);
	}

	@Transactional
	public TasktriggerUserTask getUserTaskByUserTask(int idUser, int idTask) {
		return utDao.selectUserTaskByUserTask(idUser, idTask);
	}
	
	@Transactional
	public void addUserTask(int idUser, int idTask) {
		utDao.insertUserTask(idUser, idTask);
	}

	@Transactional
	public void removeUserTask(int id) {
		utDao.deleteUserTask(id);
	}

	@Transactional
	public List<TasktriggerUserTask> getAllUserTasks() {
		return utDao.selectAllUserTasks();
	}

	@Transactional
	public List<TasktriggerUserTask> getAllUserTasksByUser(int idUser) {
		return utDao.selectAllUserTasksByUser(idUser);
	}

}
