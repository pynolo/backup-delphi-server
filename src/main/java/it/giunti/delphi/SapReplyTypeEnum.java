package it.giunti.delphi;

public enum SapReplyTypeEnum {
	POINTER("Y"),
	INFORMATIVE("I"),
	ERROR("E"),
	WARNING("W"),
	SUCCESS("S"),
	Z("Z");
	
	private final String typeString;
	
	SapReplyTypeEnum(String typeString) {
		this.typeString=typeString;
	}
	
	public String getTypeString() {
		return typeString;
	}
}
