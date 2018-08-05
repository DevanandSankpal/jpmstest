package com.ds.jpmc.sales.messageprocessor.handler;

import com.ds.jpmc.sales.messageprocessor.exception.MessageProcessException;
import com.ds.jpmc.sales.messageprocessor.factory.SalesMessageFactory;
import com.ds.jpmc.sales.messageprocessor.model.Message;
/**
 * 
 * @author dsankpal
 *	THis class is handle message queue event to process message for report.
 *
 */
public class MessageEventHander {

	private static int lineNumber = 0;

	private MessageEventHander() {

	}

	public static boolean processMessage(String messageStr) {
		lineNumber = lineNumber + 1;
		if (messageStr != null) {
			String valueOf[] = messageStr.split(",");
			String type = valueOf[0];
			Message message = null;
			if (type.equalsIgnoreCase("MessageType")) {
				System.out.println("Ignore message for First Header row");
				return false;
			}
			try {
				message = SalesMessageFactory.getMessageInstance(type);
			} catch (MessageProcessException e2) {
				System.err.println(e2.getMessage());
				return false;
			}
			if(message==null){
				System.err.println("ERROR :Invalid Message Type, Row will be Ignored - LineNumber  "
						+ lineNumber);
				return false;
			}
				try {
					message.processMessage(valueOf);
					return true;
				} catch (MessageProcessException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				return false;
			
		} else {
			System.out.println("ERROR :Invalid Message, Row will be Ignored - LineNumber  "
							+ lineNumber);
		}
		return false;
	}

}