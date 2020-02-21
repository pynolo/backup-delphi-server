package it.giunti.delphi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.delphi.controller.SapReplyMasterBean;
import it.giunti.delphi.model.dao.SapReplyLogDao;

@Service("sapReplyLogService")
public class SapReplyLogService {
	//private static final Logger LOG = LoggerFactory.getLogger(SapReplyLogService.class);
	
	@Autowired
	@Qualifier("sapReplyLogDao")
	SapReplyLogDao srlDao;
	
	@Transactional
	public List<SapReplyMasterBean> findSapHierarchicLogByDate(Date startDatetime, Date finishDatetime,
			Integer maxResults, Boolean showSuccess, String username) {
		List<SapReplyMasterBean> resultList = srlDao.findSapHierarchicLogByDate(
				startDatetime, finishDatetime, maxResults, showSuccess, username);
		if (resultList != null) {
			if (resultList.size() > 0) return resultList;
		}
		
		return new ArrayList<SapReplyMasterBean>();
	}
	
	//@Transactional
	//public List<SapReplyLog> findByRefAndBpid(String zIdRef, String bpid) {
	//	List<SapReplyLog> resultList = srlDao.findByRefAndBpid(zIdRef, bpid);
	//	if (resultList != null) {
	//		if (resultList.size() > 0) return resultList;
	//	}
	//	
	//	return new ArrayList<SapReplyLog>();
	//}
}
