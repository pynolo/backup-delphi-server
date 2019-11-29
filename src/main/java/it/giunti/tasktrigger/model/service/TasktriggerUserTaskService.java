package it.giunti.tasktrigger.model.service;

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
	public TasktriggerUserTask getTaskById(int id) {
		return utDao.selectUserTaskById(id);
	}

	@Transactional
	public void addUserTask(TasktriggerUserTask task) {
		utDao.insertUserTask(task);
	}

	@Transactional
	public void modifyUserTask(TasktriggerUserTask task) {
		utDao.updateUserTask(task);
	}

	@Transactional
	public List<TasktriggerUserTask> getAllUserTasks() {
		return utDao.selectAllUserTasks();
	}

	@Transactional
	public void removeUserTask(int id) {
		utDao.deleteUserTask(id);
	}

}
