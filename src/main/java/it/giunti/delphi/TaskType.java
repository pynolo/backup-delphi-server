package it.giunti.delphi;

public enum TaskType {
	TASK("task"),
	PLAN("plan");
	
	private final String typeName;
	
	TaskType(String roleName) {
		this.typeName=roleName;
	}
	
	public String getTypeName() {
		return typeName;
	}
}
