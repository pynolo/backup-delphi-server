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
 
    public TasktriggerUserTask selectUserTaskById(long id) {
        return entityManager.find(TasktriggerUserTask.class, id);
    }
 
    public void insertUserTask(TasktriggerUserTask ut) {
        entityManager.persist(ut);
    }
 
    public void updateUserTask(TasktriggerUserTask ut) {
    	TasktriggerUserTask utToUpdate = selectUserTaskById(ut.getId());
    	utToUpdate.setIdUser(ut.getIdUser());
    	utToUpdate.setIdTask(ut.getIdTask());
    	utToUpdate.setModifiedDate(ut.getModifiedDate());
        entityManager.flush();
    }
 
    public void deleteUserTask(long id) {
        entityManager.remove(selectUserTaskById(id));
    }
 
    @SuppressWarnings("unchecked")
    public List<TasktriggerUserTask> selectAllUserTasks() {
        Query query = entityManager.createQuery("from TasktriggerUserTask as ut order by ut.id");
        return (List<TasktriggerUserTask>) query.getResultList();
    }
 
}
