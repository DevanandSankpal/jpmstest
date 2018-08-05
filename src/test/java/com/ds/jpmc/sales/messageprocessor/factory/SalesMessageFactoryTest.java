package com.ds.jpmc.sales.messageprocessor.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ds.jpmc.sales.messageprocessor.enums.MessageType;
import com.ds.jpmc.sales.messageprocessor.exception.UnSupportedMessageTypexception;
import com.ds.jpmc.sales.messageprocessor.model.Adjustment;
import com.ds.jpmc.sales.messageprocessor.model.Sale;

public class SalesMessageFactoryTest {

	@Test
	public void testGetMessageInstanceMessageType1() throws Exception {
		assertEquals(Sale.class, SalesMessageFactory.getMessageInstance(MessageType.MessageType1.name()).getClass());
	}
	
	@Test
	public void testGetMessageInstanceMessageType2() throws Exception {
		assertEquals(Sale.class, SalesMessageFactory.getMessageInstance(MessageType.MessageType2.name()).getClass());
	}
	
	@Test
	public void testGetMessageInstanceMessageType3() throws Exception {
		assertEquals(Adjustment.class, SalesMessageFactory.getMessageInstance(MessageType.MessageType3.name()).getClass());
	}

	@Test(expected=UnSupportedMessageTypexception.class)
	public void testGetMessageInstanceInvalidMessageType()throws Exception {
		SalesMessageFactory.getMessageInstance("MessageType4");
	}
}

