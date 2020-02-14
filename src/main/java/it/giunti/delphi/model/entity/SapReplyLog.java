package it.giunti.delphi.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "gge_sapreplymessages4business")
public class SapReplyLog {

	@Id
	@Column(name = "id_")
	private String idLog;
	@Column(name = "jobName")
	private String jobName;
	@Column(name = "zIdInterfaccia")
	private String zIdInterfaccia;
	@Column(name = "bpid")
	private String bpid;
	@Column(name = "zIdRecord")
	private String zIdRecord;
	@Column(name = "zIdRef")
	private String zIdRef;
	@Column(name = "type")
	private String type;
	@Column(name = "message")
	private String message;
	@Column(name = "parameter")
	private String parameter;
	@Column(name = "row")
	private Integer row;
	@Column(name = "sData")
	private String sData;
	@Column(name = "dtDataAcq")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtDataAcq;
	@Column(name = "dtDataMod")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtDataMod;

	public String getIdLog() {
		return idLog;
	}

	public void setIdLog(String idLog) {
		this.idLog = idLog;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getzIdInterfaccia() {
		return zIdInterfaccia;
	}

	public void setzIdInterfaccia(String zIdInterfaccia) {
		this.zIdInterfaccia = zIdInterfaccia;
	}

	public String getBpid() {
		return bpid;
	}

	public void setBpid(String bpid) {
		this.bpid = bpid;
	}

	public String getzIdRecord() {
		return zIdRecord;
	}

	public void setzIdRecord(String zIdRecord) {
		this.zIdRecord = zIdRecord;
	}

	public String getzIdRef() {
		return zIdRef;
	}

	public void setzIdRef(String zIdRef) {
		this.zIdRef = zIdRef;
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

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public String getsData() {
		return sData;
	}

	public void setsData(String sData) {
		this.sData = sData;
	}

	public Date getDtDataAcq() {
		return dtDataAcq;
	}

	public void setDtDataAcq(Date dtDataAcq) {
		this.dtDataAcq = dtDataAcq;
	}

	public Date getDtDataMod() {
		return dtDataMod;
	}

	public void setDtDataMod(Date dtDataMod) {
		this.dtDataMod = dtDataMod;
	}

	@Override
	public String toString() {
		return "SapReplyLog [idLog=" + idLog + "]";
	}

}