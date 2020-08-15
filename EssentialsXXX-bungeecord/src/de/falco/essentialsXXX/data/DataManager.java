package de.falco.essentialsXXX.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import de.falco.essentialsXXX.ConfigFile;
import de.falco.essentialsXXX.EssentialsXXX;
import de.falco.essentialsXXX.data.datatypes.DataGroup;
import de.falco.essentialsXXX.data.datatypes.DataPlayer;
import de.falco.essentialsXXX.exception.NoPexException;
import de.falco.essentialsXXX.exception.NoServerException;

public class DataManager {
	
	private EssentialsXXX main;
	private ConfigFile config;
	
	public DataManager(EssentialsXXX main) {
		this.main = main;
		config = main.getEssentialsXXXconfig();
	}
	
	private Map<String,DataGroup> groups = new LinkedHashMap<>();
	private Map<UUID, DataPlayer> players = new LinkedHashMap<>();
	
	public DataGroup getGroup(String groupname) {
		
		if(hasGroup(groupname)) {
			return groups.get(groupname);
		}else {
			throw new IllegalStateException(main.getPrefix() + " no group with groupname " + groupname);
		}
		
	}
	
	public DataPlayer getPlayer(UUID uuid) {
		
		if(hasPlayer(uuid)) {
			return players.get(uuid);
		}else {
			throw new IllegalStateException(main.getPrefix() + " no player with uuid " + uuid);
		}
		
	}
	
	public boolean hasGroup(String groupname) {
		return groups.containsKey(groupname);
	}
	
	public boolean hasPlayer(UUID uuid) {
		return players.containsKey(uuid);
	}
	
	public void loadfields() throws NoServerException, NoPexException {
		
		//load groups
		if(config.getConfig().getSection("config.groups") != null) {
			
			for(String group : config.getConfig().getSection("config.groups").getKeys()) {
				
				Map<String,String> tmp = new LinkedHashMap<String, String>();
				
				String server = null;
				String pex = null;
				
				for(String index : config.getConfig().getSection("config.groups." + group).getKeys()) {
					
					if(index.equals("server")) {
						server = config.getConfig().getString("config.groups." + group + "." + index);
						continue;
					}
					
					if(index.equals("pex")) {
						pex = config.getConfig().getString("config.groups." + group + "." + index);
						continue;
					}
					
					tmp.put(index, config.getConfig().getString("config.groups." + group + "." + index));
					
				}
				
				if(server == null) {
					throw new NoServerException(main.getPrefix() + " error in group " + group + " no server!");
				}
				
				if(pex == null) {
					throw new NoPexException(main.getPrefix() + " error in group " + group + " no pex!");
				}
				
				DataGroup g = new DataGroup(server, pex);
				g.setFields(tmp);
				
				
				System.out.println(main.getPrefix() + " add group " + group);
				
				groups.put(group, g);
				
			}
			
			
		}else {
			System.out.println(main.getPrefix() + " no groups in config");
			//keine groups
		}
		
		
		//load player
		if(config.getConfig().getSection("config.player") != null) {
			
			for(String player : config.getConfig().getSection("config.player").getKeys()) {
				
				UUID uuid;
				
				try {
					
					uuid = UUID.fromString(player);
					
				}catch(Exception ex) {
					System.out.println(main.getPrefix()+ " error in player " + player + " invalid uuid");
					continue;
				}
				
				
				Map<String,String> tmp = new LinkedHashMap<String,String>();
				String server = null;
				
				for(String index : config.getConfig().getSection("config.player." + player).getKeys()) {
					
					if(index.equals("server")) {
						server = config.getConfig().getString("config.player." + player + "." + index);
						continue;
					}
					
					tmp.put(index, config.getConfig().getString("config.player." + player + "." + index));
					
					
				}
				
				if(server == null) {
					throw new NoServerException(main.getPrefix() + " error in player " + player + " no server!");
				}
				
				//Data data = new Data(player,tmp);
				
				System.out.println(main.getPrefix() + " load player " + player);
				
				DataPlayer p = new DataPlayer(server);
				p.setFields(tmp);
				
				this.players.put(uuid, p);
				
				
			}
			
			
			
		}else {//no player
			System.out.println(main.getPrefix() + " no player");
		}
		
	
	}
	
	public Map<String, DataGroup> getGroups() {
		return groups;
	}
	public Map<UUID, DataPlayer> getPlayers() {
		return players;
	}
	
}
