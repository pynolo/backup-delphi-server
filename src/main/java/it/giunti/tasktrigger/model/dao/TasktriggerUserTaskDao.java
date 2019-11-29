package it.giunti.tasktrigger.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.giunti.tasktrigger.model.entity.TasktriggerUserTask;
 
@Repository("tasktriggerUserTaskDao")
public class TasktriggerUserTaskDao {
 
    @PersistenceContext
    private EntityManager entityManager;
 
    public TasktriggerUserTask selectUserTaskById(int id) {
        return entityManager.find(TasktriggerUserTask.class, id);
    }
 
	@SuppressWarnings("unchecked")
	public TasktriggerUserTask selectUserTaskByUserTask(int idUser, int idTask) {
		Query query = entityManager.createQuery(
				"from TasktriggerUserTask as ut where "+
				"ut.idUser = :id1 and "+
				"ut.idTask = :id2")
				.setParameter("id1", idUser)
				.setParameter("id2", idTask);
		List<TasktriggerUserTask> list = (List<TasktriggerUserTask>) query.getResultList();
		if (list != null) {
			if (list.size() > 0) return list.get(0);
		}
		return null;
	}

    public void insertUserTask(TasktriggerUserTask ut) {
        entityManager.persist(ut);
    }
    
    public void insertUserTask(int idUser, int idTask) {
    	TasktriggerUserTask ut = new TasktriggerUserTask();
    	ut.setIdUser(idUser);
    	ut.setIdTask(idTask);
        entityManager.persist(ut);
    }

    public void deleteUserTask(int id) {
        entityManager.remove(selectUserTaskById(id));
    }
 
    @SuppressWarnings("unchecked")
    public List<TasktriggerUserTask> selectAllUserTasks() {
        Query query = entityManager.createQuery("from TasktriggerUserTask as ut order by ut.id");
        return (List<TasktriggerUserTask>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<TasktriggerUserTask> selectAllUserTasksByUser(int idUser) {
        Query query = entityManager.createQuery(
        		"from TasktriggerUserTask as ut where "+
        		"ut.idUser = :id1 "+
        		"order by ut.id")
        		.setParameter(":id1", idUser);
        return (List<TasktriggerUserTask>) query.getResultList();
    }
}
