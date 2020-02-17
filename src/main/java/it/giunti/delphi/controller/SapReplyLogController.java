package it.giunti.delphi.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.giunti.delphi.model.entity.SapReplyLog;
import it.giunti.delphi.service.SapReplyLogService;

@RestController
@CrossOrigin(origins = "*")
public class SapReplyLogController {

	@Autowired
	@Qualifier("sapReplyLogService")
	private SapReplyLogService srlService;

	@PostMapping("/api/findsapfilteredmaster")
	public List<SapReplyLog> findSapFilteredMaster(@Valid @RequestBody SapMasterSearchBean bean) {
		List<SapReplyLog> resultList = srlService.findFilteredMasterByDate(
				bean.getStartDatetime(), bean.getFinishDatetime(), bean.getMaxResults(), bean.getUsername());
		return resultList;
	}

	@PostMapping("/api/findsapdetail")
	public List<SapReplyLog> findSapDetail(@Valid @RequestBody SapDetailSearchBean bean) {
		List<SapReplyLog> resultList = srlService.findByRefAndBpid(
				bean.getZIdRef(), bean.getBpid());
		return resultList;
	}

	// INNER CLASSES

	public static class SapMasterSearchBean {
		private String username;
		private Date startDatetime;
		private Date finishDatetime;
		private int maxResults;
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Date getStartDatetime() {
			return startDatetime;
		}
		public void setStartDatetime(Date startDatetime) {
			this.startDatetime = startDatetime;
		}
		public Date getFinishDatetime() {
			return finishDatetime;
		}
		public void setFinishDatetime(Date finishDatetime) {
			this.finishDatetime = finishDatetime;
		}
		public int getMaxResults() {
			return maxResults;
		}
		public void setMaxResults(int maxResults) {
			this.maxResults = maxResults;
		}
	}

	public static class SapDetailSearchBean {
		private String zIdRef;
		private String bpid;
		
		public String getZIdRef() {
			return zIdRef;
		}
		public void setZIdRef(String zIdRef) {
			this.zIdRef = zIdRef;
		}
		public String getBpid() {
			return bpid;
		}
		public void setBpid(String bpid) {
			this.bpid = bpid;
		}
	}
	
}
