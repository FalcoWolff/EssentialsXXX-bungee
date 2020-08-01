package de.falco.essentialsXXX.depraced;

import java.util.Map;

public class Data {
	
	private String name;
	private Map<String,String> index;
	
	public Data(String name, Map<String,String> index) {
		this.index = index;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Map<String, String> getIndex() {
		return index;
	}
	

}
