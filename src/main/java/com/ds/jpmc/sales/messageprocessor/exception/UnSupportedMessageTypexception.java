package com.ds.jpmc.sales.messageprocessor.exception;

public class UnSupportedMessageTypexception extends MessageProcessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnSupportedMessageTypexception(String message) {
		super(message);
		
	}

	public UnSupportedMessageTypexception(String message, Throwable t) {
		super(message, t);
	}

}
