package com.ds.jpmc.sales.messageprocessor.factory;

import java.util.HashMap;
import java.util.Map;

import com.ds.jpmc.sales.messageprocessor.enums.MessageType;
import com.ds.jpmc.sales.messageprocessor.exception.UnSupportedMessageTypexception;
import com.ds.jpmc.sales.messageprocessor.model.Message;
/**
 * 
 * @author dsankpal
 * This class is restricting to creating objects for messages. 
 * To include new message Type user has to add entry here with enums.
 *
 */
public class SalesMessageFactory {

	private static Map<String, String> instanceStore;

    static {
        initSingletonStore();
    }

    public static Message getMessageInstance(String type) throws UnSupportedMessageTypexception {
        final String className = instanceStore.get(type);

        if (className == null) {
            throw new UnSupportedMessageTypexception("Unrecognized event type " + type + ". Ignoring the event");
        }

        try {
			return ((Message) Class.forName(className).getConstructor().newInstance());
		} catch (Exception e) {
			throw new UnSupportedMessageTypexception("Unrecognized Class defination for Message type " + type + ". Ignoring the event");
		} 
    }
    // To prevent creation of a number of unused objects
    private static void initSingletonStore() {
        instanceStore = new HashMap<>();
        instanceStore.put(MessageType.MessageType1.name(), "com.ds.jpmc.sales.messageprocessor.model.Sale");
        instanceStore.put(MessageType.MessageType2.name(), "com.ds.jpmc.sales.messageprocessor.model.Sale");
        instanceStore.put(MessageType.MessageType3.name(), "com.ds.jpmc.sales.messageprocessor.model.Adjustment");
    }
}
