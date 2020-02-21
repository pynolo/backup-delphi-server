package it.giunti.delphi.controller;

import java.util.ArrayList;
import java.util.List;

import it.giunti.delphi.model.entity.SapReplyLog;

public class SapReplyDetailBean extends SapReplyLog {

	private List<SapReplyLog> subList = new ArrayList<SapReplyLog>();

	public SapReplyDetailBean() {
		super();
	}
	
	public SapReplyDetailBean(SapReplyLog replyLog) {
		this.setIdLog(replyLog.getIdLog());
		this.setJobName(replyLog.getJobName());
		this.setZIdInterfaccia(replyLog.getZIdInterfaccia());
		this.setBpid(replyLog.getBpid());
		this.setZIdRecord(replyLog.getZIdRecord());
		this.setZIdRef(replyLog.getZIdRef());
		this.setType(replyLog.getType());
		this.setMessage(replyLog.getMessage());
		this.setParameter(replyLog.getParameter());
		this.setRow(replyLog.getRow());
		this.setSData(replyLog.getSData());
		this.setDtDataAcq(replyLog.getDtDataAcq());
		this.setDtDataMod(replyLog.getDtDataMod());
	}
	
	public List<SapReplyLog> getSubList() {
		return subList;
	}

	public void setSubList(List<SapReplyLog> subList) {
		this.subList = subList;
	}
	
}
