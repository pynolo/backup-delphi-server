package it.giunti.delphi.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "gge_sapreplymessages4business")
public class SapReplyLog {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_")
	private int idLog;
	@Column(name = "jobname")
	private String jobName;
	@Column(name = "zidinterfaccia")
	private String zIdInterfaccia;
	@Column(name = "bpid")
	private String bpid;
	@Column(name = "zidrecord")
	private String zIdRecord;
	@Column(name = "zidref")
	private String zIdRef;
	@Column(name = "type")
	private String type;
	@Column(name = "message")
	private String message;
	@Column(name = "parameter")
	private String parameter;
	@Column(name = "row")
	private Integer row;
	@Column(name = "sdata")
	private String sData;
	@Column(name = "dtdataacq")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtDataAcq;
	@Column(name = "dtdatamod")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtDataMod;
	
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

	public String getZIdInterfaccia() {
		return zIdInterfaccia;
	}

	public void setZIdInterfaccia(String zIdInterfaccia) {
		this.zIdInterfaccia = zIdInterfaccia;
	}

	public String getBpid() {
		return bpid;
	}

	public void setBpid(String bpid) {
		this.bpid = bpid;
	}

	public String getZIdRecord() {
		return zIdRecord;
	}

	public void setZIdRecord(String zIdRecord) {
		this.zIdRecord = zIdRecord;
	}

	public String getZIdRef() {
		return zIdRef;
	}

	public void setZIdRef(String zIdRef) {
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

	public String getSData() {
		return sData;
	}

	public void setSData(String sData) {
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