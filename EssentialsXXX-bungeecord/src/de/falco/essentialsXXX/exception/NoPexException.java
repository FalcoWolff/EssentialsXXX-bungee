package de.falco.essentialsXXX.exception;

//exception when a section has no pex fields @see de.falco.essentialsXXX.Main.loadfields()
public class NoPexException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoPexException(String message) {
		super(message);
	}

}
