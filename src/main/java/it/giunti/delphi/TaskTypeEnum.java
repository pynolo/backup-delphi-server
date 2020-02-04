package it.giunti.delphi;

public enum TaskTypeEnum {
	TASK("task"),
	PLAN("plan");
	
	private final String typeName;
	
	TaskTypeEnum(String roleName) {
		this.typeName=roleName;
	}
	
	public String getTypeName() {
		return typeName;
	}
}
