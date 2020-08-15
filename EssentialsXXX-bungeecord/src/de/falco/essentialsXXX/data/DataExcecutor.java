package de.falco.essentialsXXX.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import de.falco.essentialsXXX.EssentialsXXX;
import de.falco.essentialsXXX.data.datatypes.DataGroup;
import de.falco.essentialsXXX.data.datatypes.DataPlayer;
import de.falco.essentialsXXX.id.NotregisteredException;
import de.falco.essentialsXXX.id.PlayerData;
import de.falco.essentialsXXX.id.UserProfile;
import de.falco.essentialsXXX.permission.PermsManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Q: How to set the prefix of player?
 * A: This class try to offer a helper for prefix (variables)
 * 
 * 
 * @author Falco Wolf
 * @version 1.0
 *
 */
public interface DataExcecutor extends PlayerData{
	
	
	
	/**
	 * create a field with variables for a player
	 * 
	 * @param playername
	 * @return Map<String,String> with the key: "player" and the value playername
	 */
	default Map<String,String> getData(String playername, String server) {
		
		try {
			
			UserProfile u = getUserProfilebyUsername(playername);
			
			return getData(u.getUuid(), playername, server);
			
		} catch (NotregisteredException e) {
			
			Map<String,String> end = new LinkedHashMap<>();
			end.put("player", playername);
			end.put("server", server);
			
			return end;
			
		}
		
	}
	
	/**
	 * create a field with variables for a player
	 * execute the method getData with the uuid an "unknown"
	 * 
	 * @param uuid
	 * @return list with keys and values. When player didn't join there will be only one pair key: "player" value: "unknown"
	 */
	default Map<String,String> getData(UUID uuid, String server) {
		
		try {
			
			UserProfile u = getUserProfilebyuuid(uuid);
			return getData(uuid, u.getLastname(), server);
			
		} catch (NotregisteredException e) {
			
			Map<String,String> end = new LinkedHashMap<>();
			end.put("uuid", uuid.toString());
			end.put("server", server);
			
			return end;
			
			//return getData(uuid,"unknown");
			
		}
	}
	
	/**
	 * create a field with variables for a player
	 * 
	 * @param uuid
	 * @param pname
	 * @return list with keys and values. When player didn't join there will be only one pair key: "player" value: pname
	 */
	default Map<String,String> getData(UUID uuid, String pname, String server) {
		
		EssentialsXXX main = EssentialsXXX.getEssentialsXXXmain();
		
		ProxiedPlayer p = EssentialsXXX.getEssentialsXXXmain().getProxy().getPlayer(uuid);
		
		DataRequestEvent e = new DataRequestEvent(uuid, server, pname);
		
		Map<String,String> data = DataListenerManager.sendDataRequest(e);
		
		data.put("player", pname);
		data.put("uuid", uuid + "");
		data.put("server", server);
		
		//check the player section before the group section
		for(UUID playername : main.getDatamanager().getPlayers().keySet()) {
			
			if(!uuid.equals(playername)) {
				continue;
			}
			
			DataPlayer data_player= main.getDatamanager().getPlayer(playername);
			
			if(!data_player.getServername().equals(server) && !data_player.getServername().equals("")) {
				continue;
			}
			
			Map<String,String> d = data_player.getFields();
			
			for(String tmp : d.keySet()) {
				data.put(tmp, d.get(tmp));
			}
			
			return data;
			
		}
		
		//group section
		for(String groupname : main.getDatamanager().getGroups().keySet()) {
			
			DataGroup data_group = main.getDatamanager().getGroup(groupname);
			
			String pexG = data_group.getPex();
			
			if(p != null) {
				
			
				if(!p.hasPermission(pexG) && pexG.equals("") == false) {
					continue;
				}
				
				Map<String,String> d = data_group.getFields();
				
				for(String tmp : d.keySet()) {
					data.put(tmp, d.get(tmp));
				}
				
				return data;
				
			}else {
				
				if(pexG.equals("") == false) {
					
					boolean haspex = PermsManager.getPex().hasPermission(uuid, pexG);
					
					if(haspex == false) {
						continue;
					}
					
				}
				
				
				
				Map<String,String> d = data_group.getFields();
				
				for(String tmp : d.keySet()) {
					data.put(tmp, d.get(tmp));
				}
				
				return data;
					
				
			}
			
			
		}
		
		
		return data;
		
		//couldnt find a layout for the player
		//throw new NoLayoutFound("[" + main.getPluginName() + "] no layout found for player with uuid '" + uuid.toString() + "'");
	}
	
	/**
	 * replace a message with fields from the method getData
	 * after this progress the method delete the rest fields
	 * 
	 * @param message
	 * @param data
	 * @param suffix
	 * @return
	 */
	default String changeMessage(String message, Map<String,String> data, String suffix) {
		
		if(message == null || data == null || suffix == null) {
			throw new IllegalArgumentException("parameter couldnt be null");
		}
		
		for(String i : data.keySet()) {
				
			message = message.replaceAll("§" + i + suffix, data.get(i));
				
		}
		
		message = deletePlaceholder(message,suffix);
		
		return message;
		
	}
	
	/**
	 * remove every word ending with suffix
	 * 
	 * @param message
	 * @param suffix
	 * @return
	 */
	default String deletePlaceholder(String message, String suffix) {
		
		String[] args = message.split(" ");
		
		ArrayList<String> end = new ArrayList<>();
		
		for(int x = 0; x < args.length; x++) {
			if(!args[x].endsWith(suffix)) {
				end.add(args[x]);
			}
		}
		
		StringBuilder re = new StringBuilder();
		
		for(String i : end) {
			re.append(i + " ");
		}
		
		return re.replace(re.length() - 1, re.length() - 1, "").toString();
		
	}

	
}
