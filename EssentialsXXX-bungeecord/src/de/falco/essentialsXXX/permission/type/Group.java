package de.falco.essentialsXXX.permission.type;

public class Group {
	
	private String name;
	private String displayname;
	
	public Group(String name, String displayname) {
		this.name = name;
		this.displayname = displayname;
	}
	
	public String getDisplayname() {
		return displayname;
	}
	
	public String getName() {
		return name;
	}

}
