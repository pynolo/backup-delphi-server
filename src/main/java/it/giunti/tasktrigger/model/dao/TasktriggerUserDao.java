package it.giunti.tasktrigger.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.giunti.tasktrigger.model.entity.TasktriggerUser;

@Repository("tasktriggerUserDao")
public class TasktriggerUserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public TasktriggerUser selectUserById(int id) {
		return entityManager.find(TasktriggerUser.class, id);
	}

	public void insertUser(TasktriggerUser user) {
		entityManager.persist(user);
	}

	public void updateUser(TasktriggerUser user) {
		TasktriggerUser userToUpdate = selectUserById(user.getId());
		userToUpdate.setUsername(user.getUsername());
		entityManager.flush();
	}

	public void deleteUser(int id) {
		entityManager.remove(selectUserById(id));
	}

	@SuppressWarnings("unchecked")
	public List<TasktriggerUser> selectAllUsers() {
		Query query = entityManager.createQuery("from TasktriggerUser as user order by user.id");
		return (List<TasktriggerUser>) query.getResultList();
	}

}
