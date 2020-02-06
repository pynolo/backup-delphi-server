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
	public DelphiTask selectTaskByExecutable(String executable) {
		Query query = entityManager.createQuery(
				"from DelphiTask as task where "+
				"task.executable = :s1")
				.setParameter("s1", executable);
		List<DelphiTask> list = (List<DelphiTask>) query.getResultList();
		if (list != null) {
			if (list.size() > 0) return list.get(0);
		}
		return null;
	}
	
	public DelphiTask insertTask(DelphiTask task) {
		entityManager.persist(task);
		return task;
	}

	public DelphiTask updateTask(DelphiTask task) {
		DelphiTask taskToUpdate = selectTaskById(task.getId());
		taskToUpdate.setType(task.getType());
		taskToUpdate.setName(task.getName());
		taskToUpdate.setDescription(task.getDescription());
		taskToUpdate.setWorkspaceId(task.getWorkspaceId());
		taskToUpdate.setWorkspaceName(task.getWorkspaceName());
		taskToUpdate.setEnvironmentId(task.getEnvironmentId());
		taskToUpdate.setEnvironmentName(task.getEnvironmentName());
		taskToUpdate.setAvailable(task.isAvailable());
		taskToUpdate.setExecutable(task.getExecutable());
		entityManager.merge(taskToUpdate);
		entityManager.flush();
		return task;
	}

	public DelphiTask updateTaskDescription(String executable, String description) {
		DelphiTask task = selectTaskByExecutable(executable);
		task.setDescription(description);
		entityManager.merge(task);
		entityManager.flush();
		return task;
	}
	
	public void deleteTask(int id) {
		DelphiTask task = selectTaskById(id);
		entityManager.merge(task);
		entityManager.remove(task);
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	public List<DelphiTask> selectAllTasks(boolean onlyAvailable) {
		String qs = "from DelphiTask as task ";
		if (onlyAvailable) qs += "where task.available = :b1 ";
		qs += "order by task.name ";
		Query query = entityManager.createQuery(qs);
		if (onlyAvailable) query.setParameter("b1", true);
		return (List<DelphiTask>) query.getResultList();
	}

    @SuppressWarnings("unchecked")
    public List<DelphiTask> selectAllTasksByUser(String username) {
        Query query = entityManager.createQuery(
        		"select dt from DelphiTask as dt, DelphiUserTask as dut where "+
        		"dt.executable = dut.executable and "+
        		"dut.username = :id1 and "+
        		"dt.available = :b1 "+
        		"order by dt.name")
        		.setParameter("id1", username)
        		.setParameter("b1", true);
        List<DelphiTask> taskList = (List<DelphiTask>) query.getResultList();
        return taskList;
    }
}
