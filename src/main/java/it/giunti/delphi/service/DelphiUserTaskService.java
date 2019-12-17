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
	public DelphiUserTask getUserTask(String username , String executable) {
		return utDao.selectUserTaskByUserTask(username, executable);
	}
	
	@Transactional
	public void addUserTask(String username , String executable) {
		utDao.insertUserTask(username, executable);
	}

	@Transactional
	public void removeUserTask(int id) {
		utDao.deleteUserTask(id);
	}

	@Transactional
	public void removeUserTask(String username , String executable) {
		DelphiUserTask ut = utDao.selectUserTaskByUserTask(username, executable);
		if (ut != null) {
			utDao.deleteUserTask(ut.getId());
		}
	}
	
	@Transactional
	public List<DelphiUserTask> getAllUserTasks() {
		return utDao.selectAllUserTasks();
	}

	@Transactional
	public List<DelphiUserTask> getAllUserTasksByUser(String username) {
		return utDao.selectAllUserTasksByUser(username);
	}

}
