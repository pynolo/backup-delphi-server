package it.giunti.delphi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.delphi.model.dao.DelphiUserDao;
import it.giunti.delphi.model.entity.DelphiUser;

@Service("delphiUserService")
public class DelphiUserService {

	@Autowired
	@Qualifier("delphiUserDao")
	private DelphiUserDao userDao;

	@Transactional
	public DelphiUser getUserByUsername(String username) {
		return userDao.selectUserByUsername(username);
	}
	
	@Transactional
	public DelphiUser addUser(DelphiUser task) {
		return userDao.insertUser(task);
	}

	@Transactional
	public DelphiUser modifyUser(DelphiUser task) {
		return userDao.updateUser(task);
	}

	@Transactional
	public List<DelphiUser> getAllUsers() {
		return userDao.selectAllUsers();
	}

	@Transactional
	public void removeUser(String username) {
		userDao.deleteUser(username);
	}

}
