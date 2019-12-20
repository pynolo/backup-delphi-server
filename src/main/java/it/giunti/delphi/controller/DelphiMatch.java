package it.giunti.delphi.controller;

public class DelphiMatch {
	private boolean match = false;
	private String username = null;
	private String executable = null;
	
	public boolean isMatch() {
		return match;
	}

	public void setMatch(boolean match) {
		this.match = match;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	@Override
    public String toString() {
        return "DelphiMatch [value=" + this.match + "]";
    }
}
