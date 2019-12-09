package it.giunti.delphi.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.giunti.delphi.model.entity.DelphiTask;

@Repository("delphiTaskDao")
public class DelphiTaskDao {

	@PersistenceContext
	private EntityManager entityManager;

	public DelphiTask selectTaskById(int id) {
		return entityManager.find(DelphiTask.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public DelphiTask selectTaskByName(String name) {
		Query query = entityManager.createQuery(
				"from DelphiTask as task where "+
				"task.name like :s1")
				.setParameter("s1", name);
		List<DelphiTask> list = (List<DelphiTask>) query.getResultList();
		if (list != null) {
			if (list.size() > 0) return list.get(0);
		}
		return null;
	}
	
	public void insertTask(DelphiTask task) {
		entityManager.persist(task);
	}

	public void updateTask(DelphiTask task) {
		DelphiTask taskToUpdate = selectTaskById(task.getId());
		taskToUpdate.setName(task.getName());
		taskToUpdate.setDescription(task.getDescription());
		taskToUpdate.setWorkspaceId(task.getWorkspaceId());
		taskToUpdate.setWorkspaceName(task.getWorkspaceName());
		taskToUpdate.setEnvironamentId(task.getEnvironamentId());
		taskToUpdate.setEnvironmentName(task.getEnvironmentName());
		taskToUpdate.setAvailable(task.isAvailable());
		taskToUpdate.setExecutable(task.getExecutable());
		entityManager.flush();
	}

	public void deleteTask(int id) {
		entityManager.remove(selectTaskById(id));
	}

	@SuppressWarnings("unchecked")
	public List<DelphiTask> selectAllTasks() {
		Query query = entityManager.createQuery("from DelphiTask as task order by task.id");
		return (List<DelphiTask>) query.getResultList();
	}

}
