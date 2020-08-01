package de.falco.essentialsXXX.permission;

import java.util.ArrayList;
import java.util.UUID;

import de.falco.essentialsXXX.permission.type.Group;

public interface PermissionBasic {

	public abstract boolean hasPermission(UUID uuid , String permission);
	
	public abstract Group getGroup(String groupname);
	
	public abstract boolean isPlayerinGroup(UUID uuid, String p);
	
	public abstract ArrayList<Group> getPlayerGroups(UUID uuid);
	
}
