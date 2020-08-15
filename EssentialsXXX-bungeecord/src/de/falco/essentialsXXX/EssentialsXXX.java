package de.falco.essentialsXXX;

import java.io.IOException;
import java.sql.SQLException;

import de.falco.essentialsXXX.data.DataManager;
import de.falco.essentialsXXX.exception.NoPexException;
import de.falco.essentialsXXX.exception.NoServerException;
import de.falco.essentialsXXX.id.PlayerJoinListener;
import de.falco.essentialsXXX.util.MySql;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class EssentialsXXX extends Plugin{
	
	//de.falco.essentialsXXX.EssentialsXXX
	
	//config and main
	private ConfigFile EssentialsXXXconfig;
	private static EssentialsXXX EssentialsXXXmain;
	
	//plugin name
	private String name;
	private String prefix;
	private String chatprefix;
	
	//error message fields
	private String EssentialsXXXnotaplayer;
	private String EssentialsXXXnotonline;
	private String EssentialsXXXnopex;
	private String EssentialsXXXsyntax;
	
	//id-section
	private MySql idmysql;
	private de.falco.essentialsXXX.id.ConfigFile idconfig;
	private PlayerJoinListener idevent;
	
	//DataManager
	private DataManager datamanager;
	
	public static void main(String[] args) {
		System.out.println("hahah wft");
		System.exit(0);
	}
	
	@Override
	public void onEnable() {
		
		
		
		EssentialsXXXmain = this;
		
		this.name = EssentialsXXXmain.getDescription().getName();
		prefix = "[" + name + "]";
		chatprefix = "&7[&c" + name + "&7]";
		
		System.out.println(prefix + " --- load EssentialsXXX ---");
		
		//load configs
		loadConfigs();
		
		//load fields groups and player
		datamanager = new DataManager(this);
		
		try {
			datamanager.loadfields();
		} catch (NoServerException | NoPexException e) {
			e.printStackTrace();
		}
		
		//load db connection an try to connect
		loadDBConnections();
		
		//load events
		loadEvents();
		
		
		
	}
	
	@Override
	public void onDisable() {
		
		System.out.println(prefix + " good by");
		
		if(idmysql.isconnect()) {
			System.out.println(prefix + " close db connection");
			try {
				idmysql.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void loadDBConnections() {
		
		System.out.println(prefix + " load db connections");
		
		//id-section
		try {
			idmysql = new de.falco.essentialsXXX.util.MySql("com.mysql.jdbc.Driver", "jdbc:mysql://" + idconfig.getHost() + ":" + idconfig.getPort() + "/" + idconfig.getDatabase() + "?autoReconnect=true", idconfig.getUser(), idconfig.getPw());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			idmysql.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		idmysql.command("CREATE TABLE IF NOT EXISTS id (id INT(10) AUTO_INCREMENT PRIMARY KEY, uuid VARCHAR(50) NOT NULL, ips VARCHAR(20) NOT NULL)");
		System.out.println(prefix + " mysql-status: " +  idmysql.isconnect());
	}
	
	public void loadEvents() {
		
		System.out.println(prefix + " load Events");
		
		//debug tag event
		//DebugListener l = new DebugListener();
		
		idevent = new PlayerJoinListener(idmysql,prefix);
		
	}
	
	public void loadConfigs() {
		
		System.out.println(prefix + " load configs");
		
		//essentialsXXX config
		this.EssentialsXXXconfig = new ConfigFile(prefix,true);
		try {
			this.EssentialsXXXconfig.setup("config.yml",EssentialsXXXmain.getDataFolder());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.EssentialsXXXconfig.save();
		
		//id config
		idconfig = new de.falco.essentialsXXX.id.ConfigFile(prefix,true);
		try {
			idconfig.setup("id.yml", EssentialsXXXmain.getDataFolder());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//load error messages
		this.EssentialsXXXnotaplayer = EssentialsXXXconfig.getConfig().getString("config.message.error.notaplayer");
		this.EssentialsXXXnotonline = EssentialsXXXconfig.getConfig().getString("config.message.error.notonline");
		this.EssentialsXXXsyntax = EssentialsXXXconfig.getConfig().getString("config.message.error.syntax");
		this.EssentialsXXXnopex = EssentialsXXXconfig.getConfig().getString("config.message.error.nopex");
			
		
	}
	
	
	
	/*
	 * getter and setter
	 */
	
	public String getEssentialsXXXnotaplayer() {
		return EssentialsXXXnotaplayer;
	}
	
	public String getEssentialsXXXnotonline() {
		return EssentialsXXXnotonline;
	}
	
	public String getEssentialsXXXnopex() {
		return EssentialsXXXnopex;
	}
	
	public String getEssentialsXXXsyntax() {
		return EssentialsXXXsyntax;
	}
	
	public ConfigFile getEssentialsXXXconfig() {
		return EssentialsXXXconfig;
	}
	
	public MySql getIdmysql() {
		return idmysql;
	}
	public de.falco.essentialsXXX.id.ConfigFile getIdconfig() {
		return idconfig;
	}
	public PlayerJoinListener getIdevent() {
		return idevent;
	}
	public static EssentialsXXX getEssentialsXXXmain() {
		return EssentialsXXXmain;
	}
	public String getPrefix() {
		return prefix;
	}
	public String getChatprefix() {
		return chatprefix;
	}
	
	public DataManager getDatamanager() {
		return datamanager;
	}
	
	
	//getter for ExceptionError
	@SuppressWarnings("deprecation")
	public void ExceptionError(ProxiedPlayer p, String e) {
		p.sendMessage(ChatColor.GREEN + "sorry for this issue there is a error in your config please look in the konsole for more infos!");
	    p.sendMessage(ChatColor.GREEN + "when you are an normal user please report the bug to the server administration");
	    p.sendMessage(ChatColor.RED + "INFORMATIONS plugin: " + prefix + ChatColor.BLUE +  " error: " + e);
	   
	}
	
	
}