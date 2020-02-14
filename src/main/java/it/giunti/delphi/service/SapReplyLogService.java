package it.giunti.delphi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.giunti.delphi.model.dao.SapReplyLogDao;
import it.giunti.delphi.model.entity.SapReplyLog;

@Service("sapReplyLogService")
public class SapReplyLogService {
	//private static final Logger LOG = LoggerFactory.getLogger(SapReplyLogService.class);
	
	@Autowired
	@Qualifier("sapReplyLogDao")
	SapReplyLogDao srlDao;
	
	@Transactional
	public List<SapReplyLog> findFilteredMasterByDate(Date startDatetime, Date finishDatetime, int maxResults) {
		List<SapReplyLog> resultList = srlDao.findFilteredMasterByDate(startDatetime, finishDatetime, maxResults);
		if (resultList != null) {
			if (resultList.size() > 0) return resultList;
		}
		
		return new ArrayList<SapReplyLog>();
	}
	
	@Transactional
	public List<SapReplyLog> findByRefAndBpid(String zIdRef, String bpid) {
		List<SapReplyLog> resultList = srlDao.findByRefAndBpid(zIdRef, bpid);
		if (resultList != null) {
			if (resultList.size() > 0) return resultList;
		}
		
		return new ArrayList<SapReplyLog>();
	}
}
