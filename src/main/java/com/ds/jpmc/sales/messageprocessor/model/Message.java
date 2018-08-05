package com.ds.jpmc.sales.messageprocessor.model;

import com.ds.jpmc.sales.messageprocessor.exception.MessageProcessException;

public interface Message {
	public void processMessage(String[] message) throws MessageProcessException;
}
