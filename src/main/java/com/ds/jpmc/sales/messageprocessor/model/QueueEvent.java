package com.ds.jpmc.sales.messageprocessor.model;

import com.ds.jpmc.sales.messageprocessor.enums.MessageType;


public class QueueEvent {
    private MessageType messageType;
    private Message eventBody;

    public QueueEvent(MessageType messageType, Message eventBody) {
        this.messageType = messageType;
        this.eventBody = eventBody;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public Object getEventBody() {
        return eventBody;
    }

    @Override
    public String toString() {
        return "Event{" +
                "messageType=" + messageType +
                ", eventBody=" + eventBody +
                '}';
    }
}
