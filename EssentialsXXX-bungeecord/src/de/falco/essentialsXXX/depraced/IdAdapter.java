package de.falco.essentialsXXX.depraced;

/*
 * interface to get the id of player
 * 
 * getidbyname:int
 * getidbyuuid:int
 */
@Deprecated
public interface IdAdapter {
	
	/*
	default String getnamebyid(int id) throws IdException{
		
		if(!Main.getEssentialsXXXmainS().getIdmysql().isconnect()) {
			Main.getEssentialsXXXmainS().getIdmysql().connect();
		}
		
		ResultSet result = Main.getEssentialsXXXmainS().getIdmysql().getResult("SELECT name FROM id WHERE id= '" + id + "'");
		
		try {
			result.first();
			return result.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IdException("player is not registered! ID: " + id);
		}
		
	}
	
	default int getidbyname(String name) throws IdException{
		
		if(!Main.getEssentialsXXXmainS().getIdmysql().isconnect()) {
			Main.getEssentialsXXXmainS().getIdmysql().connect();
		}
		
		ResultSet result = Main.getEssentialsXXXmainS().getIdmysql().getResult("SELECT id FROM id WHERE name='" + name + "'");
		try {
			result.first();
			return result.getInt(1);
		} catch (SQLException e) {
			throw new IdException("player is not registered! " + name);
		}
		
	}
	
	default int getidbyuuid(String uuid) throws IdException {
		
		if(!Main.getEssentialsXXXmainS().getIdmysql().isconnect()) {
			Main.getEssentialsXXXmainS().getIdmysql().connect();
		}
		
		ResultSet result = Main.getEssentialsXXXmainS().getIdmysql().getResult("SELECT id FROM id WHERE uuid='" + uuid + "'");
		try {
			result.first();
			return result.getInt(1);
		} catch (SQLException e) {
			throw new IdException("player is not registered! " + uuid);
		}
		
	}
	
	default String getuuidbyid(int id) throws IdException {
		
		if(!Main.getEssentialsXXXmainS().getIdmysql().isconnect()) {
			Main.getEssentialsXXXmainS().getIdmysql().connect();
		}
		
		ResultSet result = Main.getEssentialsXXXmainS().getIdmysql().getResult("SELECT uuid FROM id WHERE id='" + id + "'");
		
		try {
			result.first();
			return result.getString(1);
		} catch (SQLException e) {
			throw new IdException("player is not registered! " + id);
		}
		
	}
	*/

}
