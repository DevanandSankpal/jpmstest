package com.ds.jpmc.sales.messageprocessor.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ds.jpmc.sales.messageprocessor.data.store.SalesDataStore;
import com.ds.jpmc.sales.messageprocessor.enums.MessageType;
import com.ds.jpmc.sales.messageprocessor.exception.MessageProcessException;

public class AdjustmentTest {

	private Adjustment adjustment;
    private List<Sale> sales;
    
    private Sale sale1 = new Sale();
    private Sale sale2 = new Sale();
    private Sale sale3 = new Sale();

    @Before
    public void setUp() {
        sales = new ArrayList<>();
        sale1.setProductName("Apple");
        sale1.setUnitPrice(BigDecimal.valueOf(2.00));
        sale1.setMessageType(MessageType.MessageType1);
        sales.add(sale1);
        sale2.setProductName("Mango");
        sale2.setUnitPrice(BigDecimal.valueOf(2.00));
        sale2.setMessageType(MessageType.MessageType1);
        sales.add(sale2);
        sale3.setProductName("Orange");
        sale3.setUnitPrice(BigDecimal.valueOf(1.00));
        sale3.setMessageType(MessageType.MessageType2);
        sale3.setTotalUnits(3);
        sales.add(sale3);
        adjustment= new Adjustment();
        SalesDataStore.salesData = sales;
    }

    @Test
    public void processMessage_Add() throws Exception {
        String[] adjustMessage = new String [5];
        adjustMessage[0]=MessageType.MessageType3.name();
        adjustMessage[1]="Mango";
        adjustMessage[2]="0.08";
        adjustMessage[3]="";
        adjustMessage[4]="ADD";
        
        adjustment.processMessage(adjustMessage);
        for (Sale sale : SalesDataStore.salesData) {
            if (sale.getProductName().equals("Apple")) {
                assertEquals(BigDecimal.valueOf(2.00), sale.getUnitPrice());
            } else if (sale.getProductName().equals("Mango")) {
                assertEquals(BigDecimal.valueOf(2.08), sale.getUnitPrice());
            }
        }
    }

    @Test
    public void processMessage_subtract() throws Exception {
    	String[] adjustMessage = new String [5];
        adjustMessage[0]=MessageType.MessageType3.name();
        adjustMessage[1]="Mango";
        adjustMessage[2]="0.11";
        adjustMessage[3]="";
        adjustMessage[4]="SUBTRACT";
        
        adjustment.processMessage(adjustMessage);
        for (Sale sale : SalesDataStore.salesData) {
            if (sale.getProductName().equals("Apple")) {
                assertEquals(BigDecimal.valueOf(2.00), sale.getUnitPrice());
            } else if (sale.getProductName().equals("Mango")) {
                assertEquals(BigDecimal.valueOf(1.89), sale.getUnitPrice());
            }
        }
    }

    @Test
    public void processMessage_multiply() throws Exception {
    	String[] adjustMessage = new String [5];
        adjustMessage[0]=MessageType.MessageType3.name();
        adjustMessage[1]="Mango";
        adjustMessage[2]="2";
        adjustMessage[3]="";
        adjustMessage[4]="MULTIPLY";
        
        adjustment.processMessage(adjustMessage);
        for (Sale sale : SalesDataStore.salesData) {
            if (sale.getProductName().equals("Apple")) {
                assertEquals(BigDecimal.valueOf(2.00), sale.getUnitPrice());
            } else if (sale.getProductName().equals("Mango")) {
                assertEquals(BigDecimal.valueOf(4.00), sale.getUnitPrice());
            }
        }
    }

    @Test(expected = MessageProcessException.class)
    public void processMessage_incorrect_Adjustment_type() throws Exception {
    	String[] adjustMessage = new String [5];
    	adjustMessage[0]=MessageType.MessageType3.name();
        adjustMessage[1]="Mango";
        adjustMessage[2]="1.10";
        adjustMessage[3]="";
        adjustMessage[4]="SUB";
        adjustment.processMessage(adjustMessage);
    }
    
    @Test(expected = MessageProcessException.class)
    public void processMessage_incorrect_message_type() throws Exception {
    	String[] adjustMessage = new String [5];
    	adjustMessage[0]=MessageType.MessageType2.name();
        adjustMessage[1]="Mango";
        adjustMessage[2]="1.10";
        adjustMessage[3]="";
        adjustMessage[4]="SUB";
        adjustment.processMessage(adjustMessage);
    }
    
    @Test(expected = MessageProcessException.class)
    public void processMessage_incorrect_data_type() throws Exception {
    	String[] adjustMessage = new String [4];
    	adjustMessage[0]=MessageType.MessageType3.name();
        adjustMessage[1]="Mango";
        adjustMessage[2]="1.10";
        adjustMessage[3]="SUB";
        adjustment.processMessage(adjustMessage);
    }


}
