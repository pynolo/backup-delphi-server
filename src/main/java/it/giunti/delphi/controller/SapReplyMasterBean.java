package it.giunti.delphi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.giunti.delphi.model.entity.SapReplyLog;

public class SapReplyMasterBean {
	
	private int idLog;
	private String jobName;
	private String bpid;
	private String type;
	private String message;
	private Date dtDataAcq;
	private int rowCount = 0;
	private List<SapReplyDetailBean> detailList = new ArrayList<SapReplyDetailBean>();

	public SapReplyMasterBean() {
		super();
	}
	
	public SapReplyMasterBean(SapReplyLog replyLog) {
		this.setIdLog(replyLog.getIdLog());
		this.setJobName(replyLog.getJobName());
		//this.setZIdInterfaccia(replyLog.getZIdInterfaccia());
		this.setBpid(replyLog.getBpid());
		//this.setZIdRecord(replyLog.getZIdRecord());
		//this.setZIdRef(replyLog.getZIdRef());
		this.setType(replyLog.getType());
		this.setMessage(replyLog.getMessage());
		//this.setParameter(replyLog.getParameter());
		//this.setRow(replyLog.getRow());
		//this.setSData(replyLog.getSData());
		this.setDtDataAcq(replyLog.getDtDataAcq());
		//this.setDtDataMod(replyLog.getDtDataMod());
	}

	public int getIdLog() {
		return idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getBpid() {
		return bpid;
	}

	public void setBpid(String bpid) {
		this.bpid = bpid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDtDataAcq() {
		return dtDataAcq;
	}

	public void setDtDataAcq(Date dtDataAcq) {
		this.dtDataAcq = dtDataAcq;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List<SapReplyDetailBean> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<SapReplyDetailBean> detailList) {
		this.detailList = detailList;
	}
}
