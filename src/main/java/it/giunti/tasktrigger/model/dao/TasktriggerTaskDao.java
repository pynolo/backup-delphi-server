package it.giunti.tasktrigger.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.giunti.tasktrigger.model.entity.TasktriggerTask;

@Repository("tasktriggerTaskDao")
public class TasktriggerTaskDao {

	@PersistenceContext
	private EntityManager entityManager;

	public TasktriggerTask selectTaskById(int id) {
		return entityManager.find(TasktriggerTask.class, id);
	}

	public void insertTask(TasktriggerTask task) {
		entityManager.persist(task);
	}

	public void updateTask(TasktriggerTask task) {
		TasktriggerTask taskToUpdate = selectTaskById(task.getId());
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
	public List<TasktriggerTask> selectAllTasks() {
		Query query = entityManager.createQuery("from TasktriggerTask as task order by task.id");
		return (List<TasktriggerTask>) query.getResultList();
	}

}
