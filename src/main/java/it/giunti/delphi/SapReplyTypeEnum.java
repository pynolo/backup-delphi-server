package it.giunti.delphi;

public enum SapReplyTypeEnum {

	ERROR("E"),
	INFORMATIVE("I"),
	SUCCESS("S"),
	WARNING("W"),
	POINTER("Y"),
	MANUAL("Z");
	
	private final String typeString;
	
	SapReplyTypeEnum(String typeString) {
		this.typeString=typeString;
	}
	
	public String getTypeString() {
		return typeString;
	}
}
