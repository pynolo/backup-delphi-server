package it.giunti.tasktrigger.model.dao;

import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
 
import org.springframework.stereotype.Repository;

import it.giunti.tasktrigger.model.jpa.TasktriggerTask;
 
@Repository("tasktriggerTaskDao")
public class TasktriggerTaskDao {
 
    @PersistenceContext
    private EntityManager entityManager;
 
    public TasktriggerTask selectTaskById(long id) {
        return entityManager.find(TasktriggerTask.class, id);
    }
 
    public void insertTask(TasktriggerTask task) {
        entityManager.persist(task);
    }
 
    public void updateTask(TasktriggerTask task) {
 
    	TasktriggerTask taskToUpdate = selectTaskById(task.getId());
 
    	taskToUpdate.setTaskName(task.getTaskName());
        taskToUpdate.setModifiedDate(task.getModifiedDate());
        entityManager.flush();
    }
 
    public void deleteTask(long id) {
        entityManager.remove(selectTaskById(id));
    }
 
    public List<TasktriggerTask> selectAllTasks() {
        Query query = entityManager.createQuery("from TasktriggerTask as task order by task.id");
        return (List<TasktriggerTask>) query.getResultList();
    }
 
}
