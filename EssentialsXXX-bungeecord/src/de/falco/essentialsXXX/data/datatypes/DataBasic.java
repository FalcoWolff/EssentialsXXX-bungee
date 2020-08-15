package de.falco.essentialsXXX.data.datatypes;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataBasic {
	
	private Map<String,String> fields = new LinkedHashMap<>();
	private String servername;
	
	public DataBasic(String servername) {
		this.servername = servername;
	}
	
	public Map<String, String> getFields() {
		return fields;
	}
	public String getServername() {
		return servername;
	}
	
	public void setServername(String servername) {
		this.servername = servername;
	}
	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}
	
	public void addField(String key, String value) {
		fields.put(key, value);
	}

}
