package it.giunti.delphi.model.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.giunti.delphi.SapReplyTypeEnum;
import it.giunti.delphi.model.entity.SapReplyLog;

@Repository("sapReplyLogDao")
public class SapReplyLogDao {

	@PersistenceContext
	private EntityManager entityManager;

	// Master: type = S, E, W, I, Z
	// Filtered master: type = E, W, I, Z
	
	@SuppressWarnings("unchecked")
	public List<SapReplyLog> findFilteredMasterByDate(Date startDatetime, Date finishDatetime, int maxResults) {
		Query query = entityManager.createQuery(
				"from SapReplyLog as srl where " +
				"srl.dtDataAcq > :t1 and " +
				"srl.dtDataAcq < :t2 and " +
				"type != :s1 and type != :s2 order by srl.idLog")
				.setMaxResults(maxResults)
				.setParameter("t1", startDatetime)
				.setParameter("t2", finishDatetime)
				.setParameter("s1", SapReplyTypeEnum.POINTER.getTypeString())
				.setParameter("s2", SapReplyTypeEnum.SUCCESS.getTypeString());
		List<SapReplyLog> resultList = (List<SapReplyLog>) query.getResultList();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<SapReplyLog> findByRefAndBpid(String zIdRef, String bpid) {
		Query query = entityManager.createQuery(
				"from SapReplyLog as srl where " +
				"srl.zIdRef = :s1 and " +
				"srl.bpid = :s2 order by srl.idLog")
				.setParameter("s1", zIdRef)
				.setParameter("s2", bpid);
		List<SapReplyLog> resultList = (List<SapReplyLog>) query.getResultList();
		return resultList;
	}

}
