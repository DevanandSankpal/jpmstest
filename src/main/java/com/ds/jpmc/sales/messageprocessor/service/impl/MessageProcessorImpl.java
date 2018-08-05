package com.ds.jpmc.sales.messageprocessor.service.impl;

import com.ds.jpmc.sales.messageprocessor.data.store.SalesDataStore;
import com.ds.jpmc.sales.messageprocessor.exception.EmptyQueueException;
import com.ds.jpmc.sales.messageprocessor.handler.MessageEventHander;
import com.ds.jpmc.sales.messageprocessor.service.MessageProcessor;
import com.ds.jpmc.sales.messageprocessor.service.ReportGenerator;
/**
 * 
 * @author dsankpal
 * This class is processing messages form queue and trigger Sales report after processing 10 messages.
 * Also it will generate Adjustment report after 50 messages and stop reading incoming messages.
 *
 */
public class MessageProcessorImpl implements MessageProcessor{

	private ReportGenerator reportGenerator;
	
	public MessageProcessorImpl (ReportGenerator reportGenerator){
		this.reportGenerator=reportGenerator;
	}
	
	
	@Override
	public void startProcessing() {
		System.out.println("Message processor has started!");
		
        int messageProcessed = 0;
        while (SalesDataStore.hasNextEvent()) {
            String queueMessage;
            try {
            	queueMessage = SalesDataStore.nextEvent();
            } catch (EmptyQueueException e) {
                System.err.println(e.getMessage());
                return;
            }
            if(!MessageEventHander.processMessage(queueMessage)){
                  continue;
            }
            
            messageProcessed++;

            if (messageProcessed % 10 == 0) {
                String salesReport = reportGenerator.generateSaleReport(SalesDataStore.salesData);
                System.out.println(salesReport);
            }
            if (messageProcessed == 50) {
                String adjustmentsReport = reportGenerator.generateAdjustmentReport(SalesDataStore.adjustmentsData);
                System.out.println(adjustmentsReport);
                break;
            }
        }

        System.out.println("Message processor has finished.");
	}

	
}
