package com.ds.jpmc.sales.messageprocessor.model;


import java.math.BigDecimal;

import com.ds.jpmc.sales.messageprocessor.data.store.SalesDataStore;
import com.ds.jpmc.sales.messageprocessor.enums.AdjustmentType;
import com.ds.jpmc.sales.messageprocessor.enums.MessageType;
import com.ds.jpmc.sales.messageprocessor.exception.MessageProcessException;

public class Adjustment implements Message{
	private MessageType messageType;
	private String productName;
    private AdjustmentType type;
    private BigDecimal amount;

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


	public void setType(AdjustmentType type) {
		this.type = type;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public AdjustmentType getType() {
        return type;
    }

    
    public BigDecimal getAmount() {
        return amount;
    }

    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adjustment adjustMent = (Adjustment) o;

        if (type != adjustMent.type) return false;
        if (productName != null ? !productName.equals(adjustMent.productName) : adjustMent.productName != null) return false;
        return amount != null ? amount.equals(adjustMent.amount) : adjustMent.amount == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    
    
    @Override
    public String toString() {
        return "Adjustment{" +
                "type=" + type +
                ", productName='" + getProductName() + '\'' +
                ", amount=" + amount +
                '}';
    }

	@Override
	public void processMessage(String[] message) throws MessageProcessException {
		if(message.length < 5){
			throw new MessageProcessException("Incorrect format message for "+MessageType.MessageType3.name());
		}
		if(message[0]!=null && message[0].trim()!="")
			if (message[0].equalsIgnoreCase(MessageType.MessageType3.name())) {
				this.messageType=MessageType.MessageType1;
			}else{
				throw new MessageProcessException("Invalid message type for" +MessageType.MessageType3.name());
			}
		this.setProductName(message[1]);
		this.amount=new BigDecimal(message[2]);
		String adjustType=message[4];
		switch (adjustType) {
		case "ADD":
			this.type=AdjustmentType.ADD;
			break;
		case "SUBTRACT":
			this.type=AdjustmentType.SUBTRACT;
			break;
		case "MULTIPLY":
			this.type=AdjustmentType.MULTIPLY;
			break;
		default:
			throw new MessageProcessException("Invalid Adjustment Type.");
		}
		
		applyAdjustment(this);
		SalesDataStore.addAdjustment(this);
	}
	
	private void applyAdjustment(Adjustment adjustment) {
        for (Sale sale : SalesDataStore.salesData) {
            if (sale.getProductName().equals(adjustment.getProductName())) {

                if (adjustment.getType() == AdjustmentType.ADD) {
                    sale.setUnitPrice(sale.getUnitPrice().add(adjustment.getAmount()));
                } else if (adjustment.getType() == AdjustmentType.MULTIPLY) {
                    sale.setUnitPrice(sale.getUnitPrice().multiply(adjustment.getAmount()));
                } else if (adjustment.getType() == AdjustmentType.SUBTRACT) {
                    BigDecimal newPrice = sale.getUnitPrice().subtract(adjustment.getAmount());
                    newPrice = newPrice.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : newPrice;
                    sale.setUnitPrice(newPrice);
                }
            }
        }
    }
}
