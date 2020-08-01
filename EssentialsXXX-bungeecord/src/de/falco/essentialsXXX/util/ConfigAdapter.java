package de.falco.essentialsXXX.util;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

/**
 * Bungeecord Config basic class
 * 
 * @author Falco
 * @version 1.0
 *
 */
abstract public class ConfigAdapter {
	
	  private File file;
	  private Configuration config;
	  private String prefix; //prefix
	  private boolean debug; //debug?
	  
	  
	  /**
	   * execute when the setup method is called
	   * 
	   */
	  public abstract void onload();
	  
	  /**
	   * execute when the file created in the setup method
	   * 
	   */
	  public abstract void onfirstload();
	  
	  
	  /**
	   * Constructor (small version)
	   * 
	   */
	  public ConfigAdapter() {
	  }
	  
	  
	  /**
	   * Constructor (big version)
	   * 
	   * @param prefix
	   * @param debug
	   */
	  public ConfigAdapter(String prefix, boolean debug) {
		  this.prefix = prefix;
		  this.debug = debug;
	  }
	  
	  
	  
	  public void setup(String f, File path) throws IOException {
		  
		  File file = new File(path, f);
		  setup(file,path);
		  
	  }
	  
	  /**
	   * load the variables file and create the file
	   * 
	   * @param f
	   * @param path
	   * @throws IOException
	   */
	  public void setup(File f, File path) throws IOException{
		  
		 
		  
	    if (!path.exists()) {
	      path.mkdir();
	      if (this.debug) {
	          System.out.println(prefix + " create folder " + path.getName());   
	      }
	    }
	    
	    this.file = f;
	    
	    if (!f.exists()) {
	      if (this.debug) {
	          System.out.println(getPrefix() + " create file " + f.getName());
	      }
	      f.createNewFile();
		    this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		    onfirstload();
		    onload();
		    save();
	    }else {
	    	
		    this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		    onload();
		    save();
	    	
	    }
	    
	    
	  }
	  
	  /**
	   * load the YAMLConfiguration into the file
	   * 
	   * @throws IOException
	   */
	  public void save() {
		  try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	  
	  /**
	   * load the file into the YAMLConfiguration
	   * 
	   */
	  public void reload() {
	    try {
			this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	  
	  /*
	   * setter
	   */
	  
	  public void setDebug(boolean debug) {
		this.debug = debug;
	  }
	  public void setPrefix(String prefix) {
		this.prefix = prefix;
	  }
	  public void setFile(File file) {
		this.file = file;
	}
	  public void setConfig(Configuration config) {
		this.config = config;
	}
	  
	  /*
	   * getter
	   */
	  public Configuration getConfig() {
	    return this.config;
	  }
	  public File getFile() {
	    return this.file;
	  }
	  public String getPrefix() {
	    return this.prefix;
	  }
	  public boolean getDebug() {
		  return debug;
	  }
	
	
	
	
	

}
