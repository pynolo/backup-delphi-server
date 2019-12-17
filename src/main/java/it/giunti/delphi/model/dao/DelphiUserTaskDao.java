package it.giunti.delphi.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.giunti.delphi.model.entity.DelphiUserTask;
 
@Repository("delphiUserTaskDao")
public class DelphiUserTaskDao {
 
    @PersistenceContext
    private EntityManager entityManager;
 
    public DelphiUserTask selectUserTaskById(int id) {
        return entityManager.find(DelphiUserTask.class, id);
    }
 
	@SuppressWarnings("unchecked")
	public DelphiUserTask selectUserTaskByUserTask(String username , String executable) {
		Query query = entityManager.createQuery(
				"from DelphiUserTask as ut where "+
				"ut.username = :id1 and "+
				"ut.executable = :id2")
				.setParameter("id1", username)
				.setParameter("id2", executable);
		List<DelphiUserTask> list = (List<DelphiUserTask>) query.getResultList();
		if (list != null) {
			if (list.size() > 0) return list.get(0);
		}
		return null;
	}

    public void insertUserTask(DelphiUserTask ut) {
        entityManager.persist(ut);
    }
    
    public void insertUserTask(String username , String executable) {
    	DelphiUserTask ut = new DelphiUserTask();
    	ut.setUsername(username);
    	ut.setExecutable(executable);
        entityManager.persist(ut);
    }

    public void deleteUserTask(int id) {
        entityManager.remove(selectUserTaskById(id));
    }
 
    @SuppressWarnings("unchecked")
    public List<DelphiUserTask> selectAllUserTasks() {
        Query query = entityManager.createQuery("from DelphiUserTask as ut order by ut.id");
        return (List<DelphiUserTask>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<DelphiUserTask> selectAllUserTasksByUser(String username) {
        Query query = entityManager.createQuery(
        		"from DelphiUserTask as ut where "+
        		"ut.username = :id1 "+
        		"order by ut.id")
        		.setParameter(":id1", username);
        return (List<DelphiUserTask>) query.getResultList();
    }
}
