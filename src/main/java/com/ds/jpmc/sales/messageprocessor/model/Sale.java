package com.ds.jpmc.sales.messageprocessor.model;

import java.math.BigDecimal;

import com.ds.jpmc.sales.messageprocessor.data.store.SalesDataStore;
import com.ds.jpmc.sales.messageprocessor.enums.MessageType;
import com.ds.jpmc.sales.messageprocessor.exception.MessageProcessException;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


public class Sale implements Message{
	
	private MessageType messageType;
	private String productName;
    private BigDecimal unitPrice;
    private int totalUnits;

    
	public int getTotalUnits() {
		return totalUnits;
	}



	public void setTotalUnits(int totalUnits) {
		this.totalUnits = totalUnits;
	}



	public BigDecimal getUnitPrice() {
        return unitPrice;
    }

	
	public MessageType getMessageType() {
		return messageType;
	}



	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}


    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sale sale = (Sale) o;

        if (totalUnits != sale.totalUnits) return false;
        if (productName!= null ? !productName.equals(sale.productName) : sale.productName != null) return false;
        return unitPrice != null ? unitPrice.equals(sale.unitPrice) : sale.unitPrice == null;
    }

    @Override
    public int hashCode() {
        int result = productName != null ? productName.hashCode() : 0;
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + totalUnits;
        return result;
    }
    
    @Override
    public String toString() {
        return "Sale{" +
                "productName='" + getProductName() + '\'' +
                ", unitPrice=" + unitPrice +", "
                + "totalUnits=" + totalUnits +
                
                '}';
    }



	@Override
	public void processMessage(String[] message)
			throws MessageProcessException {
		if(message[0]!=null && message[0].trim()!="")
		switch (message[0]) {
		case "MessageType1":
			this.messageType=MessageType.MessageType1;
			break;
		case "MessageType2":
			this.messageType=MessageType.MessageType2;
			break;
		default:
			System.err.println("Not supporting MesssageType for Sale message");
			 throw new MessageProcessException("Incorrect format message for Sale");
		}
		if(this.messageType.equals(MessageType.MessageType1) && message.length<3) {
            throw new MessageProcessException("Incorrect format message for "+ MessageType.MessageType1.name());
        }else if(this.messageType.equals(MessageType.MessageType2) && message.length<4){
        	throw new MessageProcessException("Incorrect format message for "+MessageType.MessageType2.name());
        }
		
		this.productName=message[1];
		try{
			this.unitPrice=new BigDecimal(message[2]);
		}catch(Exception e){
			throw new MessageProcessException("Invalid amount pass in message");
		}
		
		if(this.getMessageType().equals(MessageType.MessageType2)){
			try{
				this.totalUnits=Integer.parseInt(message[3]);
			}catch(ParseException e){
				throw new MessageProcessException("Invalid amount pass in message for "+MessageType.MessageType2.name());
			}
		}else{
			this.totalUnits=1;
		}
		SalesDataStore.addSale(this);
	}

}
