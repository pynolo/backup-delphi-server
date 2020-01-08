package it.giunti.delphi.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.giunti.delphi.model.entity.DelphiUser;

@Repository("delphiUserDao")
public class DelphiUserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public DelphiUser selectUserByUsername(String username) {
		return entityManager.find(DelphiUser.class, username);
	}
//
//	@SuppressWarnings("unchecked")
//	public DelphiUser selectUserByUsername(String username) {
//		Query query = entityManager.createQuery(
//				"from DelphiUser as user where "+
//				"user.username like :s1")
//				.setParameter("s1", username);
//		List<DelphiUser> list = (List<DelphiUser>) query.getResultList();
//		if (list != null) {
//			if (list.size() > 0) return list.get(0);
//		}
//		return null;
//	}
	
	public DelphiUser insertUser(DelphiUser user) {
		entityManager.persist(user);
		return user;
	}

	public DelphiUser updateUser(DelphiUser user) {
		DelphiUser userToUpdate = selectUserByUsername(user.getUsername());
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setRole(user.getRole());
		entityManager.merge(userToUpdate);
		entityManager.flush();
		return userToUpdate;
	}

	public void deleteUser(String username) {
		entityManager.remove(selectUserByUsername(username));
	}

	@SuppressWarnings("unchecked")
	public List<DelphiUser> selectAllUsers() {
		Query query = entityManager.createQuery("from DelphiUser as user order by user.username");
		return (List<DelphiUser>) query.getResultList();
	}

}
