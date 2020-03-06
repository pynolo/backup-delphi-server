package it.giunti.delphi.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import it.giunti.delphi.SapReplyTypeEnum;
import it.giunti.delphi.controller.SapReplyDetailBean;
import it.giunti.delphi.controller.SapReplyMasterBean;
import it.giunti.delphi.model.entity.DelphiTask;
import it.giunti.delphi.model.entity.SapReplyLog;

@Repository("sapReplyLogDao")
public class SapReplyLogDao {

	@PersistenceContext
	private EntityManager entityManager;
    @Autowired
    @Qualifier("delphiTaskDao")
    private DelphiTaskDao taskDao;
 
	
	// Interroga SAP e aggrega le informazioni in modo gerarchico
	@SuppressWarnings("unchecked")
	public List<SapReplyMasterBean> findSapHierarchicLogByDate(Boolean showSuccess, String username,
			Date startDatetime, Date finishDatetime,
			Integer maxResults, String taskName) {
		List<SapReplyMasterBean> hierarchicList = new ArrayList<SapReplyMasterBean>();
		if (maxResults == null) maxResults = 1000;
		if (showSuccess == null) showSuccess = false;
		String filter = "";
		if (!showSuccess) filter += "and srl.type != :s1 ";
		if (taskName == null) taskName = "";
		if (username == null) username = "";
		if (taskName.length() > 2) {
			filter += "and srl.jobName = :s2 ";
		} else {
			if (username.length() > 2) filter += "and srl.jobName in (:names) ";
		}
		Query query = entityManager.createQuery(
				"from SapReplyLog as srl where " +
				"srl.dtDataAcq > :t1 and " +
				"srl.dtDataAcq < :t2 " + 
				filter +
				"order by srl.idLog desc")
				.setMaxResults(maxResults)
				.setParameter("t1", startDatetime)
				.setParameter("t2", finishDatetime);
		if (!showSuccess) query.setParameter("s1", SapReplyTypeEnum.SUCCESS.getTypeString());
		if (taskName.length() > 2) {
			query.setParameter("s2", taskName);
		} else {
			if (username.length() > 2) {
				List<DelphiTask> taskList = taskDao.selectAllTasksByUser(username);
				if (taskList.size() > 0) {
					Set<String> nameSet = new HashSet<String>();
					for (DelphiTask task:taskList) nameSet.add(task.getName());
					query.setParameter("names", nameSet);
				}
			}
		}
		List<SapReplyLog> queryList = (List<SapReplyLog>) query.getResultList();
		//Create hierarchy
		SapReplyMasterBean lastMaster = new SapReplyMasterBean();
		for (SapReplyLog replyLog:queryList) {
			if (!replyLog.getType().equals(SapReplyTypeEnum.POINTER.getTypeString())) {
				//NOT Y? => Create a 2-level hierarchy
				lastMaster = addToHierarchy(hierarchicList, replyLog, lastMaster);
			}
			//Add new master if new
			if (!hierarchicList.contains(lastMaster) && !(lastMaster.getType() == null)) {
				hierarchicList.add(lastMaster);
			}
		}
		for (SapReplyLog replyLog:queryList) {
			if (replyLog.getType().equals(SapReplyTypeEnum.POINTER.getTypeString())) {
				//IS Y? => Add to all corresponding rows as a sublevel (level 3)
				addPointerRow(hierarchicList, replyLog);
			}
		}
		adjustCounts(hierarchicList);
		return hierarchicList;
	}
	private SapReplyMasterBean addToHierarchy(List<SapReplyMasterBean> hierarchicList, 
			SapReplyLog replyLog, SapReplyMasterBean lastMaster) {
		//Aggregate to previousRow if (type, bpid, jobName, message) match
		//Distinctive values (zIdRecord, parameter, row, sData, dtDataAcq)
		if (replyLog.getType().equals(lastMaster.getType()) &&
				replyLog.getBpid().equals(lastMaster.getBpid()) &&
				replyLog.getJobName().equals(lastMaster.getJobName()) &&
				replyLog.getMessage().equals(lastMaster.getMessage())) {
			//Reuse lastMaster
			lastMaster.getDetailList().add(new SapReplyDetailBean(replyLog));
			// has been merged into master => return same master
		} else {
			//Create a new master with first detail row (self)
			lastMaster = new SapReplyMasterBean(replyLog);
			lastMaster.getDetailList().add(new SapReplyDetailBean(replyLog));
		}
		return lastMaster;
	}
	private void addPointerRow(List<SapReplyMasterBean> hierarchicList, 
			SapReplyLog replyLog) {
		for (SapReplyMasterBean master:hierarchicList) {
			for (SapReplyDetailBean detail:master.getDetailList()) {
				if (detail.getZIdRecord().equalsIgnoreCase(replyLog.getZIdRef())) {
					//Add to subList
					detail.getSubList().add(replyLog);
				}
			}
		}
	}
	private void adjustCounts(List<SapReplyMasterBean> hierarchicList) {
		int masterRowCount = 0;
		for (SapReplyMasterBean master:hierarchicList) {
			masterRowCount = 0;
			for (SapReplyDetailBean detail:master.getDetailList()) {
				int detailRowCount = 1;
				detailRowCount += detail.getSubList().size();
				masterRowCount += detailRowCount;
				detail.setRowCount(detailRowCount);
			}
			master.setRowCount(masterRowCount);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SapReplyLog> findByRefAndBpid(String zIdRef, String bpid) {
		Query query = entityManager.createQuery(
				"from SapReplyLog as srl where " +
				"srl.zIdRef = :s1 and " +
				"srl.bpid = :s2 order by srl.idLog desc")
				.setParameter("s1", zIdRef)
				.setParameter("s2", bpid);
		List<SapReplyLog> resultList = (List<SapReplyLog>) query.getResultList();
		return resultList;
	}

}
