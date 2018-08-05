package com.ds.jpmc.sales.messageprocessor.main;

import com.ds.jpmc.sales.messageprocessor.exception.MessageProcessException;
import com.ds.jpmc.sales.messageprocessor.service.MessageProcessor;
import com.ds.jpmc.sales.messageprocessor.service.ReportGenerator;
import com.ds.jpmc.sales.messageprocessor.service.impl.MessageProcessorImpl;
import com.ds.jpmc.sales.messageprocessor.service.impl.ReportGeneratorImpl;

/**
 * 
 * @author dsankpal
 *	This is the entry point for SalesMessageProcessor application.
 *	The SalesMessageSimulator class is used to read messages from .csv file and push to Queue processing.
 *	Sample test file is available at root level
 *	To execute this class enter filePath as input parameter arg[0].
 *
 */
public class Main
{
    public static void main( String[] args ) throws MessageProcessException 
    {
    	if(args.length<1)
			throw new MessageProcessException("Invalid Input Parameters. File Name is expected as a input.");
    	ReportGenerator reportGenerator= new ReportGeneratorImpl();
    	SalesMessageSimulator salesMessageSimulator = new SalesMessageSimulator(args[0]);
    	salesMessageSimulator.pushMessageQueueFromInputFile();
    	MessageProcessor messageProcessor= new MessageProcessorImpl(reportGenerator);
    	messageProcessor.startProcessing();
    }
}
