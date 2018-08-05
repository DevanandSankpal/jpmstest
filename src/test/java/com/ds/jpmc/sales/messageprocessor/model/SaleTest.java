package com.ds.jpmc.sales.messageprocessor.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ds.jpmc.sales.messageprocessor.data.store.SalesDataStore;
import com.ds.jpmc.sales.messageprocessor.enums.MessageType;
import com.ds.jpmc.sales.messageprocessor.exception.MessageProcessException;

public class SaleTest {

	
	
    private List<Sale> sales;
    private Sale sale1 = new Sale();
    private Sale sale2 = new Sale();
    private Sale sale3 = new Sale();
    private Sale sale4 = new Sale();
    private Sale sale5 = new Sale();
    private Sale sale6 = new Sale();
    private Sale sale7 = new Sale();
    
    
    @Before
    public void setUp() {
        sales = new ArrayList<>();
        SalesDataStore.salesData = sales;
    }

    @Test
    public void testProcessMessage_MessageType1_ok() throws Exception {
    	String[] message = new String [3];
    	message[0]=MessageType.MessageType1.name();
        message[1]="Mango";
        message[2]="2.00";
        sale1.processMessage(message);
        assertEquals(sale1, SalesDataStore.salesData.get(0));
    }

    @Test
    public void testProcessMessage_MessageType2_ok() throws Exception {
    	String[] message = new String [4];
    	message[0]=MessageType.MessageType2.name();
        message[1]="APPLE";
        message[2]="2.00";
        message[3]="2";
        sale2.processMessage(message);
        assertEquals(sale2, SalesDataStore.salesData.get(0));
    }
    
    
    @Test(expected = MessageProcessException.class)
    public void testProcessMessage_MessageType1_dataIssue() throws Exception {
    	String[] message = new String [2];
    	message[0]=MessageType.MessageType1.name();
        message[1]="APPLE";
        sale3.processMessage(message);
    }

    @Test(expected = MessageProcessException.class)
    public void testProcessMessage_MessageType2_dataIssue() throws Exception {
    	String[] message = new String [3];
    	message[0]=MessageType.MessageType2.name();
        message[1]="APPLE";
        message[2]="2.00";
        sale4.processMessage(message);
    }
	
    
    @Test(expected = MessageProcessException.class)
    public void testProcessMessage_InvalidMessageType() throws Exception {
    	String[] message = new String [3];
    	message[0]=MessageType.MessageType3.name();
        message[1]="APPLE";
        message[2]="2.00";
        sale5.processMessage(message);
    }
    
    @Test(expected = MessageProcessException.class)
    public void testProcessMessage_InvalidAmount() throws Exception {
    	String[] message = new String [3];
    	message[0]=MessageType.MessageType1.name();
        message[1]="APPLE";
        message[2]="ss.00";
        sale6.processMessage(message);
    }
	
    @Test(expected = MessageProcessException.class)
    public void testProcessMessage_InvalidQuantity() throws Exception {
    	String[] message = new String [3];
    	message[0]=MessageType.MessageType2.name();
        message[1]="APPLE";
        message[2]="2.00";
        message[2]="1.05";
        sale7.processMessage(message);
    }
	

}
