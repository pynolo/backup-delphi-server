package it.giunti.delphi.etl;

public class EtlExecution {
	
	private String executionId; //"7b2b122e-d6b8-42de-b0ba-fa2f0d36306e",
	private String startTimestamp; //"2019-12-04T09:13:16.855Z",
	private String finishTimestamp; //"2019-12-04T09:13:16.855Z",
	//private String userId; //"fupton",
	private String jobId; //"57f64991e4b0b689a64feed0",
	private String jobVersion; //"5.2",
	private String environmentVersion; //"1.3",
	private String executionStatus; //"EXECUTION_EVENT_RECEIVED",
	private String executionType; //"SCHEDULED",
	private String executionDestination; //"REMOTE_ENGINE",
	private String containerId; //"string",
	private String runProfileId; //"157f818f-a901-4425-b592-0f9282687784",
	private String remoteEngineId; //"157f818f-a901-4425-b592-0f9282687784",
	private String remoteEngineClusterId; //"string",
	private String numberOfProcessedRows; //1234567890,
	private String numberOfRejectedRows; //0,
	private String accountId; //"8494b016-b5ef-4b9c-b16d-8b1f824d7616",
	private String workspaceId; //"57ce63d3e4b0681c36d1a1c4",
	private String planId; //"string",
	private String errorType; //"string",
	private String errorMessage; //"string"
	
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
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
	public String getEnvironmentVersion() {
		return environmentVersion;
	}
	public void setEnvironmentVersion(String environmentVersion) {
		this.environmentVersion = environmentVersion;
	}
	public String getExecutionStatus() {
		return executionStatus;
	}
	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}
	public String getExecutionType() {
		return executionType;
	}
	public void setExecutionType(String executionType) {
		this.executionType = executionType;
	}
	public String getExecutionDestination() {
		return executionDestination;
	}
	public void setExecutionDestination(String executionDestination) {
		this.executionDestination = executionDestination;
	}
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String getRunProfileId() {
		return runProfileId;
	}
	public void setRunProfileId(String runProfileId) {
		this.runProfileId = runProfileId;
	}
	public String getRemoteEngineId() {
		return remoteEngineId;
	}
	public void setRemoteEngineId(String remoteEngineId) {
		this.remoteEngineId = remoteEngineId;
	}
	public String getRemoteEngineClusterId() {
		return remoteEngineClusterId;
	}
	public void setRemoteEngineClusterId(String remoteEngineClusterId) {
		this.remoteEngineClusterId = remoteEngineClusterId;
	}
	public String getNumberOfProcessedRows() {
		return numberOfProcessedRows;
	}
	public void setNumberOfProcessedRows(String numberOfProcessedRows) {
		this.numberOfProcessedRows = numberOfProcessedRows;
	}
	public String getNumberOfRejectedRows() {
		return numberOfRejectedRows;
	}
	public void setNumberOfRejectedRows(String numberOfRejectedRows) {
		this.numberOfRejectedRows = numberOfRejectedRows;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getWorkspaceId() {
		return workspaceId;
	}
	public void setWorkspaceId(String workspaceId) {
		this.workspaceId = workspaceId;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
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
}
