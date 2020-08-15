package de.falco.essentialsXXX.data.datatypes;

public class DataGroup extends DataBasic{

	private String pex;
	
	public DataGroup(String servername, String pex) {
		super(servername);
		this.pex = pex;
	}
	
	public String getPex() {
		return pex;
	}
	public void setPex(String pex) {
		this.pex = pex;
	}
	

}
