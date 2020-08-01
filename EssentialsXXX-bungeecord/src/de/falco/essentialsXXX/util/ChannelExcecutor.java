package de.falco.essentialsXXX.util;

import java.io.ByteArrayOutputStream;
import com.google.common.io.ByteArrayDataOutput;
import net.md_5.bungee.api.config.ServerInfo;

/*
 * class to use channel
 */
public interface ChannelExcecutor {
	

	
	
	//methode to send a message to the spigot server
	default void sendDataToServer(String channel, ServerInfo server, ByteArrayDataOutput data, String prefix) {
		
		System.out.println(prefix + " sendPluginMessage (Channel: " + channel + " , ServerName: " + server.getName() + " , data: " + data.toString() + ")");
		
		server.sendData(channel, data.toByteArray());
		
	}
	
	//methode to send a message to the spigot server
	default void sendDataToServer(String channel, ServerInfo server, ByteArrayOutputStream data, String prefix) {
		
		System.out.println(prefix + " sendPluginMessage (Channel: " + channel + " , ServerName: " + server.getName() + " , data: " + data.toString() + ")");
		
		server.sendData(channel, data.toByteArray());
		
	}
	
	
	
}
