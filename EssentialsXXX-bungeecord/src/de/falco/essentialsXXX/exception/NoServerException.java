package de.falco.essentialsXXX.exception;

//exception when a config path has no server field @see Main.loadfields()
public class NoServerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoServerException(String message) {
		super(message);
	}

}
