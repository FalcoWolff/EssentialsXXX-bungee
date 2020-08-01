package de.falco.essentialsXXX.id;

import de.falco.essentialsXXX.EssentialsXXX;
import de.falco.essentialsXXX.util.ConfigAdapter;

/*
 * config for the id-secion 
 */
public class ConfigFile extends ConfigAdapter{
	
	private EssentialsXXX main = EssentialsXXX.getEssentialsXXXmain();
	
	/*
	 * mysql variables
	 */
	private String port;
	private String host;
	private String database;
	private String user;
	private String pw;

	/*
	 * constructor
	 */
	public ConfigFile(String prefix, boolean debug) {
		super(prefix, debug);
	}

	@Override
	public void onload() {
		
		if(getConfig().get("id.mysql.host") == null) {
			System.out.println(main.getPrefix() + " id.yml id.mysql.host ist null replace through default wort");
			getConfig().set("id.mysql.host", "localhost");
		}
		
		if(getConfig().get("id.mysql.port") == null) {
			System.out.println(main.getPrefix() + " id.yml id.mysql.port ist null replace through default wort");
			getConfig().set("id.mysql.port", "3306");
		}
		
		if(getConfig().get("id.mysql.database") == null) {
			System.out.println(main.getPrefix() + " id.yml id.mysql.database ist null replace through default wort");
			getConfig().set("id.mysql.database", "userid");
		}
		
		if(getConfig().get("id.mysql.user") == null) {
			System.out.println(main.getPrefix() + " id.yml id.mysql.user ist null replace through default wort");
			getConfig().set("id.mysql.user", "userid");
		}
		
		if(getConfig().get("id.mysql.pw") == null) {
			System.out.println(main.getPrefix() + " id.yml id.mysql.pw ist null replace through default wort");
			getConfig().set("id.mysql.pw", "userid");
		}
		
		/*
		 * mysql-variables
		 */
		this.host = getConfig().getString("id.mysql.host");
	    this.port = getConfig().getString("id.mysql.port");
	    this.database = getConfig().getString("id.mysql.database");
	    this.user = getConfig().getString("id.mysql.user");
	    this.pw = getConfig().getString("id.mysql.pw");
		
	}
	
	/*
	 * getter for mysql fields
	 */
	public String getHost() {
		return host;
	}
	public String getPort() {
		return port;
	}
	public String getDatabase() {
		return database;
	}
	public String getUser() {
		return user;
	}
	public String getPw() {
		return pw;
	}

	@Override
	public void onfirstload() {
		// TODO Auto-generated method stub
		
	}

}
