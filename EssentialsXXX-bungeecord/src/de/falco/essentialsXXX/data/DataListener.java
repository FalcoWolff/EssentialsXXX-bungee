package de.falco.essentialsXXX.data;

import java.util.UUID;

import de.falco.essentialsXXX.EssentialsXXX;
import de.falco.essentialsXXX.id.NotregisteredException;
import de.falco.essentialsXXX.id.PlayerData;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public interface DataListener extends PlayerData{
	
	public abstract String onDataRequest(DataRequestEvent event);

	
	
	default boolean isPlayerRegistered(String playername) {
		
		ProxiedPlayer p = EssentialsXXX.getEssentialsXXXmain().getProxy().getPlayer(playername);
		
		
		
		
		//if p ist not online set offline Object
		if(p == null) {
			
			
			try {
				getUserProfilebyUsername(playername);
				return true;
			} catch (NotregisteredException e) {
			}
			
				
		}
		
		return false;
		
	}
	
	default boolean isPlayerRegistered(UUID uuid) {
		
		ProxiedPlayer p = EssentialsXXX.getEssentialsXXXmain().getProxy().getPlayer(uuid);
		
		
		
		//if p ist not online set offline Object
		if(p == null) {
			
			try {
				getUserProfilebyuuid(uuid);
				return true;
			} catch (NotregisteredException e) {
			}
			
				
		}
		
		return false;
		
	}
	
}
