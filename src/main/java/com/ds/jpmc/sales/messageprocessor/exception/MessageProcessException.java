package com.ds.jpmc.sales.messageprocessor.exception;

public class MessageProcessException extends Exception{

	private static final long serialVersionUID = 1L;

	public MessageProcessException(String message) {
		super(message);
		
	}

	public MessageProcessException(String message, Throwable t) {
		super(message, t);
	}

}
