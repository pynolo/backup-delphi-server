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

import it.giunti.delphi.service.SapReplyLogService;

@RestController
@CrossOrigin(origins = "*")
public class SapReplyLogController {

	@Autowired
	@Qualifier("sapReplyLogService")
	private SapReplyLogService srlService;

	@PostMapping("/api/findsaphierarchiclog")
	public List<SapReplyMasterBean> findSapHierarchicLogByDate(@Valid @RequestBody SapSearchBean bean) {
		List<SapReplyMasterBean> resultList = srlService.findSapHierarchicLogByDate(bean.getShowSuccess(), bean.getUsername(),
				bean.getStartDatetime(), bean.getFinishDatetime(), bean.getMaxResults(), bean.getTaskName());
		return resultList;
	}

//	@PostMapping("/api/findsapdetail")
//	public List<SapReplyLog> findSapDetail(@Valid @RequestBody SapDetailSearchBean bean) {
//		List<SapReplyLog> resultList = srlService.findByRefAndBpid(
//				bean.getZIdRef(), bean.getBpid());
//		return resultList;
//	}

	// INNER CLASSES

	public static class SapSearchBean {
		private Boolean showSuccess;
		private String username;
		private Date startDatetime;
		private Date finishDatetime;
		private Integer maxResults;
		private String taskName;
		
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
		public String getTaskName() {
			return taskName;
		}
		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}
		public Boolean getShowSuccess() {
			return showSuccess;
		}
		public void setShowSuccess(Boolean showSuccess) {
			this.showSuccess = showSuccess;
		}
		public Integer getMaxResults() {
			return maxResults;
		}
		public void setMaxResults(Integer maxResults) {
			this.maxResults = maxResults;
		}
	}

//	public static class SapDetailSearchBean {
//		private String zIdRef;
//		private String bpid;
//		
//		public String getZIdRef() {
//			return zIdRef;
//		}
//		public void setZIdRef(String zIdRef) {
//			this.zIdRef = zIdRef;
//		}
//		public String getBpid() {
//			return bpid;
//		}
//		public void setBpid(String bpid) {
//			this.bpid = bpid;
//		}
//	}
	
}
