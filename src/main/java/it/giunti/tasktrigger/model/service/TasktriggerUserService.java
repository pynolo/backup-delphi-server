package it.giunti.tasktrigger.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.tasktrigger.model.dao.TasktriggerUserDao;
import it.giunti.tasktrigger.model.entity.TasktriggerUser;

@Service("tasktriggerUserService")
public class TasktriggerUserService {

	@Autowired
	@Qualifier("tasktriggerUserDao")
	private TasktriggerUserDao userDao;

	@Transactional
	public TasktriggerUser getUserById(long id) {
		return userDao.selectUserById(id);
	}

	@Transactional
	public void addUser(TasktriggerUser task) {
		userDao.insertUser(task);
	}

	@Transactional
	public void modifyUser(TasktriggerUser task) {
		userDao.updateUser(task);
	}

	@Transactional
	public List<TasktriggerUser> getAllUsers() {
		return userDao.selectAllUsers();
	}

	@Transactional
	public void removeUser(long id) {
		userDao.deleteUser(id);
	}

}
