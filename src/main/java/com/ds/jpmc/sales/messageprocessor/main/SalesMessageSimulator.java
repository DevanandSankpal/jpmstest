package com.ds.jpmc.sales.messageprocessor.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.ds.jpmc.sales.messageprocessor.data.store.SalesDataStore;
/**
 * This is class is to push message to queue from .csv file.
 */
public class SalesMessageSimulator {

private String fileName;	
	
	
	public SalesMessageSimulator(String fileName)
	{
		this.fileName=fileName;
	}
	
	public void pushMessageQueueFromInputFile() {
		
			try {
				List<String> msgList= Files.readAllLines(Paths.get(fileName));
				SalesDataStore.messageQueue.addAll(msgList);
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

	
	
}
