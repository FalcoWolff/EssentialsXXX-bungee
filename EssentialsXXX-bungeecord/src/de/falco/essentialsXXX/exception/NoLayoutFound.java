package de.falco.essentialsXXX.exception;

//exception triggered when there isnt a layout for a player
public class NoLayoutFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoLayoutFound(String message) {
		super(message);
	}
}
