package com.ds.jpmc.sales.messageprocessor.exception;

public class EmptyQueueException extends Exception {


	private static final long serialVersionUID = 1L;

	public EmptyQueueException(String message) {
		super(message);
		
	}

	public EmptyQueueException(String message, Throwable t) {
		super(message, t);
	}

}
