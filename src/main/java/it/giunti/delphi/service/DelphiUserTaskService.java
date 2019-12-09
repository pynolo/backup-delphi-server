package it.giunti.delphi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.delphi.model.dao.DelphiUserTaskDao;
import it.giunti.delphi.model.entity.DelphiUserTask;

@Service("delphiUserTaskService")
public class DelphiUserTaskService {

	@Autowired
	@Qualifier("delphiUserTaskDao")
	private DelphiUserTaskDao utDao;

	@Transactional
	public DelphiUserTask getUserTaskById(int id) {
		return utDao.selectUserTaskById(id);
	}

	@Transactional
	public DelphiUserTask getUserTaskByUserTask(int idUser, int idTask) {
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
	public List<DelphiUserTask> getAllUserTasks() {
		return utDao.selectAllUserTasks();
	}

	@Transactional
	public List<DelphiUserTask> getAllUserTasksByUser(int idUser) {
		return utDao.selectAllUserTasksByUser(idUser);
	}

}
