package de.falco.essentialsXXX;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import de.falco.essentialsXXX.depraced.DebugListener;
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
	
	//plugin name f
	private String name;
	private String prefix;
	private String chatprefix;
	
	
	//config fields
	private Map<String,Map<String,String>> EssentialsXXXgroups;
	private Map<UUID,Map<String,String>> EssentialsXXXplayer;
	
	//error message fields
	private String EssentialsXXXnotaplayer;
	private String EssentialsXXXnotonline;
	private String EssentialsXXXnopex;
	private String EssentialsXXXsyntax;
	
	//id-section
	private MySql idmysql;
	private de.falco.essentialsXXX.id.ConfigFile idconfig;
	private PlayerJoinListener idevent;
	
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
		try {
			loadfields();
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
	}
	
	private void loadfields() throws NoServerException, NoPexException {
		
		//load groups
		this.EssentialsXXXgroups = new LinkedHashMap<>();
		
		if(this.EssentialsXXXconfig.getConfig().getSection("config.groups") != null) {
			
			for(String group : EssentialsXXXconfig.getConfig().getSection("config.groups").getKeys()) {
				
				Map<String,String> tmp = new LinkedHashMap<String, String>();
				boolean server = false;
				boolean pex = false;
				
				for(String index : EssentialsXXXconfig.getConfig().getSection("config.groups." + group).getKeys()) {
					
					if(index.equals("server")) {
						server = true;
						if(EssentialsXXXconfig.getConfig().getString("config.groups." + group + "." + index).equals("")) {
							server = false;
						}
					}
					
					if(index.equals("pex")) {
						pex = true;
					}
					
					tmp.put(index, EssentialsXXXconfig.getConfig().getString("config.groups." + group + "." + index));
					
				}
				
				if(server == false) {
					throw new NoServerException(prefix + " error in group " + group + " no server!");
				}
				
				if(pex == false) {
					throw new NoPexException(prefix + " error in group " + group + " no pex!");
				}
				
				System.out.println(prefix + " add group " + group);
				
				this.EssentialsXXXgroups.put(group, tmp);
			}
			
			
		}else {
			System.out.println(prefix + " no groups in config");
			//keine groups
		}
		
		
		//load player
		this.EssentialsXXXplayer = new LinkedHashMap<>();
		if(EssentialsXXXconfig.getConfig().getSection("config.player") != null) {
			
			for(String player : EssentialsXXXconfig.getConfig().getSection("config.player").getKeys()) {
				
				UUID uuid;
				
				try {
					uuid = UUID.fromString(player);
				}catch(Exception ex) {
					
					System.out.println(prefix + " error in player " + player + " invalid uuid");
					
					continue;
				}
				
				
				Map<String,String> tmp = new LinkedHashMap<String,String>();
				boolean server = false;
				
				for(String index : EssentialsXXXconfig.getConfig().getSection("config.player." + player).getKeys()) {
					
					if(index.equals("server")) {
						server = true;
						if(EssentialsXXXconfig.getConfig().getString("config.player." + player + "." + index).equals("")) {
							server = false;
						}
					}
					
					tmp.put(index, EssentialsXXXconfig.getConfig().getString("config.player." + player + "." + index));
					
					
				}
				
				if(server == false) {
					throw new NoServerException(prefix + " error in player " + player + " no server!");
				}
				
				//Data data = new Data(player,tmp);
				
				System.out.println(prefix + " load player " + player);
				
				this.EssentialsXXXplayer.put(uuid, tmp);
				
			}
			
			
			
		}else {//no player
			System.out.println(prefix + " no player");
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
	public Map<String, Map<String, String>> getEssentialsXXXgroups() {
		return EssentialsXXXgroups;
	}
	
	public Map<UUID, Map<String, String>> getEssentialsXXXplayer() {
		return EssentialsXXXplayer;
	}
	
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
	
	
	//getter for ExceptionError
	@SuppressWarnings("deprecation")
	public void ExceptionError(ProxiedPlayer p, String e) {
		p.sendMessage(ChatColor.GREEN + "sorry for this issue there is a error in your config please look in the konsole for more infos!");
	    p.sendMessage(ChatColor.GREEN + "when you are an normal user please report the bug to the server administration");
	    p.sendMessage(ChatColor.RED + "INFORMATIONS plugin: " + prefix + ChatColor.BLUE +  " error: " + e);
	   
	}
	
	
}