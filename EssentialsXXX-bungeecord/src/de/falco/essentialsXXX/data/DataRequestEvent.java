package de.falco.essentialsXXX.data;

import java.util.UUID;

public class DataRequestEvent {
	
	private UUID uuid;
	private String servername;
	private String playername;
	
	public DataRequestEvent() {
		
	}
	
	public DataRequestEvent(UUID uuid, String servername, String playername) {
		this.uuid = uuid;
		this.servername = servername;
		this.playername = playername;
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setServername(String servername) {
		this.servername = servername;
	}
	public String getServername() {
		return servername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public String getPlayername() {
		return playername;
	}

}
