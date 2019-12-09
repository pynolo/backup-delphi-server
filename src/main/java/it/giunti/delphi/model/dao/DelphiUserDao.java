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

	public DelphiUser selectUserById(int id) {
		return entityManager.find(DelphiUser.class, id);
	}

	@SuppressWarnings("unchecked")
	public DelphiUser selectUserByUsername(String username) {
		Query query = entityManager.createQuery(
				"from DelphiUser as user where "+
				"user.username like :s1")
				.setParameter("s1", username);
		List<DelphiUser> list = (List<DelphiUser>) query.getResultList();
		if (list != null) {
			if (list.size() > 0) return list.get(0);
		}
		return null;
	}
	
	public void insertUser(DelphiUser user) {
		entityManager.persist(user);
	}

	public void updateUser(DelphiUser user) {
		DelphiUser userToUpdate = selectUserById(user.getId());
		userToUpdate.setUsername(user.getUsername());
		entityManager.flush();
	}

	public void deleteUser(int id) {
		entityManager.remove(selectUserById(id));
	}

	@SuppressWarnings("unchecked")
	public List<DelphiUser> selectAllUsers() {
		Query query = entityManager.createQuery("from DelphiUser as user order by user.id");
		return (List<DelphiUser>) query.getResultList();
	}

}
