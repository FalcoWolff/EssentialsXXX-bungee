package de.falco.essentialsXXX.depraced;

import java.util.Map;
import java.util.UUID;

import de.falco.essentialsXXX.EssentialsXXX;
import de.falco.essentialsXXX.id.NotregisteredException;
import de.falco.essentialsXXX.id.PlayerData;
import de.falco.essentialsXXX.id.UserProfile;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DebugListener implements Listener, de.falco.essentialsXXX.data.DataExcecutor, PlayerData{
	
	private EssentialsXXX main = EssentialsXXX.getEssentialsXXXmain();
	
	public DebugListener() {
		main.getProxy().getPluginManager().registerListener(main, this);
	}
	
	@EventHandler
	public void onChat(TabCompleteEvent event) {
		
		System.out.println("TabCompleteEvent");	
		
		String p = "luckperms.check";
			
		UUID uuid = UUID.fromString("505dc1c6-8e56-471b-9190-93c5d1435e94");
		
		
		System.out.println(uuid);
		
		
		Map<String,String> data = getData(uuid,"test");
		 
		System.out.println("get fields");
		
		for(String key : data.keySet()) {
			System.out.println(key + " " + data.get(key));
		}
		
		
		//boolean pex = PermsManager.getPex().hasPermission(uuid, p);
		//System.out.println(prefix + " pex: " + pex);
		
		
		System.out.println(" --------------------------------------- ");
				
		
		try {
			UserProfile u = getUserProfilebyuuid(uuid);
			
			System.out.println("userprofile " + u);
			
			UserProfile uu = getUserProfilebyUsername("RedThunder2403");
			
			System.out.println("userprofile " + uu);
			
		} catch (NotregisteredException e) {
			System.out.println("couldnt find uuid");
			e.printStackTrace();
			return;
		}
		
		
		
	}

}
