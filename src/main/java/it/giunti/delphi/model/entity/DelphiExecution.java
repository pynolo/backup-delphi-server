package it.giunti.delphi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delphi_execution")
public class DelphiExecution {

	@Id
    @Column(name = "execution_id")
	private String executionId; //"7b2b122e-d6b8-42de-b0ba-fa2f0d36306e",
	@Column(name = "start_timestamp")
	private String startTimestamp; //"2019-12-04T09:13:16.855Z",
	@Column(name = "finish_timestamp")
	private String finishTimestamp; //"2019-12-04T09:13:16.855Z",
	//private String userId; //"fupton",
	@Column(name = "job_id")
	private String jobId; //"57f64991e4b0b689a64feed0",
	@Column(name = "job_version")
	private String jobVersion; //"5.2",
	//private String environmentVersion; //"1.3",
	@Column(name = "execution_status")
	private String executionStatus; //"EXECUTION_EVENT_RECEIVED",
	//private String executionType; //"SCHEDULED",
	//private String executionDestination; //"REMOTE_ENGINE",
	//private String containerId; //"string",
	//private String runProfileId; //"157f818f-a901-4425-b592-0f9282687784",
	//private String remoteEngineId; //"157f818f-a901-4425-b592-0f9282687784",
	//private String remoteEngineClusterId; //"string",
	//private String numberOfProcessedRows; //1234567890,
	//private String numberOfRejectedRows; //0,
	////private String accountId; //"8494b016-b5ef-4b9c-b16d-8b1f824d7616",
	//private String workspaceId; //"57ce63d3e4b0681c36d1a1c4",
	//private String planId; //"string",
	@Column(name = "error_type")
	private String errorType; //"string",
	@Column(name = "error_message")
	private String errorMessage; //"string"
	
	@Column(name="executable")
	private String executable;
	
	
	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	public String getStartTimestamp() {
		return startTimestamp;
	}
	
	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	
	public String getFinishTimestamp() {
		return finishTimestamp;
	}
	
	public void setFinishTimestamp(String finishTimestamp) {
		this.finishTimestamp = finishTimestamp;
	}
	
	public String getJobId() {
		return jobId;
	}
	
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	public String getJobVersion() {
		return jobVersion;
	}
	
	public void setJobVersion(String jobVersion) {
		this.jobVersion = jobVersion;
	}
	
	public String getExecutionStatus() {
		return executionStatus;
	}
	
	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}
	
	public String getErrorType() {
		return errorType;
	}
	
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
    public String toString() {
        return "DelphiExecution [executionId=" + executionId + "]";
    }
 
}
