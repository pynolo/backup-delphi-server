package it.giunti.delphi;

public enum RoleEnum {
	ADMIN("admin"),
	USER("user");
	
	private final String roleName;
	
	RoleEnum(String roleName) {
		this.roleName=roleName;
	}
	
	public String getRoleName() {
		return roleName;
	}
}
