package it.giunti.delphi;

public enum SapReplyTypeEnum {
	POINTER("Y"),
	ERROR("E"),
	WARNING("W"),
	SUCCESS("S"),
	I("I"),
	Z("Z");
	
	private final String typeString;
	
	SapReplyTypeEnum(String typeString) {
		this.typeString=typeString;
	}
	
	public String getTypeString() {
		return typeString;
	}
}
