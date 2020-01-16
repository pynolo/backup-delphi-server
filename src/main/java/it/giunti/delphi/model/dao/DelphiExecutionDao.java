package it.giunti.delphi.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.giunti.delphi.model.entity.DelphiExecution;

@Repository("delphiExecutionDao")
public class DelphiExecutionDao {

	@PersistenceContext
	private EntityManager entityManager;

	public DelphiExecution selectExecutionByExecutionId(String id) {
		return entityManager.find(DelphiExecution.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public DelphiExecution selectExecutionByExecutable(String executable) {
		Query query = entityManager.createQuery(
				"from DelphiExecution as exe where "+
				"exe.executable like :s1")
				.setParameter("s1", executable);
		List<DelphiExecution> list = (List<DelphiExecution>) query.getResultList();
		if (list != null) {
			if (list.size() > 0) return list.get(0);
		}
		return null;
	}
	
	public DelphiExecution insertExecution(DelphiExecution exe) {
		entityManager.persist(exe);
		return exe;
	}

	public DelphiExecution updateExecution(DelphiExecution exe) {
		DelphiExecution exeToUpdate = selectExecutionByExecutionId(exe.getId());
		exeToUpdate.setErrorMessage(exe.getErrorMessage());
		exeToUpdate.setErrorType(exe.getErrorType());
		exeToUpdate.setExecutable(exe.getExecutable());
		exeToUpdate.setExecutionStatus(exe.getExecutionStatus());
		exeToUpdate.setFinishTimestamp(exe.getFinishTimestamp());
		exeToUpdate.setStartTimestamp(exe.getStartTimestamp());
		exeToUpdate.setJobId(exe.getJobId());
		exeToUpdate.setJobVersion(exe.getJobVersion());
		entityManager.merge(exeToUpdate);
		entityManager.flush();
		return exe;
	}

	public void deleteExecution(String id) {
		entityManager.remove(selectExecutionByExecutionId(id));
	}

}
