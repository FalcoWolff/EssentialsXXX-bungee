package de.falco.essentialsXXX.id;

import java.sql.ResultSet;
import java.sql.SQLException;
import de.falco.essentialsXXX.EssentialsXXX;
import de.falco.essentialsXXX.util.MySql;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/*
 * generate id of player
 * 
 * onPlayerJoin:void
 */
public class PlayerJoinListener implements Listener{
	
	
	private MySql mysql;
	private String prefix;
	private EssentialsXXX main = EssentialsXXX.getEssentialsXXXmain();
	
	public PlayerJoinListener(MySql mysql, String prefix) {
		this.mysql = mysql;
		this.prefix = prefix;
		main.getProxy().getPluginManager().registerListener(main,this);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PostLoginEvent event) {
		
		
		
		ResultSet result = mysql.getResult("SELECT * FROM id WHERE uuid='" + event.getPlayer().getUniqueId().toString() + "'");
		try {
			
			if(result.first()) {
				int amount = result.getInt(1);
				System.out.println(prefix + " player " + event.getPlayer().getName() + " has the id " + amount);
			    if(!result.getString(3).contains(event.getPlayer().getAddress().getHostString())) {
			    	System.out.println(prefix + " player changed ip! Now register " + event.getPlayer().getAddress().getHostString());
			    	mysql.command("UPDATE id SET ips='" + result.getString(3) + "," + event.getPlayer().getAddress().getHostString() + "' WHERE id=" + amount);
			    }
				
			}else {//register player...
				System.out.println(prefix + " player " + event.getPlayer().getName() + " has no id! generate new one");
			    ResultSet worth = mysql.getResult("SELECT id FROM id");
			    
			    int amount;
			    
			    try {
			    worth.last();
			    amount = worth.getInt(1) + 1;
				} catch (SQLException e) {
					main.getProxy().getConsole().sendMessage(ChatColor.DARK_RED + "wow this is the first player joining the server! ");
					event.getPlayer().sendMessage("you are the first player ever registered");
					amount = 0;
				}
			    System.out.println(prefix + " player " + event.getPlayer().getName() + " has now id " + amount);
				mysql.command("INSERT INTO id (id,uuid,ips) VALUES (" + amount + ",'" + event.getPlayer().getUniqueId().toString() + "','" + event.getPlayer().getAddress().getHostString() + "')");
			}
			
		} catch (SQLException e) {
			System.out.println(prefix + " error in your id.yml");
			e.printStackTrace();
		}
		
	}

}
