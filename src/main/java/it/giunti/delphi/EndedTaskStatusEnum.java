package it.giunti.delphi;

public enum EndedTaskStatusEnum {
	EXECUTION_SUCCESS("EXECUTION_SUCCESS"),
	EXECUTION_FAILED("EXECUTION_FAILED"),
	EXECUTION_TERMINATED("EXECUTION_TERMINATED");
	
	private final String statusName;
	
	EndedTaskStatusEnum(String statusName) {
		this.statusName=statusName;
	}
	
	public String getStatusName() {
		return statusName;
	}
}
