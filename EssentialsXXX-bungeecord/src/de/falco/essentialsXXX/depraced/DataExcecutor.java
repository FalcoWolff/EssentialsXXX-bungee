package de.falco.essentialsXXX.depraced;

/*
 * interface to getdata of a player
 */
public interface DataExcecutor extends IdAdapter{
	
	/*
	default Data getData(ProxiedPlayer p) throws NoLayoutFound{
		
		de.falco.essentialsXXX.Main main = de.falco.essentialsXXX.Main.getEssentialsXXXmainS();
		
		String server = p.getServer().getInfo().getName();
		
		String uuid = p.getUniqueId().toString();
		
		
		//players
		for(String playername : main.getEssentialsXXXplayer().keySet()) {
			
			//System.out.println("check player " + playername);
			
			if(!uuid.equals(playername)) {
				//System.out.println(uuid + " =! " + playername);
				continue;
			}
			
			String serverG = main.getEssentialsXXXplayer().get(playername).get("server");
			
			if(!serverG.equals(server) && serverG.equals("all") == false) {
				//System.out.println(serverG + " =! " + server);
				continue;
			}
			
			Data data = new Data(playername,main.getEssentialsXXXplayer().get(playername));
			
			//System.out.println("return data");
			
			return data;
			
		}
		
		//groups
		for(String groupname : main.getEssentialsXXXgroups().keySet()) {
			
			//System.out.println("check group " + groupname);
			
			String serverG = main.getEssentialsXXXgroups().get(groupname).get("server");
			String pexG = main.getEssentialsXXXgroups().get(groupname).get("pex");
			
			
			if(!server.equals(serverG) && serverG.equals("all") == false) {
				//System.out.println(server + " != " + serverG);
				continue;
			}
			
			if(!p.hasPermission(pexG) && pexG.equals("") == false) {
				//System.out.println(p.getName() + " has no pex " + pexG);
				continue;
			}
			
			Data data = new Data(groupname, main.getEssentialsXXXgroups().get(groupname));
			
			//System.out.println("return data");
			return data;
			
		}
		
		
		
		throw new NoLayoutFound("[" + main.getPluginName() + "] no layout found for player " + p.getName());
	}
	
	default String changeMessage(UUID uuid, String message, String suffix, Float money) {
		
		ProxiedPlayer p = BungeeCord.getInstance().getPlayer(uuid);
		
		if(p != null) {
			
			message = message.replaceAll("§player" + suffix, p.getName());
			message = message.replaceAll("§server" + suffix, p.getServer().getInfo() + "");
			message = message.replaceAll("§ping" + suffix, p.getPing() + "");
			
			if(money < 9999999) {
				message = message.replaceAll("§money" + suffix, money + "");	
			}else {
				message = message.replaceAll("§money" + suffix, money.intValue() + "");
			}
		
			Data data = null;
			try {
				data = getData(p);
				
				for(String i : data.getIndex().keySet()) {
					
					message = message.replaceAll("§" + i + suffix, data.getIndex().get(i));
					
				}
				
				return message;
				
			} catch (NoLayoutFound e) {
				
				e.printStackTrace();
				
				ArrayList<String> index = new ArrayList<>();
				
				for(String i : message.split(" ")) {
					
					if(i.contains("§") == false) {
						index.add(i);
					}
					
				}
				
				String end = "";
				
				for(String i : index) {
					end = end + " " + i;
				}
				
				return end;
			}
			
			
		}else {
			
			int id = 0;
			String name = null;
			try {
				id = getidbyuuid(uuid.toString());
				name = getnamebyid(id);
				
				message = message.replaceAll("§player" + suffix, name);
				
			} catch (IdException e) {
				e.printStackTrace();
			}
			
			
			
			if(money < 9999999) {
				message = message.replaceAll("§money" + suffix, money + "");	
			}else {
				message = message.replaceAll("§money" + suffix, money.intValue() + "");
			}
			
			ArrayList<String> index = new ArrayList<>();
			
			for(String i : message.split(" ")) {
				
				if(i.contains("§") == false) {
					index.add(i);
				}
				
			}
			
			String end = "";
			
			for(String i : index) {
				end = end + " " + i;
			}
			
			return end;
			
		}
		
	}
	
	
	default String changeMessage(ProxiedPlayer p, String message, Data data, String suffix, Float money) {
		
		
		message = message.replaceAll("§player" + suffix, p.getName());
		message = message.replaceAll("§server" + suffix, p.getServer().getInfo().getName());
		
		if(money < 9999999) {
			message = message.replaceAll("§money" + suffix, money + "");	
		}else {
			message = message.replaceAll("§money" + suffix, money.intValue() + "");
		}
		
		for(String i : data.getIndex().keySet()) {
			
			message = message.replaceAll("§" + i + suffix, data.getIndex().get(i));
			
		}
		
		
		return message;
		
	}

	*/
}