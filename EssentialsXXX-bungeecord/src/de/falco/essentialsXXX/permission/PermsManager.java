package de.falco.essentialsXXX.permission;

import de.falco.essentialsXXX.permission.luckperms.LuckPermsManager;

public class PermsManager {
	
	private static PermissionBasic pex = new LuckPermsManager();
	
	public static PermissionBasic getPex() {
		return pex;
	}
	public static void setPex(PermissionBasic pex) {
		PermsManager.pex = pex;
	}

}
