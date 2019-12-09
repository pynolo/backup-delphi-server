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
	public DelphiUserTask selectUserTaskByUserTask(int idUser, int idTask) {
		Query query = entityManager.createQuery(
				"from DelphiUserTask as ut where "+
				"ut.idUser = :id1 and "+
				"ut.idTask = :id2")
				.setParameter("id1", idUser)
				.setParameter("id2", idTask);
		List<DelphiUserTask> list = (List<DelphiUserTask>) query.getResultList();
		if (list != null) {
			if (list.size() > 0) return list.get(0);
		}
		return null;
	}

    public void insertUserTask(DelphiUserTask ut) {
        entityManager.persist(ut);
    }
    
    public void insertUserTask(int idUser, int idTask) {
    	DelphiUserTask ut = new DelphiUserTask();
    	ut.setIdUser(idUser);
    	ut.setIdTask(idTask);
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
    public List<DelphiUserTask> selectAllUserTasksByUser(int idUser) {
        Query query = entityManager.createQuery(
        		"from DelphiUserTask as ut where "+
        		"ut.idUser = :id1 "+
        		"order by ut.id")
        		.setParameter(":id1", idUser);
        return (List<DelphiUserTask>) query.getResultList();
    }
}
