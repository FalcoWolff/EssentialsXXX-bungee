package de.falco.essentialsXXX;

import de.falco.essentialsXXX.util.ConfigAdapter;

public class ConfigFile extends ConfigAdapter{

	private EssentialsXXX main = EssentialsXXX.getEssentialsXXXmain();
	
	public ConfigFile(String prefix,boolean debug) {
		super(prefix,debug);
	}
	
	@Override
	public void onload() {
	    if (getConfig().get("config.message.error.notonline") == null) {
	        System.out.println(main.getPrefix() + " config.yml config.message.error.notoline ist null replace through default wort");
	        getConfig().set("config.message.error.notonline", "&cthe player is not online");
	      } 
	      if (getConfig().get("config.message.error.notaplayer") == null) {
	        System.out.println(main.getPrefix() + " config.yml config.message.error.notaplayer ist null replace through default wort");
	        getConfig().set("config.message.error.notaplayer", "&cnot a player");
	      } 
	      if (getConfig().get("config.message.error.syntax") == null) {
	        System.out.println(main.getPrefix() + " config.yml config.message.error.syntax ist null replace through default wort");
	        getConfig().set("config.message.error.syntax", "&cSyntax error §syntax");
	      } 
	      if (getConfig().get("config.message.error.nopex") == null) {
	        System.out.println(main.getPrefix() + " config.yml config.message.error.nopex ist null replace through default wort");
	        getConfig().set("config.message.error.nopex", "&cNo pex for command §command");
	      } 
	}

	@Override
	public void onfirstload() {
		
	    System.out.println(main.getPrefix() + " config.yml load example");
	    getConfig().set("config.player.505dc1c6-8e56-471b-9190-93c5d1435e94.server", "all");
	    getConfig().set("config.player.505dc1c6-8e56-471b-9190-93c5d1435e94.prefix", "&d&l");
	    getConfig().set("config.player.505dc1c6-8e56-471b-9190-93c5d1435e94.rank", "&4&lDEVELOPER");
	    getConfig().set("config.groups.admin.pex", "msg.group.admin");
	    getConfig().set("config.groups.admin.server", "all");
	    getConfig().set("config.groups.admin.prefix", "&a&l");
	    getConfig().set("config.groups.admin.rank", "&4&lADMIN");
	    getConfig().set("config.groups.mod.pex", "msg.group.admin");
	    getConfig().set("config.groups.mod.server", "all");
	    getConfig().set("config.groups.mod.prefix", "&a&l");
	    getConfig().set("config.groups.mod.rank", "&4&lMOD");
	    getConfig().set("config.groups.sup.pex", "msg.group.sup");
	    getConfig().set("config.groups.sup.server", "all");
	    getConfig().set("config.groups.sup.prefix", "&a&l");
	    getConfig().set("config.groups.sup.rank", "&a&l&oSUP&r&2&l");
	    getConfig().set("config.groups.yt.pex", "msg.group.yt");
	    getConfig().set("config.groups.yt.server", "all");
	    getConfig().set("config.groups.yt.prefix", "&d");
	    getConfig().set("config.groups.yt.rank", "&5&lVIP");
	    getConfig().set("config.groups.vip.pex", "msg.group.vip");
	    getConfig().set("config.groups.vip.server", "all");
	    getConfig().set("config.groups.vip.prefix", "&b");
	    getConfig().set("config.groups.vip.rank", "&b&oVIP&r&9");
	    getConfig().set("config.groups.premium.pex", "msg.group.premium");
	    getConfig().set("config.groups.premium.server", "all");
	    getConfig().set("config.groups.premium.prefix", "&6");
	    getConfig().set("config.groups.premium.rank", "&6&oPREMIUM&r&e");
	    getConfig().set("config.groups.default.pex", "");
	    getConfig().set("config.groups.default.server", "all");
	    getConfig().set("config.groups.default.prefix", "&7");
	    getConfig().set("config.groups.default.rank", "&8DEFAULT&7");
		
	}

}
