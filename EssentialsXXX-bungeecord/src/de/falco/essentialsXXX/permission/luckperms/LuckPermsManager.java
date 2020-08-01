package de.falco.essentialsXXX.permission.luckperms;

import java.util.ArrayList;
import java.util.UUID;
import de.falco.essentialsXXX.permission.PermissionBasic;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedPermissionData;
import net.luckperms.api.context.ContextManager;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import net.luckperms.api.query.QueryOptions;

public class LuckPermsManager implements PermissionBasic{
	
	public static LuckPerms api = LuckPermsProvider.get();
	
	@Override
	public boolean hasPermission(UUID uuid, String permission) {
		
		return hasPermission(getUser(uuid),permission);
		
	}

	@Override
	public de.falco.essentialsXXX.permission.type.Group getGroup(String groupname) {
		
		Group p = api.getGroupManager().getGroup(groupname);
		
		String name = p.getName();
		String displayname = p.getDisplayName();
		
		return new de.falco.essentialsXXX.permission.type.Group(name, displayname);
		
	}

	@Override
	public boolean isPlayerinGroup(UUID uuid, String p) {
		
		return isPlayerinGroup(getUser(uuid),p);
		
	}

	@Override
	public ArrayList<de.falco.essentialsXXX.permission.type.Group> getPlayerGroups(UUID uuid) {
		
		ArrayList<Group> groups = getPlayerGroups(getUser(uuid));
		
		ArrayList<de.falco.essentialsXXX.permission.type.Group> regroups = new ArrayList<>();
		
		for(Group p : groups) {
			regroups.add(new de.falco.essentialsXXX.permission.type.Group(p.getName(), p.getDisplayName()));
		}
		
		return regroups;
		
	}
	
	private User getUser(UUID uuid) {
		
		if(api.getUserManager().isLoaded(uuid)) {
			return api.getUserManager().getUser(uuid);
		}else {
			api.getUserManager().loadUser(uuid).join();
			return api.getUserManager().getUser(uuid);
		}
		
	}
	
	private boolean hasPermission(User u , String permission) {
		
		ContextManager cm = api.getContextManager();

		QueryOptions queryOptions = cm.getQueryOptions(u).orElse(cm.getStaticQueryOptions());
		
		CachedPermissionData permissionData = u.getCachedData().getPermissionData(queryOptions);
		
		return permissionData.checkPermission(permission).asBoolean();
		
	}
	
	private boolean isPlayerinGroup(User u, String p) {
		
		
		
		for(Node n : u.getNodes()) {
			
			if(n instanceof InheritanceNode) {
				
				if(n.getKey().equals("group." + p)) {
					return true;
				}
				
			}
			
		}
		
		return false;
		
	}
	
	private ArrayList<Group> getPlayerGroups(User u) {
		
		ArrayList<Group> group = new ArrayList<>();
		
		for(Node n : u.getNodes()) {
			
			System.out.println("key: " + n.getKey());
			
			if(n instanceof InheritanceNode) {
				
				String name = n.getKey().replaceFirst("group.", "");
				
				System.out.println("add to list " + name);
				
				Group p = api.getGroupManager().getGroup(name);
				
				group.add(p);
				
			}
			
			
		}
 		
		
		return group;
		
		
	}


}
